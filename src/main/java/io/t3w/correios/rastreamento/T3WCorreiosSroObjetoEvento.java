package io.t3w.correios.rastreamento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosSroObjetoEvento {

    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("tipo")
    private String tipo;

    @JsonProperty("dtHrCriado")
    private LocalDateTime data;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("unidade")
    private T3wCorreiosSroUnidade unidade;

    public T3WCorreiosSroObjetoEvento(){}

    public T3WCorreiosSroObjetoEvento(String codigo, String tipo, LocalDateTime data, String descricao, T3wCorreiosSroUnidade unidade) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.data = data;
        this.descricao = descricao;
        this.unidade = unidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public T3WCorreiosSroObjetoEvento setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public T3WCorreiosSroObjetoEvento setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public LocalDateTime getData() {
        return data;
    }

    public T3WCorreiosSroObjetoEvento setData(LocalDateTime data) {
        this.data = data;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public T3WCorreiosSroObjetoEvento setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public T3wCorreiosSroUnidade getUnidade() {
        return unidade;
    }

    public T3WCorreiosSroObjetoEvento setUnidade(T3wCorreiosSroUnidade unidade) {
        this.unidade = unidade;
        return this;
    }
}
