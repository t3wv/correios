package io.t3w.correios.prepostagem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Representa um item da declaração de conteúdo exigida na pré-postagem dos Correios.
 *
 * <p><strong>IMPORTANTE:</strong> A partir de 2025, os Correios exigem que cada pré-postagem
 * informe obrigatoriamente um dos seguintes:
 * <ol>
 *   <li>Chave da nota fiscal (NF-e)</li>
 *   <li>Chave da declaração de conteúdo eletrônica</li>
 *   <li>Ou os <strong>itens detalhados</strong> da declaração de conteúdo: {@code conteudo}, {@code quantidade}, {@code valor} e {@code peso}</li>
 * </ol>
 *
 * <p>Esta classe representa a terceira opção: o detalhamento dos itens da declaração de conteúdo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrepostagemItemDeclaracaoConteudo {

    /**
     * Descrição do conteúdo enviado (ex: "Materiais promocionais", "HDs").
     */
    @JsonProperty("conteudo")
    private String conteudo;

    /**
     * Quantidade de unidades do item.
     */
    @JsonProperty("quantidade")
    private int quantidade;

    /**
     * Valor total dos itens.
     */
    @JsonProperty("valor")
    private BigDecimal valor;

    /**
     * Peso total dos itens, em gramas.
     */
    @JsonProperty("peso")
    private BigDecimal peso;

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo() {
    }

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo(String conteudo, int quantidade, BigDecimal valor, BigDecimal peso) {
        this.conteudo = conteudo;
        this.quantidade = quantidade;
        this.valor = valor;
        this.peso = peso;
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

    public BigDecimal getPeso() {
        return peso;
    }

    public T3WCorreiosPrepostagemItemDeclaracaoConteudo setPeso(BigDecimal peso) {
        this.peso = peso;
        return this;
    }
}
