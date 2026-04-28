package io.t3w.correios.prepostagem.enums;

public enum T3WCorreiosPrepostagemStatus {
    PREATENDIDO(1, "Preatendido"),
    PREPOSTADO(2, "Prepostado"),
    POSTADO(3, "Postado"),
    EXPIRADO(4, "Expirado"),
    CANCELADO(5, "Cancelado"),
    ESTORNADO(6, "Estornado");

    private final int codigo;
    private final String descricao;

    T3WCorreiosPrepostagemStatus(final int codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static T3WCorreiosPrepostagemStatus valueOfCodigo(Integer codigo) {
        for (T3WCorreiosPrepostagemStatus status : values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        return null;
    }
}
