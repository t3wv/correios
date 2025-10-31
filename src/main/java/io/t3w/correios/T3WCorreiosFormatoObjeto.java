package io.t3w.correios;


public enum T3WCorreiosFormatoObjeto {

    ENVELOPE("1", "Envelope"),
    PACOTE("2", "Pacote"),
    ROLO("3", "Rolo");

    private final String codigo;
    private final String descricao;

    T3WCorreiosFormatoObjeto(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static T3WCorreiosFormatoObjeto valueOfCodigo(String codigo) {
        for (T3WCorreiosFormatoObjeto tipoObjeto : values()) {
            if (tipoObjeto.getCodigo().equalsIgnoreCase(codigo)) {
                return tipoObjeto;
            }
        }
        return null;
    }
}
