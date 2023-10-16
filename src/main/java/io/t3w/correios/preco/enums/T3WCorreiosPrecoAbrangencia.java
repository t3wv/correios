package io.t3w.correios.preco.enums;

public enum T3WCorreiosPrecoAbrangencia {
    O(1,"Oferta"),
    N(2,"Negociação");

    private final int codigo;
    private final String descricao;

    T3WCorreiosPrecoAbrangencia(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
