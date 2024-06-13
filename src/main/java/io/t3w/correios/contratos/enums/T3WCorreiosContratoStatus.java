package io.t3w.correios.contratos.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum T3WCorreiosContratoStatus {

    @JsonProperty("ATIVO")
    ATIVO("ATIVO"),
    @JsonProperty("CANCELADO")
    CANCELADO("CANCELADO"),
    @JsonProperty("SUSPENSO")
    SUSPENSO("SUSPENSO"),
    @JsonProperty("EM_DESENVOLVIMENTO")
    EM_DESENVOLVIMENTO("EM_DESENVOLVIMENTO");

    private String descricao;

    T3WCorreiosContratoStatus(String descricao) {
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
