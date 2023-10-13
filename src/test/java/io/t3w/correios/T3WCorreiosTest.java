package io.t3w.correios;

import io.t3w.correios.rastreamento.T3WCorreiosSroObjeto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.naming.directory.InvalidAttributesException;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class T3WCorreiosTest {

    private static T3WCorreios CORREIOS;

    @BeforeAll
    public static void preparaTestes() {
        final var userId = System.getenv("CORREIOS_USER_ID");
        final var apiToken = System.getenv("CORREIOS_API_TOKEN");
        final var cartaoPostagem = System.getenv("CORREIOS_CARTAO_POSTAGEM");
        CORREIOS = new T3WCorreios(userId, apiToken, cartaoPostagem);
    }

    @Test
    void testRequestBearerToken() throws Exception {
        final var requestToken = CORREIOS.requestBearerToken();
        assertNotNull(requestToken);
        assertFalse(requestToken.getToken().isEmpty());
    }

    @Test
    void testRastreamentoObjetos() throws Exception {
        final var objetosCodigosList = Set.of("OZ719611391BR");
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
        assertTrue(objetosComErrosRastreamentos.stream().filter(T3WCorreiosSroObjeto::isValido).count() != objetosCodigosComErrosList.size());
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
        final var preco = CORREIOS.calcularPreco("03220","88010100","88101001",2.35,2,10,10,10,10,true,33.33,true);
        assertNotNull(preco);
        assertTrue(preco.getPrecoFinal() > 0);
    }

    @Test
    void testRequestPrecoServicoErros() throws Exception {
        assertThrows(Exception.class, () -> CORREIOS.calcularPreco("3220", "88010", "3", 1, 33, 12341234, 28347, 23847, 238472, true, 4, false));
    }
}
