package io.t3w.correios.faturas.enums;

public enum T3WCorreiosFaturasTipoPrevia {
    ANALITICO("Analítica"),
    SINTETICO("Sintética"),;

    private final String descricao;

    T3WCorreiosFaturasTipoPrevia(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
