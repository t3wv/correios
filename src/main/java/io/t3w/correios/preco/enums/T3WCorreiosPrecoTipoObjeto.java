package io.t3w.correios.preco.enums;


public enum T3WCorreiosPrecoTipoObjeto {

//    Tipo do objeto da postagem: 1 - Envelope, 2 - Pacote; 3 - Rolo.

    ENVELOPE("1","Envelope"),

    PACOTE("2","Pacote"),

    ROLO("3","Rolo");

    private final String codigo;
    private final String descricao;


    T3WCorreiosPrecoTipoObjeto(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static T3WCorreiosPrecoTipoObjeto valueOfCodigo(String codigo) {
        for (T3WCorreiosPrecoTipoObjeto tipoObjeto : values()) {
            if(tipoObjeto.getCodigo().equalsIgnoreCase(codigo)){
                return tipoObjeto;
            }
        }
        return null;
    }
}
