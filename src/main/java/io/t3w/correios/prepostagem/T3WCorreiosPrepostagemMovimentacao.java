package io.t3w.correios.prepostagem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)

public class T3WCorreiosPrepostagemMovimentacao {
    @JsonProperty("id")
    private String id;

    @JsonProperty("numeroPlp")
    private Long numeroPlp;

    @JsonProperty("numeroExternoCliente")
    private String numeroExternoCliente;

    @JsonProperty("numeroCartaoPostagem")
    private Integer numeroCartaoPostagem;

    @JsonProperty("cepAgencia")
    private String cepAgencia;

    @JsonProperty("cepDestino")
    private String cepDestino;

    @JsonProperty("numeroAtendimento")
    private String numeroAtendimento;

    @JsonProperty("dataPostagem")
    private String dataPostagem;

    @JsonProperty("dataHoraAtendimento")
    private String dataHoraAtendimento;

    @JsonProperty("valorAtendimento")
    private BigDecimal valorAtendimento;

    @JsonProperty("numeroSistema")
    private String numeroSistema;

    @JsonProperty("cepRemetente")
    private String cepRemetente;

    @JsonProperty("cepDestinatario")
    private String cepDestinatario;

    @JsonProperty("codigoObjeto")
    private String codigoObjeto;

    @JsonProperty("codigoServico")
    private String codigoServico;

    @JsonProperty("nomeServico")
    private String nomeServico;

    @JsonProperty("alturaObjeto")
    private String alturaObjeto;

    @JsonProperty("comprimentoObjeto")
    private String comprimentoObjeto;

    @JsonProperty("larguraObjeto")
    private String larguraObjeto;

    @JsonProperty("diametroObjeto")
    private String diametroObjeto;

    @JsonProperty("pesoObjeto")
    private String pesoObjeto;

    @JsonProperty("pesoTarifadoObjeto")
    private String pesoTarifadoObjeto;

    @JsonProperty("valorDeclaradoObjeto")
    private String valorDeclaradoObjeto;

    @JsonProperty("numeroServicoAdicional1")
    private Long numeroServicoAdicional1;

    @JsonProperty("valorServicoAdicional1")
    private Double valorServicoAdicional1;

    @JsonProperty("numeroServicoAdicional2")
    private Long numeroServicoAdicional2;

    @JsonProperty("valorServicoAdicional2")
    private Double valorServicoAdicional2;

    @JsonProperty("numeroServicoAdicional3")
    private Long numeroServicoAdicional3;

    @JsonProperty("valorServicoAdicional3")
    private Double valorServicoAdicional3;

    @JsonProperty("numeroServicoAdicional4")
    private Long numeroServicoAdicional4;

    @JsonProperty("valorServicoAdicional4")
    private Double valorServicoAdicional4;

    @JsonProperty("numeroServicoAdicional5")
    private Long numeroServicoAdicional5;

    @JsonProperty("valorServicoAdicional5")
    private Double valorServicoAdicional5;

    @JsonProperty("numeroServicoAdicional6")
    private Long numeroServicoAdicional6;

    @JsonProperty("valorServicoAdicional6")
    private Double valorServicoAdicional6;

    @JsonProperty("numeroServicoAdicional7")
    private Long numeroServicoAdicional7;

    @JsonProperty("valorServicoAdicional7")
    private Double valorServicoAdicional7;

    public T3WCorreiosPrepostagemMovimentacao() {
    }

