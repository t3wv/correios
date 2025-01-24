package io.t3w.correios.faturas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.faturas.enums.T3WCorreiosFaturasProcessamentoStatus;
import io.t3w.correios.faturas.enums.T3WCorreiosFaturasTipoProcessamento;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosFaturaProcessoAssincrono {

    @JsonProperty("id")
    private String id;

    @JsonProperty("tipoProcessamento")
    private T3WCorreiosFaturasTipoProcessamento tipoProcessamento;

    @JsonProperty("status")
    private T3WCorreiosFaturasProcessamentoStatus status;

    @JsonProperty("dataSolicitacao")
    private String dataSolicitacao;

    @JsonProperty("dataInicioProcessamento")
    private String dataInicioProcessamento;

    @JsonProperty("dataFimProcessamento")
    private String dataFimProcessamento;

    @JsonProperty("nomeArquivo")
    private String nomeArquivo;

    @JsonProperty("tipoArquivo")
    private String tipoArquivo;

    @JsonProperty("erro")
    private String erro;

    public T3WCorreiosFaturaProcessoAssincrono() {
    }

    public String getId() {
        return id;
    }

    public T3WCorreiosFaturaProcessoAssincrono setId(String id) {
        this.id = id;
        return this;
    }

    public T3WCorreiosFaturasTipoProcessamento getTipoProcessamento() {
        return tipoProcessamento;
    }

    public T3WCorreiosFaturaProcessoAssincrono setTipoProcessamento(T3WCorreiosFaturasTipoProcessamento tipoProcessamento) {
        this.tipoProcessamento = tipoProcessamento;
        return this;
    }

    public T3WCorreiosFaturasProcessamentoStatus getStatus() {
        return status;
    }

    public T3WCorreiosFaturaProcessoAssincrono setStatus(T3WCorreiosFaturasProcessamentoStatus status) {
        this.status = status;
        return this;
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public T3WCorreiosFaturaProcessoAssincrono setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
        return this;
    }

    public String getDataInicioProcessamento() {
        return dataInicioProcessamento;
    }

    public T3WCorreiosFaturaProcessoAssincrono setDataInicioProcessamento(String dataInicioProcessamento) {
        this.dataInicioProcessamento = dataInicioProcessamento;
        return this;
    }

    public String getDataFimProcessamento() {
        return dataFimProcessamento;
    }

    public T3WCorreiosFaturaProcessoAssincrono setDataFimProcessamento(String dataFimProcessamento) {
        this.dataFimProcessamento = dataFimProcessamento;
        return this;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public T3WCorreiosFaturaProcessoAssincrono setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        return this;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public T3WCorreiosFaturaProcessoAssincrono setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
        return this;
    }

    public String getErro() {
        return erro;
    }

    public T3WCorreiosFaturaProcessoAssincrono setErro(String erro) {
        this.erro = erro;
        return this;
    }

}
