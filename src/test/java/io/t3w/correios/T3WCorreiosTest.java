package io.t3w.correios;

import io.t3w.correios.contratos.T3WCorreiosContrato;
import io.t3w.correios.prepostagem.responses.T3WCorreiosPrepostagemResponseCancelamento;
import io.t3w.correios.responses.T3WCorreiosResponseDefault;
import io.t3w.correios.preco.enums.T3WCorreiosPrecoServicoAdicional;
import io.t3w.correios.prepostagem.T3WCorreiosPrepostagem;
import io.t3w.correios.rastreamento.T3WCorreiosSroObjeto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class T3WCorreiosTest {

    private static T3WCorreios CORREIOS;

    @BeforeAll
    public static void preparaTestes() {
        final var userId = System.getenv("CORREIOS_USER_ID");
        final var apiToken = System.getenv("CORREIOS_API_TOKEN");
        final var cartaoPostagem = System.getenv("CORREIOS_CARTAO_POSTAGEM");
        CORREIOS = new T3WCorreios(userId, apiToken, cartaoPostagem);
    }

//    @Test
//    void testRequestBearerToken() throws Exception {
//        final var requestToken = CORREIOS.requestBearerToken();
//        assertNotNull(requestToken);
//        assertFalse(requestToken.getToken().isEmpty());
//    }

    @Test
    void testRastreamentoObjetos() throws Exception {
        final var objetosCodigosList = Set.of("TJ134711095BR");
        final var objetosRastreamentos = CORREIOS.rastrearObjetos(objetosCodigosList);
        assertNotNull(objetosRastreamentos);
        assertEquals(objetosRastreamentos.size(), objetosCodigosList.size());
        assertTrue(objetosRastreamentos.stream().allMatch(o -> o.getMensagem() == null));
    }

    @Test
    void testRequestComErros() throws Exception {
        final var objetosCodigosComErrosList = Set.of("OZ719594203BR", "YYYYYYYYYYYYYY", "OZ719594305BR");
        final var objetosComErrosRastreamentos = CORREIOS.rastrearObjetos(objetosCodigosComErrosList);
        final var objetosCodigosSohErrosList = Set.of("XXXXXXXXXXXXXX", "YYYYYYYYYYYYYY", "ZZZZZZZZZZZZZZ");
        final var objetosSohErrosRastreamentos = CORREIOS.rastrearObjetos(objetosCodigosSohErrosList);
        final var objetosCodigosEmptyList = Set.of("");

        assertNotNull(objetosComErrosRastreamentos);
        assertNotNull(objetosSohErrosRastreamentos);
        assertNotEquals(objetosComErrosRastreamentos.stream().filter(T3WCorreiosSroObjeto::isValido).count(), objetosCodigosComErrosList.size());
        assertTrue(objetosSohErrosRastreamentos.stream().noneMatch(T3WCorreiosSroObjeto::isValido));
        assertThrows(Exception.class, () -> CORREIOS.rastrearObjetos(objetosCodigosEmptyList));
    }

    @Test
    void testRequestPrazoServico() throws Exception {
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

        assertThrows(Exception.class, () -> CORREIOS.calcularPrazo("032X98", cepOrigem, cepDestino));
        assertThrows(Exception.class, () -> CORREIOS.calcularPrazo(codigoServico, "123412341234", cepDestino));
        assertThrows(Exception.class, () -> CORREIOS.calcularPrazo(codigoServico, cepOrigem, "123412341234"));
        assertThrows(Exception.class, () -> CORREIOS.calcularPrazo("123412341234", "123412341234", "123412341234"));
    }

    @Test
    void testRequestPrecoServico() throws Exception {
        final var preco = CORREIOS.calcularPreco("03220", "88010100", "69999999", 30000, T3WCorreiosFormatoObjeto.PACOTE, 70, 70, 60, 0, new BigDecimal("330.33"), Collections.singleton(T3WCorreiosPrecoServicoAdicional.AVISO_RECEBIMENTO));
        assertNotNull(preco);
        assertTrue(preco.getPrecoFinal().signum() > 0);
    }

    @Test
    void testRequestPrecoServicoErros() throws Exception {
        assertThrows(Exception.class, () -> CORREIOS.calcularPreco("3220", "88010", "3", 1, T3WCorreiosFormatoObjeto.valueOfCodigo("1"), 12341234, 28347, 23847, 238472, new BigDecimal("4.00"), Collections.emptySet()));
    }

    @Test
    void testRequestPrepostagemInexistente() {
            Assertions.assertThrows(T3WCorreiosResponseDefault.class, ()-> CORREIOS.consultarPrepostagem("TJ134711047BR"));
    }

    @Test
    void testCriarPrepostagem() throws Exception, T3WCorreiosResponseDefault {
        final var remetente = new T3WCorreiosPessoa("teste", new T3WCorreiosEndereco("88101000","Av. Presidente Kennedy","568", "CAMPINAS", "SAO JOSE", "SC")).setCpfCnpj(System.getenv("CORREIOS_TEST_CNPJ"));
        final var destinatario = new T3WCorreiosPessoa("teste", new T3WCorreiosEndereco("88101000","Av. Presidente Kennedy","568", "CAMPINAS", "SAO JOSE", "SC"));
        final var prepostagem = new T3WCorreiosPrepostagem(remetente, destinatario, "03220", "30", "1", "1");
        final T3WCorreiosPrepostagem prepostagemEfetivada = CORREIOS.criarPrepostagem(prepostagem);
    }

    @Test
    void testListarPrepostagens() throws Exception, T3WCorreiosResponseDefault {
        final var prepostagens = CORREIOS.consultarPrepostagens(null, null, null, null, System.getenv("CORREIOS_TEST_IDCORREIOS"), "PREPOSTADO", null, "TODOS", null, null);
        assertNotNull(prepostagens);

        for (final var prepostagem : prepostagens) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(prepostagem));
        }
    }

    @Test
    void testListarPrepostagensErro() {
        Assertions.assertThrows(T3WCorreiosResponseDefault.class, ()-> CORREIOS.consultarPrepostagens(null, null, null, null, "999999", "PREPOSTADO", null, "TODOS", null, null));
    }

    @Test
    void testCancelarPrepostagem() throws Exception, T3WCorreiosResponseDefault {
        assertNotNull(CORREIOS.cancelarPrePostagem(System.getenv("CORREIOS_TEST_IDCORREIOS"), "XPTO"));
    }

    @Test
    void testListarContratos() throws T3WCorreiosResponseDefault, Exception {
        final List<T3WCorreiosContrato> contratos = CORREIOS.consultarContratos(System.getenv("CORREIOS_TEST_CNPJ"), null, false);  assertNotNull(contratos);
        for (final var contrato : contratos) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(contrato));
        }
    }

    @Test
    void testListarServicosDeContrato() throws Exception, T3WCorreiosResponseDefault {
        final var servicos = CORREIOS.consultarServicosByContrato(System.getenv("CORREIOS_TEST_CNPJ"),System.getenv("CORREIOS_TEST_NUMERO_CONTRATO"), System.getenv("CORREIOS_TEST_NUMERO_CARTAOPOSTAGEM"));
        assertNotNull(servicos);

        for (final var servico : servicos) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(servico));
        }
    }

    @Test
    void testConsultarCategoriaContrato() throws Exception, T3WCorreiosResponseDefault {
        final var contrato = CORREIOS.consultarCategoriaContrato(System.getenv("CORREIOS_TEST_CNPJ"), System.getenv("CORREIOS_TEST_NUMERO_CONTRATO"));
        assertNotNull(contrato);

        System.out.println("Contrato: %s\nCategoria: %s\nNuCombo: %s".formatted(contrato.getNuContrato(), contrato.getCategoria(), contrato.getNuCombo()));
    }

    @Test
    void testListarCartoesDeContrato() throws Exception, T3WCorreiosResponseDefault {
        final var cartoes = CORREIOS.consultarCartoesPostagemByContrato(System.getenv("CORREIOS_TEST_CNPJ"), System.getenv("CORREIOS_TEST_NUMERO_CONTRATO"), null, false);
        assertNotNull(cartoes);

        for (final var cartao : cartoes) {
            System.out.println(CORREIOS.getObjectMapper().writeValueAsString(cartao));
        }
    }

}