    public T3WCorreiosPrepostagemMovimentacao(String id, Long numeroPlp, String numeroExternoCliente, Integer numeroCartaoPostagem, String cepAgencia, String cepDestino, String numeroAtendimento, String dataPostagem, String dataHoraAtendimento, BigDecimal valorAtendimento, String numeroSistema, String cepRemetente, String cepDestinatario, String codigoObjeto, String codigoServico, String nomeServico, String alturaObjeto, String comprimentoObjeto, String larguraObjeto, String diametroObjeto, String pesoObjeto, String pesoTarifadoObjeto, String valorDeclaradoObjeto, Long numeroServicoAdicional1, Double valorServicoAdicional1, Long numeroServicoAdicional2, Double valorServicoAdicional2, Long numeroServicoAdicional3, Double valorServicoAdicional3, Long numeroServicoAdicional4, Double valorServicoAdicional4, Long numeroServicoAdicional5, Double valorServicoAdicional5, Long numeroServicoAdicional6, Double valorServicoAdicional6, Long numeroServicoAdicional7, Double valorServicoAdicional7) {
        this.id = id;
        this.numeroPlp = numeroPlp;
        this.numeroExternoCliente = numeroExternoCliente;
        this.numeroCartaoPostagem = numeroCartaoPostagem;
        this.cepAgencia = cepAgencia;
        this.cepDestino = cepDestino;
        this.numeroAtendimento = numeroAtendimento;
        this.dataPostagem = dataPostagem;
        this.dataHoraAtendimento = dataHoraAtendimento;
        this.valorAtendimento = valorAtendimento;
        this.numeroSistema = numeroSistema;
        this.cepRemetente = cepRemetente;
        this.cepDestinatario = cepDestinatario;
        this.codigoObjeto = codigoObjeto;
        this.codigoServico = codigoServico;
        this.nomeServico = nomeServico;
        this.alturaObjeto = alturaObjeto;
        this.comprimentoObjeto = comprimentoObjeto;
        this.larguraObjeto = larguraObjeto;
        this.diametroObjeto = diametroObjeto;
        this.pesoObjeto = pesoObjeto;
        this.pesoTarifadoObjeto = pesoTarifadoObjeto;
        this.valorDeclaradoObjeto = valorDeclaradoObjeto;
        this.numeroServicoAdicional1 = numeroServicoAdicional1;
        this.valorServicoAdicional1 = valorServicoAdicional1;
        this.numeroServicoAdicional2 = numeroServicoAdicional2;
        this.valorServicoAdicional2 = valorServicoAdicional2;
        this.numeroServicoAdicional3 = numeroServicoAdicional3;
        this.valorServicoAdicional3 = valorServicoAdicional3;
        this.numeroServicoAdicional4 = numeroServicoAdicional4;
        this.valorServicoAdicional4 = valorServicoAdicional4;
        this.numeroServicoAdicional5 = numeroServicoAdicional5;
        this.valorServicoAdicional5 = valorServicoAdicional5;
        this.numeroServicoAdicional6 = numeroServicoAdicional6;
        this.valorServicoAdicional6 = valorServicoAdicional6;
        this.numeroServicoAdicional7 = numeroServicoAdicional7;
        this.valorServicoAdicional7 = valorServicoAdicional7;
    }

    public String getId() {
        return id;
    }

    public T3WCorreiosPrepostagemMovimentacao setId(String id) {
        this.id = id;
        return this;
    }

