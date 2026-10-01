package io.t3w.correios.prepostagem.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum T3WCorreiosPrepostagemTipoDace {
    TERMICA("T", "Térmica"),
    RESUMIDA("R", "Resumida"),
    COMPLETA("C", "Completa");

    private final String codigo;
    private final String descricao;

    T3WCorreiosPrepostagemTipoDace(final String codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static T3WCorreiosPrepostagemTipoDace valueOfCodigo(final String codigo) {
        for (final T3WCorreiosPrepostagemTipoDace tipoDace : values()) {
            if (tipoDace.getCodigo().equalsIgnoreCase(codigo)) {
                return tipoDace;
            }
        }
        return null;
    }
}
