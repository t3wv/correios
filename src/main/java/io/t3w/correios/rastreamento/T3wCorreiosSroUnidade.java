package io.t3w.correios.rastreamento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3wCorreiosSroUnidade {

    @JsonProperty("codSro")
    private String codigo;
    @JsonProperty("tipo")
    private String tipo;
    @JsonProperty("endereco")
    private T3WCorreiosSroEndereco endereco;

    public T3wCorreiosSroUnidade() {
    }

    public T3wCorreiosSroUnidade(String codigo, String tipo, T3WCorreiosSroEndereco endereco) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.endereco = endereco;
    }

    public String getCodigo() {
        return codigo;
    }

    public T3wCorreiosSroUnidade setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public T3wCorreiosSroUnidade setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public T3WCorreiosSroEndereco getEndereco() {
        return endereco;
    }

    public T3wCorreiosSroUnidade setEndereco(T3WCorreiosSroEndereco endereco) {
        this.endereco = endereco;
        return this;
    }

    @JsonProperty("endereco")
    private void unpackEndereco(Map<String, String> endereco) {
        this.endereco = new ObjectMapper().convertValue(endereco, T3WCorreiosSroEndereco.class);
    }
}
