package io.t3w.correios.preco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrecoTaxaExtra {
    @JsonProperty("codigo")
    private String codigo;
    @JsonProperty("tipo")
    private String tipo;
    @JsonProperty("vlTaxa")
    private double valor;

    public T3WCorreiosPrecoTaxaExtra() {}

    public T3WCorreiosPrecoTaxaExtra(String codigo, String tipo, double valor) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public T3WCorreiosPrecoTaxaExtra setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public T3WCorreiosPrecoTaxaExtra setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public double getValor() {
        return valor;
    }

    public T3WCorreiosPrecoTaxaExtra setValor(double valor) {
        this.valor = valor;
        return this;
    }
}
