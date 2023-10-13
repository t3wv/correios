package io.t3w.correios.preco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrecoServicoAdicional {

    @JsonProperty("coServAdicional")
    private String codigo;
    @JsonProperty("tpServAdicional")
    private String tipo;
    @JsonProperty("pcServicoAdicional")
    private double preco;

    public T3WCorreiosPrecoServicoAdicional() {}

    public T3WCorreiosPrecoServicoAdicional(String codigo, String tipo, double preco) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public T3WCorreiosPrecoServicoAdicional setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public T3WCorreiosPrecoServicoAdicional setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public double getPreco() {
        return preco;
    }

    public T3WCorreiosPrecoServicoAdicional setPreco(double preco) {
        this.preco = preco;
        return this;
    }
}
