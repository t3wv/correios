package io.t3w.correios.contratos.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum T3WCorreiosContratoCartaoStatus {

    @JsonProperty("ATIVO")
    ATIVO("ATIVO"),
    @JsonProperty("CANCELADO")
    CANCELADO("CANCELADO"),
    @JsonProperty("SUSPENSO")
    SUSPENSO("SUSPENSO"),
    @JsonProperty("IRREGULAR")
    EM_DESENVOLVIMENTO("IRREGULAR");

    private String descricao;

    T3WCorreiosContratoCartaoStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public String toString() {
        return String.valueOf(descricao);
    }
}
