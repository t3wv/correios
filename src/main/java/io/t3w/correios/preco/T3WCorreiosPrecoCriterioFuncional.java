package io.t3w.correios.preco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrecoCriterioFuncional {

    @JsonProperty("nuCriterio")
    private long numero;
    @JsonProperty("noCriterio")
    private String nome;
    @JsonProperty("atendido")
    private boolean atendido;
    @JsonProperty("peNegociado")
    private double percentualNegociado;
    @JsonProperty("peConcedido")
    private double percentualConcedido;

    public T3WCorreiosPrecoCriterioFuncional() {}

    public T3WCorreiosPrecoCriterioFuncional(long numero, String nome, boolean atendido, double percentualNegociado, double percentualConcedido) {
        this.numero = numero;
        this.nome = nome;
        this.atendido = atendido;
        this.percentualNegociado = percentualNegociado;
        this.percentualConcedido = percentualConcedido;
    }

    public long getNumero() {
        return numero;
    }

    public T3WCorreiosPrecoCriterioFuncional setNumero(long numero) {
        this.numero = numero;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public T3WCorreiosPrecoCriterioFuncional setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public T3WCorreiosPrecoCriterioFuncional setAtendido(boolean atendido) {
        this.atendido = atendido;
        return this;
    }

    public double getPercentualNegociado() {
        return percentualNegociado;
    }

    public T3WCorreiosPrecoCriterioFuncional setPercentualNegociado(double percentualNegociado) {
        this.percentualNegociado = percentualNegociado;
        return this;
    }

    public double getPercentualConcedido() {
        return percentualConcedido;
    }

    public T3WCorreiosPrecoCriterioFuncional setPercentualConcedido(double percentualConcedido) {
        this.percentualConcedido = percentualConcedido;
        return this;
    }
}
