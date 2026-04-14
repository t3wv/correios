package io.t3w.correios;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
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
import io.t3w.correios.faturas.T3WCorreiosFatura;
import io.t3w.correios.faturas.T3WCorreiosFaturaProcessoAssincrono;
import io.t3w.correios.faturas.enums.T3WCorreiosFaturasTipoPrevia;
import io.t3w.correios.prazo.T3WCorreiosPrazo;
import io.t3w.correios.preco.T3WCorreiosPreco;
import io.t3w.correios.preco.enums.T3WCorreiosPrecoServicoAdicional;
import io.t3w.correios.prepostagem.T3WCorreiosPrepostagem;
import io.t3w.correios.prepostagem.T3WCorreiosPrepostagemMovimentacao;
import io.t3w.correios.prepostagem.T3WCorreiosPrepostagemRequisicaoRotulo;
import io.t3w.correios.prepostagem.responses.T3WCorreiosPrepostagemResponseCancelamento;
import io.t3w.correios.prepostagem.responses.T3WCorreiosPrepostagemResponseListagemPaginado;
import io.t3w.correios.rastreamento.T3WCorreiosSroObjeto;
import io.t3w.correios.responses.T3WCorreiosResponseDefault;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Classe responsavel por concentrar as opções de serviços dos Correios.
 */
public class T3WCorreios implements T3WCorreiosLoggable {

    private static final String URL_BASE_PRODUCAO = "https://api.correios.com.br";
    private static final String URL_BASE_HOMOLOGACAO = "https://apihom.correios.com.br";

    private Duration timeout;
    private final ObjectMapper objectMapper;
    private final String userId;
    private final String apiToken;
    private final String cartaoPostagem;
    private final String urlBase;
    private T3WCorreiosBearerToken bearerToken;

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * Classe principal de consulta à api.
     *
     * @param userId         ID de usuário dos Correios.
     * @param apiToken       Token de API dos Correios.
     * @param cartaoPostagem Número do cartão de postagem.
     * @param isHomologacao  Indica se a consulta será realizada em ambiente de homologação.
     * @throws IllegalArgumentException Se os parâmetros de entrada forem inválidos.
     */
    public T3WCorreios(final String userId, final String apiToken, final String cartaoPostagem, final boolean isHomologacao) {
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
        this.urlBase = isHomologacao ? URL_BASE_HOMOLOGACAO : URL_BASE_PRODUCAO;

        this.setTimeout(Duration.ofSeconds(15));
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


    /**
     * Define o tempo limite para as solicitações feitas por esta instância.
     *
     * @param timeout Tempo limite. {@see Duration}.
     * @return A própria instância de {@link T3WCorreios}, permitindo encadeamento de chamadas.
     * @note O tempo limite padrão é de 15 segundos.
     */
    public T3WCorreios setTimeout(Duration timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * Método privado que solicita um novo token de autenticação aos servidores dos Correios.
     * Este método é chamado automaticamente quando o token expira ou não existe.
     *
     * @return Objeto {@link T3WCorreiosBearerToken} representando o novo token de autenticação.
     * @throws Exception              Se ocorrer um erro durante a solicitação do token.
     * @throws IllegalAccessException Se a solicitação de token retornar um código de status inesperado.
     */
    private T3WCorreiosBearerToken requestBearerToken() throws Exception, T3WCorreiosResponseDefault {
        if (this.bearerToken == null || this.bearerToken.getExpiraEm().isBefore(LocalDateTime.now())) {
            this.getLogger().debug("Requisitando novo bearer token para usuario '{}', api token '{}' e cartão de postagem '{}'", this.userId, this.apiToken, this.cartaoPostagem);
            try (final var client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).followRedirects(HttpClient.Redirect.NORMAL).build()) {
                final var request = HttpRequest.newBuilder().uri(new URI(urlBase + "/token/v1/autentica/cartaopostagem")).POST(HttpRequest.BodyPublishers.ofString("{\"numero\":\"%s\"}".formatted(cartaoPostagem))).headers("Content-Type", "application/json; charset=utf-8", "Authorization", ("Basic %s".formatted(Base64.getEncoder().encodeToString("%s:%s".formatted(userId, apiToken).getBytes(StandardCharsets.UTF_8))))).timeout(this.timeout).build();
                final var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == HttpURLConnection.HTTP_CREATED) {
                    this.bearerToken = this.objectMapper.readValue(response.body(), T3WCorreiosBearerToken.class);
                } else if (response.body() != null && !response.body().isBlank()) {
                    throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
                } else {
                    throw new IllegalAccessException("Requisição de bearer token retornou codigo '%s': '%s'".formatted(response.statusCode(), response.body()));
                }
            }
        }
        return bearerToken;
    }

