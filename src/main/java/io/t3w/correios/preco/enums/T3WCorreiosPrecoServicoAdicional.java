package io.t3w.correios.preco.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum T3WCorreiosPrecoServicoAdicional {
    AVISO_RECEBIMENTO("001", "AR", "Aviso de recebimento"),
    MAO_PROPRIA("002", "MP", "Mão própria nacional"),
    ENTREGA_VIZINHO("011", "EV", "Entrega no vizinho"),
    ELEICAO("017", "EL", "Eleição"),
    VALOR_DECLARADO("019", "VD", "Valor declarado"),
    AVISO_RECEBIMENTO_ELETRONICO("021", "EAR", "Aviso de recebimento eletrônico"),
    REGISTRO_NACIONAL("025", "RR", "Registro nacional"),
    CARTA_REGISTRADA("035", "VD", "Carta registrada com valor declarado"),
    GRANDES_FORMATOS("057", "GF", "Grandes formatos (GF)"),
    VALOR_DECLARADO_NACIONAL_STANDARD("064", "VD", "Valor declarado nacional standard"),
    VALOR_DECLARADO_MINI_ENVIOS("065", "VD", "Valor declarado para o correios mini envios"),
    VALOR_DECLARADO_RFID_EXPRESSO("075", "VD", "Valor declarado RFID expresso"),
    VALOR_DECLARADO_RFID_STANDARD("076", "VD", "Valor declarado RFID standard");

    private final String codigo;
    private final String sigla;
    private final String desricao;

    T3WCorreiosPrecoServicoAdicional(String codigo, String sigla, String desricao) {
        this.codigo = codigo;
        this.sigla = sigla;
        this.desricao = desricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public String getDesricao() {
        return desricao;
    }

    @JsonCreator
    public static T3WCorreiosPrecoServicoAdicional valueOfCodigo(@JsonProperty("coServAdicional") String codigo) {
        for (T3WCorreiosPrecoServicoAdicional servicoAdicional : values()) {
            if(servicoAdicional.getCodigo().equalsIgnoreCase(codigo)){
                return servicoAdicional;
            }
        }
        return null;
    }
}


