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
    private String nuContrato;

    @JsonProperty("nuSe")
    private Long nuSe;

    @JsonProperty("nuCombo")
    private Long nuCombo;

    @JsonProperty("categoria")
    private String categoria;

    @JsonProperty("dtInicioVigencia")
    private LocalDateTime dtInicioVigencia;

    @JsonProperty("dtFimVigencia")
    private LocalDateTime dtFimVigencia;

    @JsonProperty("status")
    private T3WCorreiosContratoStatus status;

    public T3WCorreiosContrato() {
    }

    public T3WCorreiosContrato(String cnpj, String nuContrato, Long nuSe, LocalDateTime dtInicioVigencia, LocalDateTime dtFimVigencia, T3WCorreiosContratoStatus status) {
        this.cnpj = cnpj;
        this.nuContrato = nuContrato;
        this.nuSe = nuSe;
        this.dtInicioVigencia = dtInicioVigencia;
        this.dtFimVigencia = dtFimVigencia;
        this.status = status;
    }

    public String getCnpj() {
        return cnpj;
    }

    public T3WCorreiosContrato setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getNuContrato() {
        return nuContrato;
    }

    public T3WCorreiosContrato setNuContrato(String nuContrato) {
        this.nuContrato = nuContrato;
        return this;
    }

    public Long getNuSe() {
        return nuSe;
    }

    public T3WCorreiosContrato setNuSe(Long nuSe) {
        this.nuSe = nuSe;
        return this;
    }

    public LocalDateTime getDtInicioVigencia() {
        return dtInicioVigencia;
    }

    public T3WCorreiosContrato setDtInicioVigencia(LocalDateTime dtInicioVigencia) {
        this.dtInicioVigencia = dtInicioVigencia;
        return this;
    }

    public LocalDateTime getDtFimVigencia() {
        return dtFimVigencia;
    }

    public T3WCorreiosContrato setDtFimVigencia(LocalDateTime dtFimVigencia) {
        this.dtFimVigencia = dtFimVigencia;
        return this;
    }

    public T3WCorreiosContratoStatus getStatus() {
        return status;
    }

    public T3WCorreiosContrato setStatus(T3WCorreiosContratoStatus status) {
        this.status = status;
        return this;
    }

    public Long getNuCombo() {
        return nuCombo;
    }

    public T3WCorreiosContrato setNuCombo(Long nuCombo) {
        this.nuCombo = nuCombo;
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
