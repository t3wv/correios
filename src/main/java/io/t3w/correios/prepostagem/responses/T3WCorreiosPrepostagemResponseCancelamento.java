package io.t3w.correios.prepostagem.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class T3WCorreiosPrepostagemResponseCancelamento {
    @JsonProperty("resultadoCancelamento")
    private String resultadoCancelamento = null;

    @JsonProperty("mensagem")
    private String mensagem = null;

    @JsonProperty("idRecibo")
    private String idRecibo = null;

    public T3WCorreiosPrepostagemResponseCancelamento() {
    }

    public T3WCorreiosPrepostagemResponseCancelamento(String resultadoCancelamento, String mensagem, String idRecibo) {
        this.resultadoCancelamento = resultadoCancelamento;
        this.mensagem = mensagem;
        this.idRecibo = idRecibo;
    }

    public String getResultadoCancelamento() {
        return resultadoCancelamento;
    }

    public T3WCorreiosPrepostagemResponseCancelamento setResultadoCancelamento(String resultadoCancelamento) {
        this.resultadoCancelamento = resultadoCancelamento;
        return this;
    }

    public String getMensagem() {
        return mensagem;
    }

    public T3WCorreiosPrepostagemResponseCancelamento setMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

    public String getIdRecibo() {
        return idRecibo;
    }

    public T3WCorreiosPrepostagemResponseCancelamento setIdRecibo(String idRecibo) {
        this.idRecibo = idRecibo;
        return this;
    }

    @Override
    public String toString() {
        return "T3WCorreiosPrepostagemResponseCancelamento{" +
               "resultadoCancelamento='" + resultadoCancelamento + '\'' +
               ", mensagem='" + mensagem + '\'' +
               ", idRecibo='" + idRecibo + '\'' +
               '}';
    }
}
