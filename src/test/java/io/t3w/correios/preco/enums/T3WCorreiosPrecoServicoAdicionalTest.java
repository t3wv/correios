package io.t3w.correios.preco.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class T3WCorreiosPrecoServicoAdicionalTest {

    @Test
    void valueOfCodigo() {
        Assertions.assertNull(T3WCorreiosPrecoServicoAdicional.valueOfCodigo("XXX"));
        Assertions.assertEquals(T3WCorreiosPrecoServicoAdicional.AVISO_RECEBIMENTO, T3WCorreiosPrecoServicoAdicional.valueOfCodigo("001"));
    }
}