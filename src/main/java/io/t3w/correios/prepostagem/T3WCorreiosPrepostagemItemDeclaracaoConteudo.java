package io.t3w.correios.prepostagem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class T3WCorreiosPrepostagemItemDeclaracaoConteudo {
    @JsonProperty("conteudo")
    private String conteudo;

    @JsonProperty("quantidade")
    private String quantidade;

    @JsonProperty("valor")
    private String valor;

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo() {
    }

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo(String conteudo, String quantidade, String valor) {
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

    public String getQuantidade() {
        return quantidade;
    }

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo setQuantidade(String quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public String getValor() {
        return valor;
    }

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo setValor(String valor) {
        this.valor = valor;
        return this;
    }
}
