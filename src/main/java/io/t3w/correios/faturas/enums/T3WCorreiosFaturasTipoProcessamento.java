package io.t3w.correios.faturas.enums;

public enum T3WCorreiosFaturasTipoProcessamento {
    EXTRATO_ANALITICO("Extrato analítico"),
    PREVIA_SINTETICO("Prévia sintética"),
    PREVIA_ANALITICO("Prévia analítica"),
    EXTRATO_ANALITICO_PDF("Extrato analítico PDF");

    private final String descricao;

    T3WCorreiosFaturasTipoProcessamento(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
