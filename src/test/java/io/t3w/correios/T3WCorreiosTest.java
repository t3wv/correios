package io.t3w.correios;

import io.t3w.correios.contratos.T3WCorreiosContrato;
import io.t3w.correios.contratos.enums.T3WCorreiosContratoCartaoStatus;
import io.t3w.correios.contratos.enums.T3WCorreiosContratoStatus;
import io.t3w.correios.faturas.T3WCorreiosFatura;
import io.t3w.correios.faturas.T3WCorreiosFaturaProcessoAssincrono;
import io.t3w.correios.faturas.enums.T3WCorreiosFaturasTipoPrevia;
import io.t3w.correios.prepostagem.T3WCorreiosPrepostagemItemDeclaracaoConteudo;
import io.t3w.correios.prepostagem.T3WCorreiosPrepostagemRequisicaoRotulo;
import io.t3w.correios.responses.T3WCorreiosResponseDefault;
import io.t3w.correios.preco.enums.T3WCorreiosPrecoServicoAdicional;
import io.t3w.correios.prepostagem.T3WCorreiosPrepostagem;
import io.t3w.correios.rastreamento.T3WCorreiosSroObjeto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class T3WCorreiosTest {

    private static String CNPJ;
    private static String USER_ID;
    private static String API_TOKEN;
    private static String CARTAO_POSTAGEM;
    private static String CONTRATO;
    private static String DRSE_CONTRATO;
    private static T3WCorreiosCorreios CORREIOS;

    @BeforeAll
    public static void preparaTestes() {
        USER_ID = System.getenv("CORREIOS_USER_ID");
        API_TOKEN = System.getenv("CORREIOS_API_TOKEN");
        CARTAO_POSTAGEM = System.getenv("CORREIOS_CARTAO_POSTAGEM");
        CNPJ = System.getenv("CORREIOS_CNPJ");
        CONTRATO = System.getenv("CORREIOS_CONTRATO");
        DRSE_CONTRATO = System.getenv("CORREIOS_DRSE_CONTRATO");
        CORREIOS = new T3WCorreiosCorreios(USER_ID, API_TOKEN, CARTAO_POSTAGEM, true);
    }

    @Disabled
    @Test
    void testRastreamentoObjetos() throws Exception, T3WCorreiosResponseDefault {
        final var objetosCodigosList = Set.of("TJ134711095BR");
        final var objetosRastreamentos = CORREIOS.rastrearObjetos(objetosCodigosList);
        assertNotNull(objetosRastreamentos);
        assertEquals(objetosRastreamentos.size(), objetosCodigosList.size());
        assertTrue(objetosRastreamentos.stream().allMatch(o -> o.getMensagem() == null));
    }

    @Disabled
    @Test
    void testRequestComErros() throws Exception, T3WCorreiosResponseDefault {
        final var objetosCodigosComErrosList = Set.of("OZ719594203BR", "YYYYYYYYYYYYYY", "OZ719594305BR");
        final var objetosComErrosRastreamentos = CORREIOS.rastrearObjetos(objetosCodigosComErrosList);
        final var objetosCodigosSohErrosList = Set.of("XXXXXXXXXXXXXX", "YYYYYYYYYYYYYY", "ZZZZZZZZZZZZZZ");
        final var objetosSohErrosRastreamentos = CORREIOS.rastrearObjetos(objetosCodigosSohErrosList);
        final var objetosCodigosEmptyList = Set.of("");

        assertNotNull(objetosComErrosRastreamentos);
        assertNotNull(objetosSohErrosRastreamentos);
        assertNotEquals(objetosComErrosRastreamentos.stream().filter(T3WCorreiosSroObjeto::isValido).count(), objetosCodigosComErrosList.size());
        assertTrue(objetosSohErrosRastreamentos.stream().noneMatch(T3WCorreiosSroObjeto::isValido));
        assertThrows(T3WCorreiosResponseDefault.class, () -> CORREIOS.rastrearObjetos(objetosCodigosEmptyList));
    }

    @Disabled
    @Test
    void testRequestPrazoServico() throws Exception, T3WCorreiosResponseDefault {
        final var codigoServico = "03298";
        final var cepOrigem = "88010100";
        final var cepDestino = "88101001";
        final var prazo = CORREIOS.calcularPrazo(codigoServico, cepOrigem, cepDestino);

        assertNotNull(prazo);
        assertEquals(codigoServico, prazo.getCodigo());
        assertTrue(prazo.getPrazoEmDiasUteis() > 0);
        assertTrue(prazo.getDataMaximaParaEntrega().isAfter(LocalDateTime.now()));
    }

    @Disabled
    @Test
    void testRequestErrosPrazoServico() throws Exception {
        final var codigoServico = "03298";
        final var cepOrigem = "88010100";
        final var cepDestino = "88101001";

        assertThrows(T3WCorreiosResponseDefault.class, () -> CORREIOS.calcularPrazo("032X98", cepOrigem, cepDestino));
        assertThrows(T3WCorreiosResponseDefault.class, () -> CORREIOS.calcularPrazo(codigoServico, "123412341234", cepDestino));
        assertThrows(T3WCorreiosResponseDefault.class, () -> CORREIOS.calcularPrazo(codigoServico, cepOrigem, "123412341234"));
        assertThrows(T3WCorreiosResponseDefault.class, () -> CORREIOS.calcularPrazo("123412341234", "123412341234", "123412341234"));
    }

    @Disabled
    @Test
    void testRequestPrecoServico() throws Exception, T3WCorreiosResponseDefault {
        final var preco = CORREIOS.calcularPreco("03220", "88010100", "69999999", 30000, T3WCorreiosFormatoObjeto.PACOTE, 70, 70, 60, 0, new BigDecimal("330.33"), Collections.singleton(T3WCorreiosPrecoServicoAdicional.AVISO_RECEBIMENTO));
        assertNotNull(preco);
        assertTrue(preco.getPrecoFinal().signum() > 0);
    }

    @Disabled
    @Test
    void testRequestPrecoServicoErros() throws Exception {
        assertThrows(T3WCorreiosResponseDefault.class, () -> CORREIOS.calcularPreco("3220", "88010", "3", 1, T3WCorreiosFormatoObjeto.valueOfCodigo("1"), 12341234, 28347, 23847, 238472, new BigDecimal("4.00"), Collections.emptySet()));
    }

    @Disabled
    @Test
    void testRequestPrepostagemInexistente() {
        Assertions.assertThrows(T3WCorreiosResponseDefault.class, () -> CORREIOS.consultarPrepostagem("TJ134711047BR"));
    }

    @Disabled
    @Test
    void testCriarPrepostagem() throws Exception, T3WCorreiosResponseDefault {
        final var remetente = new T3WCorreiosPessoa("teste", new T3WCorreiosEndereco("88101000", "Av. Presidente Kennedy", "568", "CAMPINAS", "SAO JOSE", "SC")).setCpfCnpj(CNPJ);
        final var destinatario = new T3WCorreiosPessoa("teste", new T3WCorreiosEndereco("88101000", "Av. Presidente Kennedy", "568", "CAMPINAS", "SAO JOSE", "SC"));
        final var prepostagem = new T3WCorreiosPrepostagem(remetente, destinatario, "03220", "30", "1", "1");

        // Itens da declaração de conteúdo, passou a ser obrigatório o envio, conforme resposta da API do Correios
        // "NF e declaração de conteúdo: Obrigatório informar a chave da nota fiscal, chave da declaração de conteúdo eletrônica ou os itens da declaração de conteúdo."
        prepostagem.setItensDeclaracaoConteudo(List.of(new T3WCorreiosPrepostagemItemDeclaracaoConteudo("Teste", 1, BigDecimal.valueOf(10), 600)));
        final T3WCorreiosPrepostagem prepostagemEfetivada = CORREIOS.criarPrepostagem(prepostagem);
    }

    @Disabled
    @Test
    void testListarPrepostagens() throws Exception, T3WCorreiosResponseDefault {
        final var prepostagens = CORREIOS.consultarPrepostagens(null, null, null, null, USER_ID, "PREPOSTADO", null, "TODOS", null, null);
        assertNotNull(prepostagens);

        for (final var prepostagem : prepostagens) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(prepostagem));
        }
    }

    @Disabled
    @Test
    void testListarPrepostagensErro() {
        Assertions.assertThrows(T3WCorreiosResponseDefault.class, () -> CORREIOS.consultarPrepostagens(null, null, null, null, "999999", "PREPOSTADO", null, "TODOS", null, null));
    }

    @Disabled
    @Test
    void testCancelarPrepostagem() throws Exception, T3WCorreiosResponseDefault {
        assertNotNull(CORREIOS.cancelarPrePostagem(USER_ID, "XPTO"));
    }

    @Disabled
    @Test
    void testSolicitarRotulo() throws Exception, T3WCorreiosResponseDefault {
        final var rotuloSolicitacao = new T3WCorreiosPrepostagemRequisicaoRotulo();
        rotuloSolicitacao.setIdCorreios(USER_ID)
                .setNumeroCartaoPostagem(CARTAO_POSTAGEM)
                .setCodigosObjeto(List.of("AB033948628BR"))
                .setTipoRotulo("P")
                .setFormatoRotulo("P");

        final var pdfByteArray = CORREIOS.baixarRotulo(CORREIOS.solicitarRotulo(rotuloSolicitacao));
        assertNotNull(pdfByteArray);

        final FileOutputStream fos = new FileOutputStream("/tmp/%s".formatted("teste.pdf"));
        fos.write(pdfByteArray);
        fos.close();
    }

    @Disabled
    @Test
    void testListarContratosAtivos() throws T3WCorreiosResponseDefault, Exception {
        final List<T3WCorreiosContrato> contratos = CORREIOS.consultarContratos(CNPJ, T3WCorreiosContratoStatus.ATIVO, false);
        assertNotNull(contratos);
        for (final var contrato : contratos) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(contrato));
        }
    }

    @Disabled
    @Test
    void testListarServicosDeContrato() throws Exception, T3WCorreiosResponseDefault {
        final var servicos = CORREIOS.consultarServicosByContrato(CNPJ, CONTRATO, CARTAO_POSTAGEM);
        assertNotNull(servicos);

        for (final var servico : servicos) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(servico));
        }
    }

    @Disabled
    @Test
    void testConsultarCategoriaContrato() throws Exception, T3WCorreiosResponseDefault {
        final var contrato = CORREIOS.consultarCategoriaContrato(CNPJ, CONTRATO);
        assertNotNull(contrato);

        System.out.println("Contrato: %s\nCategoria: %s\nNuCombo: %s".formatted(contrato.getNumeroContrato(), contrato.getCategoria(), contrato.getNumeroCombo()));
    }

    @Disabled
    @Test
    void testListarCartoesAtivosDeContrato() throws Exception, T3WCorreiosResponseDefault {
        final var cartoes = CORREIOS.consultarCartoesPostagemByContrato(CNPJ, CONTRATO, T3WCorreiosContratoCartaoStatus.ATIVO, false);
        assertNotNull(cartoes);
        for (final var cartao : cartoes) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(cartao));
        }
    }

    @Disabled
    @Test
    void testListarContratos() throws Exception, T3WCorreiosResponseDefault {
        final var contratos = CORREIOS.consultarContratos(CNPJ);
        assertNotNull(contratos);
        for (final var cartao : contratos) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(cartao));
        }
    }

    @Disabled
    @Test
    void testListarCartoesDeContrato() throws Exception, T3WCorreiosResponseDefault {
        final var cartoes = CORREIOS.consultarCartoesPostagemByContrato(CNPJ, CONTRATO);
        assertNotNull(cartoes);
        for (final var cartao : cartoes) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(cartao));
        }
    }

    @Disabled
    @Test
    void testListarTodosCartoesDeTodosContratos() throws Exception, T3WCorreiosResponseDefault {
        final var contratos = CORREIOS.consultarContratos(CNPJ);
        for (final var contrato : contratos) {
            final var cartoes = CORREIOS.consultarCartoesPostagemByContrato(contrato.getCnpj(), contrato.getNumeroContrato());
            assertNotNull(cartoes);
            for (final var cartao : cartoes) {
                System.out.println(CORREIOS.getObjectMapper().writeValueAsString(cartao));
            }
        }
    }

    @Disabled
    @Test
    void testSolicitarPreviaFaturaAssincrono() throws Exception, T3WCorreiosResponseDefault {
        final T3WCorreiosFaturaProcessoAssincrono processoAssincrono = CORREIOS.solicitarPreviaFatura(T3WCorreiosFaturasTipoPrevia.ANALITICO, CONTRATO, DRSE_CONTRATO, null);

        assertNotNull(processoAssincrono);
        assertNotNull(processoAssincrono.getId());
        assertFalse(processoAssincrono.getId().isBlank());
        System.out.println(processoAssincrono.getId());
    }

    @Disabled
    @Test
    void testVerificarStatusProcessamentoSolicitacaoFatura() throws Exception, T3WCorreiosResponseDefault {
        final T3WCorreiosFaturaProcessoAssincrono processamento = CORREIOS.verificarProcessamentoSolicitacaoFatura("00000000-0000-0000-0000-000000000000");
        assertNotNull(processamento);
    }

    @Disabled
    @Test
    void testBaixarFatura() throws Exception, T3WCorreiosResponseDefault {
        final String faturaCSV = CORREIOS.baixarFatura("00000000-0000-0000-0000-000000000000");
        assertNotNull(faturaCSV);
        assertFalse(faturaCSV.isBlank());
    }

    @Disabled
    @Test
    void testListarFaturasPorPeriodo() throws Exception, T3WCorreiosResponseDefault {
        final List<T3WCorreiosFatura> faturas = CORREIOS.buscaFaturasPorPeriodo(CONTRATO, DRSE_CONTRATO, LocalDate.now().minusMonths(2), LocalDate.now());
        assertNotNull(faturas);
        assertFalse(faturas.isEmpty());
    }

    @Disabled
    @Test
    void testSolicitarExtratoAnaliticoFaturaAssincrono() throws Exception, T3WCorreiosResponseDefault {
        final T3WCorreiosFaturaProcessoAssincrono solicitacao = CORREIOS.solicitarExtratoAnaliticoFatura("0000000", "RE", DRSE_CONTRATO, "001");
        assertNotNull(solicitacao);
    }
}
