package io.t3w.correios.preco.enums;

public enum TipoBeneficio {
    DP(1,""),
    DF(2,""),
    BP(3,""),
    BF(4,""),
    BC(5,"");

    private final int codigo;
    private final String tipo;

    TipoBeneficio(int codigo, String tipo){
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
