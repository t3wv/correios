package io.t3w.correios.contratos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.contratos.enums.T3WCorreiosContratoStatus;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosContrato {
    @JsonProperty("cnpj")
    private String cnpj;

    @JsonProperty("nuContrato")
    private String numeroContrato;

    @JsonProperty("nuSe")
    private Long numeroSe;

    @JsonProperty("nuCombo")
    private Long numeroCombo;

    @JsonProperty("categoria")
    private String categoria;

    @JsonProperty("dtInicioVigencia")
    private LocalDateTime dataInicioVigencia;

    @JsonProperty("dtFimVigencia")
    private LocalDateTime dataFimVigencia;

    @JsonProperty("status")
    private T3WCorreiosContratoStatus status;

    public T3WCorreiosContrato() {
    }

    public T3WCorreiosContrato(String cnpj, String numeroContrato, Long numeroSe, LocalDateTime dataInicioVigencia, LocalDateTime dataFimVigencia, T3WCorreiosContratoStatus status) {
        this.cnpj = cnpj;
        this.numeroContrato = numeroContrato;
        this.numeroSe = numeroSe;
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
        this.status = status;
    }

    public String getCnpj() {
        return cnpj;
    }

    public T3WCorreiosContrato setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public T3WCorreiosContrato setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
        return this;
    }

    public Long getNumeroSe() {
        return numeroSe;
    }

    public T3WCorreiosContrato setNumeroSe(Long numeroSe) {
        this.numeroSe = numeroSe;
        return this;
    }

    public LocalDateTime getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public T3WCorreiosContrato setDataInicioVigencia(LocalDateTime dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
        return this;
    }

    public LocalDateTime getDataFimVigencia() {
        return dataFimVigencia;
    }

    public T3WCorreiosContrato setDataFimVigencia(LocalDateTime dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
        return this;
    }

    public T3WCorreiosContratoStatus getStatus() {
        return status;
    }

    public T3WCorreiosContrato setStatus(T3WCorreiosContratoStatus status) {
        this.status = status;
        return this;
    }

    public Long getNumeroCombo() {
        return numeroCombo;
    }

    public T3WCorreiosContrato setNumeroCombo(Long numeroCombo) {
        this.numeroCombo = numeroCombo;
        return this;
    }

    public String getCategoria() {
        return categoria;
    }

    public T3WCorreiosContrato setCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }
}
