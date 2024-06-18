package io.t3w.correios.contratos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.contratos.enums.T3WCorreiosContratoCartaoStatus;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosContratoCartaoPostagem {
    @JsonProperty("cnpj")
    private String cnpj;

    @JsonProperty("nuContrato")
    private String numeroContrato;

    @JsonProperty("nuSe")
    private Long numeroSe;

    @JsonProperty("nuCartaoPostagem")
    private String numeroCartaoPostagem;

    @JsonProperty("cnpjCartao")
    private String cnpjCartao;

    @JsonProperty("dtInicioVigencia")
    private LocalDateTime dataInicioVigencia;

    @JsonProperty("dtFimVigencia")
    private LocalDateTime dataFimVigencia;

    @JsonProperty("status")
    private T3WCorreiosContratoCartaoStatus status;

    public T3WCorreiosContratoCartaoPostagem() {
    }

    public T3WCorreiosContratoCartaoPostagem(String cnpj, String numeroContrato, Long numeroSe, String numeroCartaoPostagem, String cnpjCartao, LocalDateTime dataInicioVigencia, LocalDateTime dataFimVigencia) {
        this.cnpj = cnpj;
        this.numeroContrato = numeroContrato;
        this.numeroSe = numeroSe;
        this.numeroCartaoPostagem = numeroCartaoPostagem;
        this.cnpjCartao = cnpjCartao;
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public T3WCorreiosContratoCartaoPostagem setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public T3WCorreiosContratoCartaoPostagem setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
        return this;
    }

    public Long getNumeroSe() {
        return numeroSe;
    }

    public T3WCorreiosContratoCartaoPostagem setNumeroSe(Long numeroSe) {
        this.numeroSe = numeroSe;
        return this;
    }

    public String getNumeroCartaoPostagem() {
        return numeroCartaoPostagem;
    }

    public T3WCorreiosContratoCartaoPostagem setNumeroCartaoPostagem(String numeroCartaoPostagem) {
        this.numeroCartaoPostagem = numeroCartaoPostagem;
        return this;
    }

    public String getCnpjCartao() {
        return cnpjCartao;
    }

    public T3WCorreiosContratoCartaoPostagem setCnpjCartao(String cnpjCartao) {
        this.cnpjCartao = cnpjCartao;
        return this;
    }

    public LocalDateTime getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public T3WCorreiosContratoCartaoPostagem setDataInicioVigencia(LocalDateTime dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
        return this;
    }

    public LocalDateTime getDataFimVigencia() {
        return dataFimVigencia;
    }

    public T3WCorreiosContratoCartaoPostagem setDataFimVigencia(LocalDateTime dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
        return this;
    }

    public T3WCorreiosContratoCartaoStatus getStatus() {
        return status;
    }

    public T3WCorreiosContratoCartaoPostagem setStatus(T3WCorreiosContratoCartaoStatus status) {
        this.status = status;
        return this;
    }
}
