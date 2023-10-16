package io.t3w.correios.preco.enums;

public enum T3WCorreiosPrecoTipoBeneficio {
    DP(1,""),
    DF(2,""),
    BP(3,""),
    BF(4,""),
    BC(5,"");

    private final int codigo;
    private final String tipo;

    T3WCorreiosPrecoTipoBeneficio(int codigo, String tipo){
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }
}
