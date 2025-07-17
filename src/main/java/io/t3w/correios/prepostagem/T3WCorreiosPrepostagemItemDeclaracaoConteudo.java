package io.t3w.correios.prepostagem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)

public class T3WCorreiosPrepostagemItemDeclaracaoConteudo {
    @JsonProperty("conteudo")
    private String conteudo;

    @JsonProperty("quantidade")
    private int quantidade;

    @JsonProperty("valor")
    private BigDecimal valor;

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo() {
    }

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo(String conteudo, int quantidade, BigDecimal valor) {
        this.conteudo = conteudo;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo setConteudo(String conteudo) {
        this.conteudo = conteudo;
        return this;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }
}
