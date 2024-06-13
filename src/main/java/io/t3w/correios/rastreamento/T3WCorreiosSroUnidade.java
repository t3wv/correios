package io.t3w.correios.rastreamento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.t3w.correios.T3WCorreiosEndereco;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosSroUnidade {

    @JsonProperty("codSro")
    private String codigo;
    @JsonProperty("tipo")
    private String tipo;
    @JsonProperty("endereco")
    private T3WCorreiosEndereco endereco;

    public T3WCorreiosSroUnidade() {
    }

    public T3WCorreiosSroUnidade(String codigo, String tipo, T3WCorreiosEndereco endereco) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.endereco = endereco;
    }

    public String getCodigo() {
        return codigo;
    }

    public T3WCorreiosSroUnidade setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public T3WCorreiosSroUnidade setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public T3WCorreiosEndereco getEndereco() {
        return endereco;
    }

    public T3WCorreiosSroUnidade setEndereco(T3WCorreiosEndereco endereco) {
        this.endereco = endereco;
        return this;
    }

    @JsonProperty("endereco")
    private void unpackEndereco(Map<String, String> endereco) {
        this.endereco = new ObjectMapper().convertValue(endereco, T3WCorreiosEndereco.class);
    }
}
