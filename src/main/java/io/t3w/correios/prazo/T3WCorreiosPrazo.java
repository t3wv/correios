package io.t3w.correios.prazo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrazo {

    @JsonProperty("coProduto")
    private String codigo;

    @JsonProperty("nuRequisicao")
    private String numeroRequisicao;

    @JsonProperty("prazoEntrega")
    private int prazoEmDiasUteis;

    @JsonProperty("dataMaxima")
    private LocalDateTime dataMaximaParaEntrega;

    @JsonProperty("txErro")
    private String txErro;

    @JsonProperty("entregaDomiciliar")
    private boolean entregaDomiciliar;

    @JsonProperty("entregaSabado")
    private boolean entregaSabado;

    @JsonProperty("msgPrazo")
    private String msg;

    public T3WCorreiosPrazo() {
    }

    public T3WCorreiosPrazo(String codigo, String numeroRequisicao, int prazoEmDiasUteis, LocalDateTime dataMaximaParaEntrega, String txErro, boolean entregaDomiciliar, boolean entregaSabado, String msg) {
        this.codigo = codigo;
        this.numeroRequisicao = numeroRequisicao;
        this.prazoEmDiasUteis = prazoEmDiasUteis;
        this.dataMaximaParaEntrega = dataMaximaParaEntrega;
        this.txErro = txErro;
        this.entregaDomiciliar = entregaDomiciliar;
        this.entregaSabado = entregaSabado;
        this.msg = msg;
    }

    public String getCodigo() {
        return codigo;
    }

    public T3WCorreiosPrazo setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getNumeroRequisicao() {
        return numeroRequisicao;
    }

    public T3WCorreiosPrazo setNumeroRequisicao(String numeroRequisicao) {
        this.numeroRequisicao = numeroRequisicao;
        return this;
    }

    public int getPrazoEmDiasUteis() {
        return prazoEmDiasUteis;
    }

    public T3WCorreiosPrazo setPrazoEmDiasUteis(int prazoEmDiasUteis) {
        this.prazoEmDiasUteis = prazoEmDiasUteis;
        return this;
    }

    public LocalDateTime getDataMaximaParaEntrega() {
        return dataMaximaParaEntrega;
    }

    public T3WCorreiosPrazo setDataMaximaParaEntrega(LocalDateTime dataMaximaParaEntrega) {
        this.dataMaximaParaEntrega = dataMaximaParaEntrega;
        return this;
    }

    public String getTxErro() {
        return txErro;
    }

    public T3WCorreiosPrazo setTxErro(String txErro) {
        this.txErro = txErro;
        return this;
    }

    public boolean isEntregaDomiciliar() {
        return entregaDomiciliar;
    }

    public T3WCorreiosPrazo setEntregaDomiciliar(boolean entregaDomiciliar) {
        this.entregaDomiciliar = entregaDomiciliar;
        return this;
    }

    @JsonProperty("entregaDomiciliar")
    private void setEntregaDomiciliar (String entregaDomiciliar){
        this.setEntregaDomiciliar(entregaDomiciliar.equals("S"));
    }

    public boolean isEntregaSabado() {
        return entregaSabado;
    }

    public T3WCorreiosPrazo setEntregaSabado(boolean entregaSabado) {
        this.entregaSabado = entregaSabado;
        return this;
    }

    @JsonProperty("entregaSabado")
    private void setEntregaSabado (String entregaSabado){
        this.setEntregaSabado(entregaSabado.equals("S"));
    }

    public String getMsg() {
        return msg;
    }

    public T3WCorreiosPrazo setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