    /**
     * M&eacute;todo privado que envia uma solicita&ccedil;&atilde;o POST para a API dos correios com cabe&ccedil;alho:
     * <code>Content-Type application/json; charset=utf-8</code> e <code>Authorization Bearer {token}<code>.
     *
     * @param uri    URI para onde a solicita&ccedil;&atilde;o ser&aacute; enviada.
     * @param object Objeto que ser&aacute; enviado no corpo da solicita&ccedil;&atilde;o como um JSON.
     * @return Objeto {@link HttpResponse} contendo a resposta da API.
     * @throws Exception Se ocorrer um erro durante o envio da solicita&ccedil;&atilde;o.
     * @note O tempo limite da solicita&ccedil;&atilde;o &eacute; definido pelo valor de {@link #setTimeout(Duration)}}".
     */
    private HttpResponse<String> sendPostRequest(final URI uri, final Object object) throws Exception, T3WCorreiosResponseDefault {
        try (final var client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).followRedirects(HttpClient.Redirect.NORMAL).build()) {
            return client.send(HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(this.objectMapper.writeValueAsString(object))).headers("Content-Type", "application/json; charset=utf-8", "Authorization", ("Bearer %s".formatted(this.requestBearerToken().getToken()))).timeout(this.timeout).uri(uri).build(), HttpResponse.BodyHandlers.ofString());
        }
    }

    /**
     * Método privado que realiza uma requisição GET para a API dos Correios com cabeçalho:
     * <code>Content-Type application/json; charset=utf-8</code> e <code>Authorization Bearer {token}<code>.
     *
     * @param uri URI para a qual a requisição será feita.
     * @return Objeto {@link HttpResponse} contendo a resposta da API.
     * @throws Exception Se ocorrer um erro durante a requisição.
     * @note O tempo limite da solicitação é definido pelo valor de {@link #setTimeout(Duration)}}".
     */
    private HttpResponse<String> sendGetRequest(final URI uri) throws Exception, T3WCorreiosResponseDefault {
        try (final var client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).followRedirects(HttpClient.Redirect.NORMAL).build()) {
            return client.send(HttpRequest.newBuilder().GET().uri(uri).headers("Content-Type", "application/json; charset=utf-8", "Authorization", ("Bearer %s".formatted(this.requestBearerToken().getToken()))).timeout(this.timeout).build(), HttpResponse.BodyHandlers.ofString());
        }
    }

    /**
     * Método privado que realiza uma requisição DELETE para a API dos Correios com cabeçalho:
     * <code>Content-Type application/json; charset=utf-8</code> e <code>Authorization Bearer {token}<code>.
     *
     * @param uri URI para a qual a requisição será feita.
     * @return Objeto {@link HttpResponse} contendo a resposta da API.
     * @throws Exception Se ocorrer um erro durante a requisição.
     * @note O tempo limite da solicitação é definido pelo valor de {@link #setTimeout(Duration)}}".
     */
    private HttpResponse<String> sendDeleteRequest(final URI uri) throws Exception, T3WCorreiosResponseDefault {
        try (final var client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).followRedirects(HttpClient.Redirect.NORMAL).build()) {
            return client.send(HttpRequest.newBuilder().DELETE().uri(uri).headers("Content-Type", "application/json; charset=utf-8", "Authorization", ("Bearer %s".formatted(this.requestBearerToken().getToken()))).timeout(this.timeout).build(), HttpResponse.BodyHandlers.ofString());
        }
    }

    // Rastro

    /**
     * Método que realiza o rastreamento de objetos de acordo com os códigos de objetos fornecidos.
     *
     * @param codigosObjetos Conjunto de códigos de objetos de envio a serem rastreados.
     * @return Lista de {@link T3WCorreiosSroObjeto} representando os objetos rastreados.
     * @throws Exception                  Se ocorrer um erro durante o processo de consulta.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado durante a consulta.
     */
    public List<T3WCorreiosSroObjeto> rastrearObjetos(final Set<String> codigosObjetos) throws Exception, T3WCorreiosResponseDefault {
        this.getLogger().debug("Rastreando objetos para usuario '{}', api token '{}' e cartão de postagem '{}': '{}'", this.userId, this.apiToken, this.cartaoPostagem, codigosObjetos);

        final var url = new URI(urlBase + "/srorastro/v1/objetos?codigosObjetos=%s&resultado=T".formatted(String.join(",", codigosObjetos)));
        final var response = this.sendGetRequest(url);
        if (response.statusCode() == HttpsURLConnection.HTTP_OK) {
            return Arrays.asList(this.objectMapper.convertValue(this.objectMapper.readTree(response.body()).get("objetos"), T3WCorreiosSroObjeto[].class));
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
        }
    }

    // Prazo

    /**
     * Método que calcula o prazo de entrega para um determinado serviço de envio.
     *
     * @param codigoServico Código do serviço de envio.
     * @param cepOrigem     CEP de origem.
     * @param cepDestino    CEP de destino.
     * @return Objeto {@link T3WCorreiosPrazo} representando o prazo de entrega.
     * @throws Exception                  Se ocorrer um erro durante o processo.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado.
     */
    public T3WCorreiosPrazo calcularPrazo(final String codigoServico, final String cepOrigem, final String cepDestino) throws Exception, T3WCorreiosResponseDefault {
        this.getLogger().debug("Solicitando prazo de entrega para o servico '{}' de '{}' para '{}'...", codigoServico, cepOrigem, cepDestino);
        final var url = new URI(urlBase + "/prazo/v1/nacional/%s?cepOrigem=%s&cepDestino=%s".formatted(codigoServico, cepOrigem, cepDestino));
        final var response = this.sendGetRequest(url);
        if (response.statusCode() == HttpsURLConnection.HTTP_OK) {
            return this.objectMapper.readValue(response.body(), T3WCorreiosPrazo.class);
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
        }
    }

    // Preço

    /**
     * Método que calcula o preço de um serviço de entrega dos Correios.
     *
     * @param codigoServico      Código do serviço de entrega.
     * @param cepOrigem          CEP de origem.
     * @param cepDestino         CEP de destino.
     * @param pesoGramas         Peso do objeto em gramas.
     * @param tipoObjeto         Tipo do objeto de envio.
     * @param comprimentoCm      Comprimento do objeto em centímetros.
     * @param alturaCm           Altura do objeto em centímetros.
     * @param larguraCm          Largura do objeto em centímetros.
     * @param diametroCm         Diâmetro do objeto em centímetros.
     * @param valorDeclarado     Valor declarado do objeto.
     * @param servicosAdicionais Serviços adicionais solicitados.
     * @return Objeto {@link T3WCorreiosPreco} representando o preço do serviço.
     * @throws Exception                  Se ocorrer um erro durante o cálculo do preço.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado durante o cálculo do preço.
     */

    public T3WCorreiosPreco calcularPreco(final String codigoServico, final String cepOrigem, String cepDestino, int pesoGramas, T3WCorreiosFormatoObjeto tipoObjeto, int comprimentoCm, int alturaCm, int larguraCm, int diametroCm, BigDecimal valorDeclarado, final Set<T3WCorreiosPrecoServicoAdicional> servicosAdicionais) throws Exception, T3WCorreiosResponseDefault {
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
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
        }
    }

    // Prepostagem

    /**
     * Método que cria uma pré-postagem.
     *
     * @param prepostagem Objeto {@link T3WCorreiosPrepostagem} contendo os dados da pré-postagem a ser criada.
     * @return Objeto {@link T3WCorreiosPrepostagem} representando a pré-postagem criada.
     * @throws Exception                  Se ocorrer um erro durante o processo de criação.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado durante a criação.
     */
    public T3WCorreiosPrepostagem criarPrepostagem(T3WCorreiosPrepostagem prepostagem) throws Exception, T3WCorreiosResponseDefault {
        final var url = new URI(urlBase + "/prepostagem/v1/prepostagens");
        final var response = sendPostRequest(url, prepostagem);
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return this.objectMapper.readValue(response.body(), T3WCorreiosPrepostagem.class);
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
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
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
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
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
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
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
        }
    }

    /**
     * Método que solicita a etiqueta de postagem de um objeto registrado criado por uma prepostagem.
     *
     * @param rotulo Objeto {@link T3WCorreiosPrepostagemRequisicaoRotulo} contendo as informações necessárias para solicitar o rótulo.
     * @return Identificador do recibo da solicitação de rótulo.
     **/
    public String solicitarRotulo(T3WCorreiosPrepostagemRequisicaoRotulo rotulo) throws Exception, T3WCorreiosResponseDefault {
        final var url = new URI(urlBase + "/prepostagem/v1/prepostagens/rotulo/assincrono/pdf");
        final var response = sendPostRequest(url, rotulo);
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return this.objectMapper.readTree(response.body()).get("idRecibo") != null ? this.objectMapper.readTree(response.body()).get("idRecibo").asText() : null;
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
        }
    }

    /**
     * Método que faz download da etiqueta gerada através do método {@link  T3WCorreios#solicitarRotulo(T3WCorreiosPrepostagemRequisicaoRotulo)}
     *
     * @param idRecibo
     * @return Array de bytes contendo os dados da etiqueta.
     * @throws Exception
     * @throws T3WCorreiosResponseDefault
     */

    public byte[] baixarRotulo(String idRecibo) throws Exception, T3WCorreiosResponseDefault {
        final var tentativas = 5;
        final var timeoutTentativaSolicitacaoRotulo = 3;
        final var url = new URI(urlBase + "/prepostagem/v1/prepostagens/rotulo/download/assincrono/%s".formatted(idRecibo));
        var response = sendGetRequest(url);

        for (int i = 0; i < tentativas; i++) {
            if (response.statusCode() == 200) {
                System.out.println(response.body());
                return Base64.getDecoder().decode(this.objectMapper.readTree(response.body()).get("dados").asText());
            } else {
                TimeUnit.SECONDS.sleep(timeoutTentativaSolicitacaoRotulo);
                response = sendGetRequest(url);
            }
        }
        return new byte[0];
    }

    // contratos

    /**
     * Método que consulta <b>todos</b> os contratos de um determinado cnpj.
     *
     * @param cnpj Cnpj da empresa.
     */
    public List<T3WCorreiosContrato> consultarContratos(final String cnpj) throws Exception {
        final List<T3WCorreiosContrato> contratos = new ArrayList<>();
        for (T3WCorreiosContratoStatus correiosContratoStatus : T3WCorreiosContratoStatus.values()) {
            try {
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
        var response = this.sendGetRequest(new URI(urlBase + "/meucontrato/v1/empresas/%s/contratos?status=%s&vigente=%s".formatted(cnpj, Objects.toString(status, ""), somenteVigentes ? "S" : "N")));
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return Arrays.stream(this.objectMapper.readValue(response.body(), T3WCorreiosContrato[].class)).toList();
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
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
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
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
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
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
        var response = this.sendGetRequest(new URI(uri.formatted(cnpj, numeroContrato, Objects.toString(status, ""), somenteVigentes ? "S" : "N", page, pageSize)));

        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            var responseParsed = this.objectMapper.readValue(response.body(), T3WCorreiosContratoResponseListagemCartaoPaginado.class);
            do {
                cartoes.addAll(responseParsed.getItens());
                if (!Objects.equals(responseParsed.getPage().getNumber(), responseParsed.getPage().getTotalPages())) {
                    page++;
                    response = this.sendGetRequest(new URI(uri.formatted(cnpj, numeroContrato, Objects.toString(status, ""), somenteVigentes ? "S" : "N", page, pageSize)));
                    responseParsed = this.objectMapper.readValue(response.body(), T3WCorreiosContratoResponseListagemCartaoPaginado.class);
                }
            } while (!Objects.equals(responseParsed.getPage().getNumber(), responseParsed.getPage().getTotalPages()));
            return cartoes;
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
        }
    }

    /**
     * Método que consulta as faturas de um contrato emitidas durante um determinado período.
     *
     * @param numeroContrato Número do contrato.
     * @param drContrato     Código DR do contrato, também referenciado em outras partes da api como SE.
     * @param dataInicial    Data inicial para o período em que as faturas buscadas são relativas.
     * @param dataFinal      Data final para o período em que as faturas buscadas são relativas.
     * @return Lista de {@link T3WCorreiosFatura} representando as faturas existentes.
     * @throws Exception                  Se ocorrer um erro durante o processo.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado.
     */
    public List<T3WCorreiosFatura> buscaFaturasPorPeriodo(String numeroContrato, String drContrato, LocalDate dataInicial, LocalDate dataFinal) throws Exception, T3WCorreiosResponseDefault {
        final var dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        final var url = new URI(urlBase + "/faturas/v1/faturas?contrato=%s&dr=%s&dataInicial=%s&dataFinal=%s".formatted(numeroContrato, drContrato, dataInicial.format(dateFormatter), dataFinal.format(dateFormatter)));
        final var response = sendGetRequest(url);
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            this.objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);
            return this.objectMapper.readValue(response.body(), new TypeReference<>() {
            });
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
        }
    }

    /**
     * Método que solicita a geração da prévia de uma fatura
     *
     * @param tipoPrevia     Tipo da prévia "ANALITICO" ou "SINTETICO"
     * @param numeroContrato Número do contrato.
     * @param drContrato     Código DR do contrato, também referenciado em outras partes da api como SE
     * @param centroCusto    Centro de custo - Numero atrelado ao cartão de postagem
     * @return Objeto {@link T3WCorreiosFaturaProcessoAssincrono} representando a solicitação registrada.
     * @throws Exception                  Se ocorrer um erro durante o processo.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado.
     */
    public T3WCorreiosFaturaProcessoAssincrono solicitarPreviaFatura(T3WCorreiosFaturasTipoPrevia tipoPrevia, String numeroContrato, String drContrato, String centroCusto) throws Exception, T3WCorreiosResponseDefault {
        final var url = new URI(urlBase + "/faturas/v1/previas?tipoPrevia=%s&contrato=%s&dr=%s%s".formatted(tipoPrevia.name(), numeroContrato, drContrato, centroCusto != null && !centroCusto.isBlank() ? "&centroCusto=%s".formatted(centroCusto) : ""));
        final var response = sendPostRequest(url, "");
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return this.objectMapper.readValue(response.body(), T3WCorreiosFaturaProcessoAssincrono.class);
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
        }
    }

    /**
     * Método que solicita a geração do extrato analítico de uma fatura
     *
     * @param fatura        Número da fatura
     * @param tipoDocumento Tipo de documento da fatura.<b>Obs.:</b> Não consta informações na documentação oficial sobre os outros tipos de documentos existentes ou ao que se refere exatamente.
     *                      Retirado da descrição do campo no swagger '<i>Tipo do documento. Exemplo: RE (equivale ao R&)</i>'
     * @param drFatura      Código DR da fatura, também referenciado em outras partes da api como SE.
     * @param itemFatura    Número do item da fatura.
     * @return Objeto {@link T3WCorreiosFaturaProcessoAssincrono} representando a solicitação registrada.
     * @throws Exception                  Se ocorrer um erro durante o processo.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado.
     */
    public T3WCorreiosFaturaProcessoAssincrono solicitarExtratoAnaliticoFatura(String fatura, String tipoDocumento, String drFatura, String itemFatura) throws Exception, T3WCorreiosResponseDefault {
        final var url = new URI(urlBase + "/faturas/v1/faturas/%s/analitico?tipoDocumento=%s&drFatura=%s&itemFatura=%s".formatted(fatura, tipoDocumento, drFatura, itemFatura));
        final var response = sendPostRequest(url, "");
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return this.objectMapper.readValue(response.body(), T3WCorreiosFaturaProcessoAssincrono.class);
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
        }
    }

    /**
     * Método que verifica o processamento de uma solicitação de fatura gerada previamente
     *
     * @param idProcessamento Id da solicitação de processamento gerada anteriormente
     * @return Objeto {@link T3WCorreiosFaturaProcessoAssincrono} objeto representando a solicitação.
     * @throws Exception                  Se ocorrer um erro durante o processo.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado.
     */
    public T3WCorreiosFaturaProcessoAssincrono verificarProcessamentoSolicitacaoFatura(String idProcessamento) throws Exception, T3WCorreiosResponseDefault {
        final var url = new URI(urlBase + "/faturas/v1/processamentos/%s".formatted(idProcessamento));
        final var response = sendGetRequest(url);
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return this.objectMapper.readValue(response.body(), T3WCorreiosFaturaProcessoAssincrono.class);
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
        }
    }

    /**
     * Método que resgata uma fatura(ou prévia) em CSV através do id de processamento recebido de uma consulta de geração anterior
     *
     * @param idProcessamento Id da solicitação de processamento gerada anteriormente
     * @return String CSV com a fatura solicitada.
     * @throws Exception                  Se ocorrer um erro durante o processo.
     * @throws T3WCorreiosResponseDefault Se a API retornar um resultado inesperado.
     */

    public String baixarFatura(String idProcessamento) throws Exception, T3WCorreiosResponseDefault {
        final var url = new URI(urlBase + "/faturas/v1/processamentos/%s/file".formatted(idProcessamento));
        final var response = sendGetRequest(url);
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            return response.body();
        } else if (response.body() != null && !response.body().isBlank()) {
            throw this.objectMapper.readValue(response.body(), T3WCorreiosResponseDefault.class);
        } else {
            throw new Exception("Erro inesperado durante a requisição - '%s': '%s'".formatted(response.statusCode(), response.body()));
        }
    }
}
