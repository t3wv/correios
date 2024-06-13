package io.t3w.correios.contratos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosContratoCartaoPostagem {
    @JsonProperty("cnpj")
    private String cnpj;

    @JsonProperty("nuContrato")
    private String nuContrato;

    @JsonProperty("nuSe")
    private Long nuSe;

    @JsonProperty("nuCartaoPostagem")
    private String nuCartaoPostagem;

    @JsonProperty("cnpjCartao")
    private String cnpjCartao;

    @JsonProperty("dtInicioVigencia")
    private LocalDateTime dtInicioVigencia;

    @JsonProperty("dtFimVigencia")
    private LocalDateTime dtFimVigencia;

    public T3WCorreiosContratoCartaoPostagem() {
    }

    public T3WCorreiosContratoCartaoPostagem(String cnpj, String nuContrato, Long nuSe, String nuCartaoPostagem, String cnpjCartao, LocalDateTime dtInicioVigencia, LocalDateTime dtFimVigencia) {
        this.cnpj = cnpj;
        this.nuContrato = nuContrato;
        this.nuSe = nuSe;
        this.nuCartaoPostagem = nuCartaoPostagem;
        this.cnpjCartao = cnpjCartao;
        this.dtInicioVigencia = dtInicioVigencia;
        this.dtFimVigencia = dtFimVigencia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public T3WCorreiosContratoCartaoPostagem setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getNuContrato() {
        return nuContrato;
    }

    public T3WCorreiosContratoCartaoPostagem setNuContrato(String nuContrato) {
        this.nuContrato = nuContrato;
        return this;
    }

    public Long getNuSe() {
        return nuSe;
    }

    public T3WCorreiosContratoCartaoPostagem setNuSe(Long nuSe) {
        this.nuSe = nuSe;
        return this;
    }

    public String getNuCartaoPostagem() {
        return nuCartaoPostagem;
    }

    public T3WCorreiosContratoCartaoPostagem setNuCartaoPostagem(String nuCartaoPostagem) {
        this.nuCartaoPostagem = nuCartaoPostagem;
        return this;
    }

    public String getCnpjCartao() {
        return cnpjCartao;
    }

    public T3WCorreiosContratoCartaoPostagem setCnpjCartao(String cnpjCartao) {
        this.cnpjCartao = cnpjCartao;
        return this;
    }

    public LocalDateTime getDtInicioVigencia() {
        return dtInicioVigencia;
    }

    public T3WCorreiosContratoCartaoPostagem setDtInicioVigencia(LocalDateTime dtInicioVigencia) {
        this.dtInicioVigencia = dtInicioVigencia;
        return this;
    }

    public LocalDateTime getDtFimVigencia() {
        return dtFimVigencia;
    }

    public T3WCorreiosContratoCartaoPostagem setDtFimVigencia(LocalDateTime dtFimVigencia) {
        this.dtFimVigencia = dtFimVigencia;
        return this;
    }
}
