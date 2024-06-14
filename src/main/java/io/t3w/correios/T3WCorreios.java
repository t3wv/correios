package io.t3w.correios;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.t3w.correios.contratos.T3WCorreiosContrato;
import io.t3w.correios.contratos.T3WCorreiosContratoCartaoPostagem;
import io.t3w.correios.contratos.T3WCorreiosContratoServico;
import io.t3w.correios.contratos.enums.T3WCorreiosContratoCartaoStatus;
import io.t3w.correios.contratos.enums.T3WCorreiosContratoStatus;
import io.t3w.correios.contratos.responses.T3WCorreiosContratoResponseListagemCartaoPaginado;
import io.t3w.correios.contratos.responses.T3WCorreiosContratoResponseListagemServicosPaginado;
import io.t3w.correios.prepostagem.responses.T3WCorreiosPrepostagemResponseCancelamento;
import io.t3w.correios.responses.T3WCorreiosResponseDefault;
import io.t3w.correios.prazo.T3WCorreiosPrazo;
import io.t3w.correios.preco.T3WCorreiosPreco;
import io.t3w.correios.preco.enums.T3WCorreiosPrecoServicoAdicional;
import io.t3w.correios.prepostagem.T3WCorreiosPrepostagemMovimentacao;
import io.t3w.correios.prepostagem.T3WCorreiosPrepostagem;
import io.t3w.correios.rastreamento.T3WCorreiosSroObjeto;
import io.t3w.correios.prepostagem.responses.T3WCorreiosPrepostagemResponseListagemPaginado;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe responsavel por concentrar as opções de serviços dos Correios.
 */
public class T3WCorreios implements T3WLoggable {

    private static final String URL_BASE_PRODUCAO = "https://api.correios.com.br";
    private static final String URL_BASE_HOMOLOGACAO = "https://apihom.correios.com.br";

