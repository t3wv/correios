package io.t3w.correios;

import io.t3w.correios.contratos.T3WCorreiosContrato;
import io.t3w.correios.contratos.enums.T3WCorreiosContratoCartaoStatus;
import io.t3w.correios.contratos.enums.T3WCorreiosContratoStatus;
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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class T3WCorreiosTest {

    private static String CNPJ, USER_ID, API_TOKEN, CARTAO_POSTAGEM, CONTRATO;
    private static T3WCorreios CORREIOS;

    @BeforeAll
    public static void preparaTestes() {
        USER_ID = System.getenv("CORREIOS_USER_ID");
        API_TOKEN = System.getenv("CORREIOS_API_TOKEN");
        CARTAO_POSTAGEM = System.getenv("CORREIOS_CARTAO_POSTAGEM");
        CNPJ = System.getenv("CORREIOS_CNPJ");
        CONTRATO = System.getenv("CORREIOS_CONTRATO");
        CORREIOS = new T3WCorreios(USER_ID, API_TOKEN, CARTAO_POSTAGEM, false);
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

    @Test
    void testRequestPrecoServico() throws Exception, T3WCorreiosResponseDefault {
        final var preco = CORREIOS.calcularPreco("03220", "88010100", "69999999", 30000, T3WCorreiosFormatoObjeto.PACOTE, 70, 70, 60, 0, new BigDecimal("330.33"), Collections.singleton(T3WCorreiosPrecoServicoAdicional.AVISO_RECEBIMENTO));
        assertNotNull(preco);
        assertTrue(preco.getPrecoFinal().signum() > 0);
    }

    @Test
    void testRequestPrecoServicoErros() throws Exception {
        assertThrows(T3WCorreiosResponseDefault.class, () -> CORREIOS.calcularPreco("3220", "88010", "3", 1, T3WCorreiosFormatoObjeto.valueOfCodigo("1"), 12341234, 28347, 23847, 238472, new BigDecimal("4.00"), Collections.emptySet()));
    }

    @Test
    void testRequestPrepostagemInexistente() {
        Assertions.assertThrows(T3WCorreiosResponseDefault.class, () -> CORREIOS.consultarPrepostagem("TJ134711047BR"));
    }

    @Test
    void testCriarPrepostagem() throws Exception, T3WCorreiosResponseDefault {
        final var remetente = new T3WCorreiosPessoa("teste", new T3WCorreiosEndereco("88101000", "Av. Presidente Kennedy", "568", "CAMPINAS", "SAO JOSE", "SC")).setCpfCnpj(CNPJ);
        final var destinatario = new T3WCorreiosPessoa("teste", new T3WCorreiosEndereco("88101000", "Av. Presidente Kennedy", "568", "CAMPINAS", "SAO JOSE", "SC"));
        final var prepostagem = new T3WCorreiosPrepostagem(remetente, destinatario, "03220", "30", "1", "1");
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

    @Test
    void testListarContratosAtivos() throws T3WCorreiosResponseDefault, Exception {
        final List<T3WCorreiosContrato> contratos = CORREIOS.consultarContratos(CNPJ, T3WCorreiosContratoStatus.ATIVO, false);
        assertNotNull(contratos);
        for (final var contrato : contratos) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(contrato));
        }
    }

    @Test
    void testListarServicosDeContrato() throws Exception, T3WCorreiosResponseDefault {
        final var servicos = CORREIOS.consultarServicosByContrato(CNPJ, CONTRATO, CARTAO_POSTAGEM);
        assertNotNull(servicos);

        for (final var servico : servicos) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(servico));
        }
    }

    @Test
    void testConsultarCategoriaContrato() throws Exception, T3WCorreiosResponseDefault {
        final var contrato = CORREIOS.consultarCategoriaContrato(CNPJ, CONTRATO);
        assertNotNull(contrato);

        System.out.println("Contrato: %s\nCategoria: %s\nNuCombo: %s".formatted(contrato.getNumeroContrato(), contrato.getCategoria(), contrato.getNumeroCombo()));
    }

    @Test
    void testListarCartoesAtivosDeContrato() throws Exception, T3WCorreiosResponseDefault {
        final var cartoes = CORREIOS.consultarCartoesPostagemByContrato(CNPJ, CONTRATO, T3WCorreiosContratoCartaoStatus.ATIVO, false);
        assertNotNull(cartoes);
        for (final var cartao : cartoes) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(cartao));
        }
    }

    @Test
    void testListarContratos() throws Exception, T3WCorreiosResponseDefault {
        final var contratos = CORREIOS.consultarContratos(CNPJ);
        assertNotNull(contratos);
        for (final var cartao : contratos) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(cartao));
        }
    }

    @Test
    void testListarCartoesDeContrato() throws Exception, T3WCorreiosResponseDefault {
        final var cartoes = CORREIOS.consultarCartoesPostagemByContrato(CNPJ, CONTRATO);
        assertNotNull(cartoes);
        for (final var cartao : cartoes) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(cartao));
        }
    }

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
}