    public Long getNumeroPlp() {
        return numeroPlp;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroPlp(Long numeroPlp) {
        this.numeroPlp = numeroPlp;
        return this;
    }

    public String getNumeroExternoCliente() {
        return numeroExternoCliente;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroExternoCliente(String numeroExternoCliente) {
        this.numeroExternoCliente = numeroExternoCliente;
        return this;
    }

    public Integer getNumeroCartaoPostagem() {
        return numeroCartaoPostagem;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroCartaoPostagem(Integer numeroCartaoPostagem) {
        this.numeroCartaoPostagem = numeroCartaoPostagem;
        return this;
    }

    public String getCepAgencia() {
        return cepAgencia;
    }

    public T3WCorreiosPrepostagemMovimentacao setCepAgencia(String cepAgencia) {
        this.cepAgencia = cepAgencia;
        return this;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public T3WCorreiosPrepostagemMovimentacao setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
        return this;
    }

    public String getNumeroAtendimento() {
        return numeroAtendimento;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroAtendimento(String numeroAtendimento) {
        this.numeroAtendimento = numeroAtendimento;
        return this;
    }

    public String getDataPostagem() {
        return dataPostagem;
    }

    public T3WCorreiosPrepostagemMovimentacao setDataPostagem(String dataPostagem) {
        this.dataPostagem = dataPostagem;
        return this;
    }

    public String getDataHoraAtendimento() {
        return dataHoraAtendimento;
    }

    public T3WCorreiosPrepostagemMovimentacao setDataHoraAtendimento(String dataHoraAtendimento) {
        this.dataHoraAtendimento = dataHoraAtendimento;
        return this;
    }

    public BigDecimal getValorAtendimento() {
        return valorAtendimento;
    }

    public T3WCorreiosPrepostagemMovimentacao setValorAtendimento(BigDecimal valorAtendimento) {
        this.valorAtendimento = valorAtendimento;
        return this;
    }

    public String getNumeroSistema() {
        return numeroSistema;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroSistema(String numeroSistema) {
        this.numeroSistema = numeroSistema;
        return this;
    }

    public String getCepRemetente() {
        return cepRemetente;
    }

    public T3WCorreiosPrepostagemMovimentacao setCepRemetente(String cepRemetente) {
        this.cepRemetente = cepRemetente;
        return this;
    }

    public String getCepDestinatario() {
        return cepDestinatario;
    }

    public T3WCorreiosPrepostagemMovimentacao setCepDestinatario(String cepDestinatario) {
        this.cepDestinatario = cepDestinatario;
        return this;
    }

    public String getCodigoObjeto() {
        return codigoObjeto;
    }

    public T3WCorreiosPrepostagemMovimentacao setCodigoObjeto(String codigoObjeto) {
        this.codigoObjeto = codigoObjeto;
        return this;
    }

    public String getCodigoServico() {
        return codigoServico;
    }

    public T3WCorreiosPrepostagemMovimentacao setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
        return this;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public T3WCorreiosPrepostagemMovimentacao setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
        return this;
    }

    public String getAlturaObjeto() {
        return alturaObjeto;
    }

    public T3WCorreiosPrepostagemMovimentacao setAlturaObjeto(String alturaObjeto) {
        this.alturaObjeto = alturaObjeto;
        return this;
    }

    public String getComprimentoObjeto() {
        return comprimentoObjeto;
    }

    public T3WCorreiosPrepostagemMovimentacao setComprimentoObjeto(String comprimentoObjeto) {
        this.comprimentoObjeto = comprimentoObjeto;
        return this;
    }

    public String getLarguraObjeto() {
        return larguraObjeto;
    }

    public T3WCorreiosPrepostagemMovimentacao setLarguraObjeto(String larguraObjeto) {
        this.larguraObjeto = larguraObjeto;
        return this;
    }

    public String getDiametroObjeto() {
        return diametroObjeto;
    }

    public T3WCorreiosPrepostagemMovimentacao setDiametroObjeto(String diametroObjeto) {
        this.diametroObjeto = diametroObjeto;
        return this;
    }

    public String getPesoObjeto() {
        return pesoObjeto;
    }

    public T3WCorreiosPrepostagemMovimentacao setPesoObjeto(String pesoObjeto) {
        this.pesoObjeto = pesoObjeto;
        return this;
    }

    public String getPesoTarifadoObjeto() {
        return pesoTarifadoObjeto;
    }

    public T3WCorreiosPrepostagemMovimentacao setPesoTarifadoObjeto(String pesoTarifadoObjeto) {
        this.pesoTarifadoObjeto = pesoTarifadoObjeto;
        return this;
    }

    public String getValorDeclaradoObjeto() {
        return valorDeclaradoObjeto;
    }

    public T3WCorreiosPrepostagemMovimentacao setValorDeclaradoObjeto(String valorDeclaradoObjeto) {
        this.valorDeclaradoObjeto = valorDeclaradoObjeto;
        return this;
    }

    public Long getNumeroServicoAdicional1() {
        return numeroServicoAdicional1;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroServicoAdicional1(Long numeroServicoAdicional1) {
        this.numeroServicoAdicional1 = numeroServicoAdicional1;
        return this;
    }

    public Double getValorServicoAdicional1() {
        return valorServicoAdicional1;
    }

    public T3WCorreiosPrepostagemMovimentacao setValorServicoAdicional1(Double valorServicoAdicional1) {
        this.valorServicoAdicional1 = valorServicoAdicional1;
        return this;
    }

    public Long getNumeroServicoAdicional2() {
        return numeroServicoAdicional2;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroServicoAdicional2(Long numeroServicoAdicional2) {
        this.numeroServicoAdicional2 = numeroServicoAdicional2;
        return this;
    }

    public Double getValorServicoAdicional2() {
        return valorServicoAdicional2;
    }

    public T3WCorreiosPrepostagemMovimentacao setValorServicoAdicional2(Double valorServicoAdicional2) {
        this.valorServicoAdicional2 = valorServicoAdicional2;
        return this;
    }

    public Long getNumeroServicoAdicional3() {
        return numeroServicoAdicional3;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroServicoAdicional3(Long numeroServicoAdicional3) {
        this.numeroServicoAdicional3 = numeroServicoAdicional3;
        return this;
    }

    public Double getValorServicoAdicional3() {
        return valorServicoAdicional3;
    }

    public T3WCorreiosPrepostagemMovimentacao setValorServicoAdicional3(Double valorServicoAdicional3) {
        this.valorServicoAdicional3 = valorServicoAdicional3;
        return this;
    }

    public Long getNumeroServicoAdicional4() {
        return numeroServicoAdicional4;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroServicoAdicional4(Long numeroServicoAdicional4) {
        this.numeroServicoAdicional4 = numeroServicoAdicional4;
        return this;
    }

    public Double getValorServicoAdicional4() {
        return valorServicoAdicional4;
    }

    public T3WCorreiosPrepostagemMovimentacao setValorServicoAdicional4(Double valorServicoAdicional4) {
        this.valorServicoAdicional4 = valorServicoAdicional4;
        return this;
    }

    public Long getNumeroServicoAdicional5() {
        return numeroServicoAdicional5;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroServicoAdicional5(Long numeroServicoAdicional5) {
        this.numeroServicoAdicional5 = numeroServicoAdicional5;
        return this;
    }

    public Double getValorServicoAdicional5() {
        return valorServicoAdicional5;
    }

    public T3WCorreiosPrepostagemMovimentacao setValorServicoAdicional5(Double valorServicoAdicional5) {
        this.valorServicoAdicional5 = valorServicoAdicional5;
        return this;
    }

    public Long getNumeroServicoAdicional6() {
        return numeroServicoAdicional6;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroServicoAdicional6(Long numeroServicoAdicional6) {
        this.numeroServicoAdicional6 = numeroServicoAdicional6;
        return this;
    }

    public Double getValorServicoAdicional6() {
        return valorServicoAdicional6;
    }

    public T3WCorreiosPrepostagemMovimentacao setValorServicoAdicional6(Double valorServicoAdicional6) {
        this.valorServicoAdicional6 = valorServicoAdicional6;
        return this;
    }

    public Long getNumeroServicoAdicional7() {
        return numeroServicoAdicional7;
    }

    public T3WCorreiosPrepostagemMovimentacao setNumeroServicoAdicional7(Long numeroServicoAdicional7) {
        this.numeroServicoAdicional7 = numeroServicoAdicional7;
        return this;
    }

    public Double getValorServicoAdicional7() {
        return valorServicoAdicional7;
    }

    public T3WCorreiosPrepostagemMovimentacao setValorServicoAdicional7(Double valorServicoAdicional7) {
        this.valorServicoAdicional7 = valorServicoAdicional7;
        return this;
    }
}