    private Duration timeout;
    private final HttpClient client;
    private final ObjectMapper objectMapper;
    private final String userId;
    private final String apiToken;
    private final String cartaoPostagem;
    private final String urlBase;
    private T3WCorreiosBearerToken bearerToken;

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public T3WCorreios(final String userId, final String apiToken, final String cartaoPostagem, final boolean isProducao) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("Um ID de usuário válido é necessário para a consulta!");
        }
        if (apiToken == null || apiToken.isBlank()) {
            throw new IllegalArgumentException("Um token de API válido é necessário para a consulta!");
        }
        if (cartaoPostagem == null || cartaoPostagem.isBlank()) {
            throw new IllegalArgumentException("Um cartão de postagem válido é necessário para a consulta!");
        }

        this.userId = userId;
        this.apiToken = apiToken;
        this.cartaoPostagem = cartaoPostagem;
        this.urlBase = isProducao ? URL_BASE_PRODUCAO : URL_BASE_HOMOLOGACAO;

        this.setTimeout(Duration.ofSeconds(15));
        this.client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).followRedirects(HttpClient.Redirect.NORMAL).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        this.objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        this.objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        this.objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        this.objectMapper.registerModule(new SimpleModule().addDeserializer(BigDecimal.class, new StdScalarDeserializer<>(BigDecimal.class) {
            @Override
            public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                return new BigDecimal(p.getValueAsString().replace(',', '.'));
            }
        }));
    }

    public T3WCorreios setTimeout(Duration timeout) {
        this.timeout = timeout;
        return this;
    }

    private T3WCorreiosBearerToken requestBearerToken() throws Exception {
        if (this.bearerToken == null || this.bearerToken.getExpiraEm().isBefore(LocalDateTime.now())) {
            this.getLogger().debug("Requisitando novo bearer token para usuario '{}', api token '{}' e cartão de postagem '{}'", this.userId, this.apiToken, this.cartaoPostagem);
            final var httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(urlBase + "/token/v1/autentica/cartaopostagem"))
                    .POST(HttpRequest.BodyPublishers.ofString("{\"numero\":\"%s\"}".formatted(cartaoPostagem)))
                    .headers("Content-Type", "application/json; charset=utf-8", "Authorization", ("Basic %s".formatted(Base64.getEncoder().encodeToString("%s:%s".formatted(userId, apiToken).getBytes(StandardCharsets.UTF_8)))))
                    .timeout(this.timeout)
                    .build();
            final var response = this.client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpURLConnection.HTTP_CREATED) {
                this.bearerToken = this.objectMapper.readValue(response.body(), T3WCorreiosBearerToken.class);
            } else {
                throw new IllegalAccessException("Requisição de bearer token retornou codigo '%s': '%s'".formatted(response.statusCode(), response.body()));
            }
        }
        return bearerToken;
    }

    private HttpResponse<String> sendPostRequest(final URI uri, final Object object) throws Exception {
        return this.client.send(HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(this.objectMapper.writeValueAsString(object)))
                .headers("Content-Type", "application/json; charset=utf-8", "Authorization", ("Bearer %s".formatted(this.requestBearerToken().getToken())))
                .timeout(this.timeout)
                .uri(uri)
                .build(), HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> sendGetRequest(final URI uri) throws Exception {
        return this.client.send(HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .headers("Content-Type", "application/json; charset=utf-8", "Authorization", ("Bearer %s".formatted(this.requestBearerToken().getToken())))
                .timeout(this.timeout)
                .build(), HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> sendDeleteRequest(final URI uri) throws Exception {
        return this.client.send(HttpRequest.newBuilder()
                .DELETE()
                .uri(uri)
                .headers("Content-Type", "application/json; charset=utf-8", "Authorization", ("Bearer %s".formatted(this.requestBearerToken().getToken())))
                .timeout(this.timeout)
                .build(), HttpResponse.BodyHandlers.ofString());
    }

    // Rastro

    public List<T3WCorreiosSroObjeto> rastrearObjetos(final Set<String> codigosObjetos) throws Exception {
        this.getLogger().debug("Rastreando objetos para usuario '{}', api token '{}' e cartão de postagem '{}': '{}'", this.userId, this.apiToken, this.cartaoPostagem, codigosObjetos);
        final var url = new URI(urlBase + "/srorastro/v1/objetos?codigosObjetos=%s&resultado=T".formatted(String.join(",", codigosObjetos)));
        final var response = this.sendGetRequest(url);
        if (response.statusCode() == HttpsURLConnection.HTTP_OK) {
            return Arrays.asList(this.objectMapper.convertValue(this.objectMapper.readTree(response.body()).get("objetos"), T3WCorreiosSroObjeto[].class));
        }
        throw new Exception("Requisição de rastreamento de objetos retornou código '%s': '%s'".formatted(response.statusCode(), response.body()));
    }

    // Prazo

    public T3WCorreiosPrazo calcularPrazo(final String codigoServico, final String cepOrigem, final String cepDestino) throws Exception {
        this.getLogger().debug("Solicitando prazo de entrega para o servico '{}' de '{}' para '{}'...", codigoServico, cepOrigem, cepDestino);
        final var url = new URI(urlBase + "/prazo/v1/nacional/%s?cepOrigem=%s&cepDestino=%s".formatted(codigoServico, cepOrigem, cepDestino));
        final var response = this.sendGetRequest(url);
        if (response.statusCode() == HttpsURLConnection.HTTP_OK) {
            return this.objectMapper.readValue(response.body(), T3WCorreiosPrazo.class);
        }
        throw new Exception("Verificação de prazo de entrega para servico '%s' retornou código '%s': '%s'".formatted(codigoServico, response.statusCode(), response.body()));
    }

    // Preço

    public T3WCorreiosPreco calcularPreco(final String codigoServico, final String cepOrigem, String cepDestino, int pesoGramas, T3WCorreiosFormatoObjeto tipoObjeto, int comprimentoCm, int alturaCm, int larguraCm, int diametroCm, BigDecimal valorDeclarado, final Set<T3WCorreiosPrecoServicoAdicional> servicosAdicionais) throws Exception {
        this.getLogger().debug("Solicitando preço para servico {}, origem {}, destino {}, peso {}g...", codigoServico, cepOrigem, cepDestino, pesoGramas);

        //se houver o valor declarado, tenho que adicionar nos servicos adicionais
        final var servicosAdicionaisTratado = new HashSet<>(servicosAdicionais);
        if (valorDeclarado != null && valorDeclarado.signum() != 0) {
            servicosAdicionaisTratado.add(T3WCorreiosPrecoServicoAdicional.VALOR_DECLARADO);
        }

        final var url = new URI(urlBase + "/preco/v1/nacional/%s?cepDestino=%s&cepOrigem=%s&psObjeto=%d&tpObjeto=%s&comprimento=%d&altura=%d&largura=%d&diametro=%d&vlDeclarado=%s&servicosAdicionais=%s".formatted(codigoServico, cepDestino, cepOrigem, pesoGramas, tipoObjeto.getCodigo(), comprimentoCm, alturaCm, larguraCm, diametroCm, valorDeclarado, servicosAdicionaisTratado.stream().map(T3WCorreiosPrecoServicoAdicional::getCodigo).collect(Collectors.joining(","))));
        final var response = this.sendGetRequest(url);
        if (response.statusCode() == HttpsURLConnection.HTTP_OK) {
            return this.objectMapper.readValue(response.body(), T3WCorreiosPreco.class);
        }
        throw new Exception("Verificação de preco de envio retornou código '%s': '%s'".formatted(response.statusCode(), response.body()));
    }

    // Prepostagem

    public T3WCorreiosPrepostagem criarPrepostagem(T3WCorreiosPrepostagem prepostagem) throws Exception, T3WCorreiosResponseDefault {
        final var url = new URI(urlBase + "/prepostagem/v1/prepostagens");
        final var response = sendPostRequest(url, prepostagem);
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return this.objectMapper.readValue(response.body(), T3WCorreiosPrepostagem.class);
        } else {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        }
    }

    /**
     * Método que consulta a movimentação de uma pré-postagem pelo código do objeto.
     *
     * @param codigoObjeto Código do objeto de envio.
     * @return Objeto {@link T3WCorreiosPrepostagemMovimentacao} representando a movimentação da pré-postagem.
     * @throws Exception                  Se ocorrer um erro durante o processo de consulta.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado durante a consulta.
     */
    public T3WCorreiosPrepostagemMovimentacao consultarPrepostagem(final String codigoObjeto) throws Exception, T3WCorreiosResponseDefault {
        final var url = new URI(urlBase + "/prepostagem/v1/prepostagens/postada?codigoObjeto=%s".formatted(codigoObjeto));
        final var response = this.sendGetRequest(url);

        if (response.statusCode() == HttpsURLConnection.HTTP_OK) {
            return this.objectMapper.readValue(response.body(), T3WCorreiosPrepostagemMovimentacao.class);
        } else {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        }
    }

    /**
     * Método que consulta as pré-postagens de acordo com os filtros fornecidos.
     *
     * @param id                  Identificador dos Correios do solicitante.
     * @param codigoObjeto        Código do objeto de envio.
     * @param eTicket             Número do e-Ticket.
     * @param codigoEstampa2D     Código da estampa 2D.
     * @param idCorreios          Identificador dos Correios.
     * @param status              Status da pré-postagem.
     * @param logisticaReversa    Indicador de logística reversa.
     * @param tipoObjeto          Tipo do objeto de envio.
     * @param modalidadePagamento Modalidade de pagamento.
     * @param objetoCargo         Objeto de carga.
     * @return Lista de {@link T3WCorreiosPrepostagem} representando as pré-postagens.
     * @throws Exception                  Se ocorrer um erro durante o processo de consulta.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado durante a consulta.
     * @note Os parâmetros opcionais podem ser omitidos. Se não fornecidos, a consulta retornará todas as pré-postagens.
     * @note A consulta é realizada em páginas para evitar problemas de memória com grandes conjuntos de dados.
     */
    public List<T3WCorreiosPrepostagem> consultarPrepostagens(String id, String codigoObjeto, String eTicket, String codigoEstampa2D, String idCorreios, String status, String logisticaReversa, String tipoObjeto, String modalidadePagamento, String objetoCargo) throws Exception, T3WCorreiosResponseDefault {
        var page = 0;
        final var pageSize = 50;
        var uri = (urlBase + "/prepostagem/v2/prepostagens?id=%s&codigoObjeto=%s&eTicket=%s&codigoEstampa2D=%s&idCorreios=%s&status=%s&logisticaReversa=%s&tipoObjeto=%s&modalidadePagamento=%s&objetoCargo=%s&page=%s&size=%s");

        var url = new URI(uri.formatted(Objects.toString(id, ""), Objects.toString(codigoObjeto, ""), Objects.toString(eTicket, ""), Objects.toString(codigoEstampa2D, ""), Objects.toString(idCorreios, ""), Objects.toString(status, ""), Objects.toString(logisticaReversa, ""), Objects.toString(tipoObjeto, ""), Objects.toString(modalidadePagamento, ""), Objects.toString(objetoCargo, ""), page, pageSize));
        var response = this.sendGetRequest(url);

        final var prepostagens = new ArrayList<T3WCorreiosPrepostagem>();

        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            var responseParsed = this.objectMapper.readValue(response.body(), T3WCorreiosPrepostagemResponseListagemPaginado.class);
            do {
                prepostagens.addAll(responseParsed.getItens());
                if (!responseParsed.getPage().getLast()) {
                    page++;
                    url = new URI(uri.formatted(Objects.toString(id, ""), Objects.toString(codigoObjeto, ""), Objects.toString(eTicket, ""), Objects.toString(codigoEstampa2D, ""), Objects.toString(idCorreios, ""), Objects.toString(status, ""), Objects.toString(logisticaReversa, ""), Objects.toString(tipoObjeto, ""), Objects.toString(modalidadePagamento, ""), Objects.toString(objetoCargo, ""), page, pageSize));
                    response = this.sendGetRequest(url);
                    responseParsed = this.objectMapper.readValue(response.body(), T3WCorreiosPrepostagemResponseListagemPaginado.class);
                }
            } while (responseParsed.getPage().getNext());
            return prepostagens;
        } else {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        }
    }

    /**
     * Método que cancela uma pré-postagem.
     *
     * @param idCorreios    Identificador dos Correios do solicitante do cancelamento.
     * @param idPrepostagem Identificador da pré-postagem a ser cancelada.
     * @return Objeto {@link T3WCorreiosPrepostagemResponseCancelamento} representando o resultado do cancelamento.
     * @throws Exception                  Se ocorrer um erro durante o processo de cancelamento.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado durante o cancelamento.
     */
    public T3WCorreiosPrepostagemResponseCancelamento cancelarPrePostagem(String idCorreios, String idPrepostagem) throws Exception, T3WCorreiosResponseDefault {
        final var url = new URI(urlBase + "/prepostagem/v1/prepostagens/%s?idCorreiosSolicitanteCancelamento=%s".formatted(idPrepostagem, idCorreios));
        final var response = this.sendDeleteRequest(url);
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return this.objectMapper.readValue(response.body(), T3WCorreiosPrepostagemResponseCancelamento.class);
        } else {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        }
    }

    // contratos

    /**
     * Método que consulta <b>todos</b> os contratos de um determinado cnpj.
     *
     * @param cnpj Cnpj da empresa.
     */
    public List<T3WCorreiosContrato> consultarContratos(final String cnpj) throws Exception, T3WCorreiosResponseDefault {
        final List<T3WCorreiosContrato> contratos = new ArrayList<>();
        for (T3WCorreiosContratoStatus correiosContratoStatus : T3WCorreiosContratoStatus.values()) {
            try {
                contratos.addAll(this.consultarContratos(cnpj, correiosContratoStatus, true));
                contratos.addAll(this.consultarContratos(cnpj, correiosContratoStatus, false));
            } catch (T3WCorreiosResponseDefault e) {
                this.getLogger().info("Consulta de contratos com status '{}' não obteve resultados. Motivo: '{}'", correiosContratoStatus, e.getMessage());
            }
        }
        return contratos;
    }

    /**
     * Método que consulta os contratos de um determinado cnpj.
     *
     * @param cnpj            Cnpj da empresa a ser consultado os contratos.
     * @param status          Status do contrato.
     * @param somenteVigentes Flag para indicar se a consulta deve trazer somente contratos vigentes. 'true' para somente contratos vigentes, 'false' para todos.
     * @return Lista de {@link T3WCorreiosContrato} representando os contratos.
     * @throws Exception                  Se ocorrer um erro durante o processo.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado.
     * @note O parâmetro {@code status} é opcional. Se não informado, retorna por padrão os ativos.
     */
    public List<T3WCorreiosContrato> consultarContratos(final String cnpj, T3WCorreiosContratoStatus status, boolean somenteVigentes) throws Exception, T3WCorreiosResponseDefault {
        var response = this.sendGetRequest(new URI(urlBase + "/meucontrato/v1/empresas/%s/contratos?status=%s&vigente=%s".formatted(cnpj, Objects.toString(status, ""), somenteVigentes ? "S" : "")));
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return Arrays.stream(this.objectMapper.readValue(response.body(), T3WCorreiosContrato[].class)).toList();
        } else {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        }
    }

    /**
     * Método que consulta os serviços de um determinado contrato.
     *
     * @param cnpj                 Cnpj da empresa.
     * @param numeroContrato       Número do contrato.
     * @param numeroCartaoPostagem Número do cartão de postagem.
     * @return Lista de {@link T3WCorreiosContratoServico} representando os serviços.
     * @throws Exception                  Se ocorrer um erro durante o processo.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado.
     * @note O parâmetro {@code numeroCartaoPostagem} é opcional. Se informado, busca apenas os serviços disponíveis para este cartão.
     */
    public List<T3WCorreiosContratoServico> consultarServicosByContrato(String cnpj, String numeroContrato, String numeroCartaoPostagem) throws Exception, T3WCorreiosResponseDefault {
        var page = 0;
        final var pageSize = 50;
        final var servicos = new ArrayList<T3WCorreiosContratoServico>();

        var uri = (urlBase + "/meucontrato/v1/empresas/%s/contratos/%s/servicos?nuCartaoPostagem=%s&page=%s&size=%s");
        var response = this.sendGetRequest(new URI(uri.formatted(cnpj, numeroContrato, Objects.toString(numeroCartaoPostagem, ""), page, pageSize)));

        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            var responseParsed = this.objectMapper.readValue(response.body(), T3WCorreiosContratoResponseListagemServicosPaginado.class);
            do {
                servicos.addAll(responseParsed.getItens());
                if (!Objects.equals(responseParsed.getPage().getNumber(), responseParsed.getPage().getTotalPages())) {
                    page++;
                    response = this.sendGetRequest(new URI(uri.formatted(cnpj, numeroContrato, Objects.toString(numeroCartaoPostagem, ""), page, pageSize)));
                    responseParsed = this.objectMapper.readValue(response.body(), T3WCorreiosContratoResponseListagemServicosPaginado.class);
                }
            } while (!Objects.equals(responseParsed.getPage().getNumber(), responseParsed.getPage().getTotalPages()));
            return servicos;
        } else {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        }
    }

    /**
     * Método que consulta a categoria de um determinado contrato.
     *
     * @param cnpj           Cnpj da empresa.
     * @param numeroContrato Número do contrato.
     * @return Objeto {@link T3WCorreiosContrato} representando a categoria do contrato.
     * @throws Exception                  Se ocorrer um erro durante o processo.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado.
     */
    public T3WCorreiosContrato consultarCategoriaContrato(String cnpj, String numeroContrato) throws Exception, T3WCorreiosResponseDefault {
        var response = this.sendGetRequest(new URI(urlBase + "/meucontrato/v1/empresas/%S/contratos/%s/categoria".formatted(cnpj, numeroContrato)));
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return this.objectMapper.readValue(response.body(), T3WCorreiosContrato.class);
        } else {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        }
    }

    /**
     * Método que consulta <b>todos</b> os cartões de postagem de um determinado contrato.
     *
     * @param cnpj           Cnpj da empresa.
     * @param numeroContrato Número do contrato.
     */
    public List<T3WCorreiosContratoCartaoPostagem> consultarCartoesPostagemByContrato(String cnpj, String numeroContrato) throws T3WCorreiosResponseDefault, Exception {
        final List<T3WCorreiosContratoCartaoPostagem> cartoes = new ArrayList<>();
        for (T3WCorreiosContratoCartaoStatus correiosCartoesStatus : T3WCorreiosContratoCartaoStatus.values()) {
            try {
                cartoes.addAll(this.consultarCartoesPostagemByContrato(cnpj, numeroContrato, correiosCartoesStatus, true));
                cartoes.addAll(this.consultarCartoesPostagemByContrato(cnpj, numeroContrato, correiosCartoesStatus, false));
            } catch (T3WCorreiosResponseDefault e) {
                this.getLogger().info("Consulta de cartões postagem com status '{}' não obteve retornos. Motivo: '{}'", correiosCartoesStatus, e.getMessage());
            }
        }
        return cartoes;
    }

    /**
     * Método que consulta os cartões de postagem de um determinado contrato.
     *
     * @param cnpj            Cnpj da empresa.
     * @param numeroContrato  Número do contrato.
     * @param status          Status do cartão de postagem.
     * @param somenteVigentes Flag para indicar se a consulta deve trazer somente cartões vigentes. 'true' para somente cartões vigentes, 'false' para todos.
     * @return Lista de {@link T3WCorreiosContratoCartaoPostagem} representando os cartões de postagem.
     * @throws Exception                  Se ocorrer um erro durante o processo.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado.
     * @note O parâmetro {@code status} é opcional. Se não informado, por padrão a api retorna os ativos.
     */
    public List<T3WCorreiosContratoCartaoPostagem> consultarCartoesPostagemByContrato(String cnpj, String numeroContrato, T3WCorreiosContratoCartaoStatus status, boolean somenteVigentes) throws Exception, T3WCorreiosResponseDefault {
        var page = 0;
        final var pageSize = 50;
        final var cartoes = new ArrayList<T3WCorreiosContratoCartaoPostagem>();

        var uri = (urlBase + "/meucontrato/v1/empresas/%s/contratos/%s/cartoes?status=%s&vigente=%s&page=%s&size=%s");
        var response = this.sendGetRequest(new URI(uri.formatted(cnpj, numeroContrato, Objects.toString(status, ""), somenteVigentes ? "S" : "", page, pageSize)));

        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            var responseParsed = this.objectMapper.readValue(response.body(), T3WCorreiosContratoResponseListagemCartaoPaginado.class);
            do {
                cartoes.addAll(responseParsed.getItens());
                if (!Objects.equals(responseParsed.getPage().getNumber(), responseParsed.getPage().getTotalPages())) {
                    page++;
                    response = this.sendGetRequest(new URI(uri.formatted(cnpj, numeroContrato, Objects.toString(status, ""), somenteVigentes ? "S" : "", page, pageSize)));
                    responseParsed = this.objectMapper.readValue(response.body(), T3WCorreiosContratoResponseListagemCartaoPaginado.class);
                }
            } while (!Objects.equals(responseParsed.getPage().getNumber(), responseParsed.getPage().getTotalPages()));
            return cartoes;
        } else {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        }
    }

    // todo: GENERALIZAR RESPOSTAS PAGINADAS
}
