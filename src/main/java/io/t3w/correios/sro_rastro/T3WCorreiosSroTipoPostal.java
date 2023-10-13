package io.t3w.correios.sro_rastro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosSroTipoPostal {

    @JsonProperty("sigla")
    private String sigla;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("categoria")
    private String categoria;

    public T3WCorreiosSroTipoPostal() {
    }

    public T3WCorreiosSroTipoPostal(String sigla, String descricao, String categoria) {
        this.sigla = sigla;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public String getSigla() {
        return sigla;
    }

    public T3WCorreiosSroTipoPostal setSigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public T3WCorreiosSroTipoPostal setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getCategoria() {
        return categoria;
    }

    public T3WCorreiosSroTipoPostal setCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }
}
