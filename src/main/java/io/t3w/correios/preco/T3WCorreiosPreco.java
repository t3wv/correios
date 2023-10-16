package io.t3w.correios.preco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.preco.enums.T3WCorreiosPrecoInformacaoAdicional;
import io.t3w.correios.preco.enums.T3WCorreiosPrecoServicoAdicional;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPreco {

    @JsonProperty("coProduto")
    private String codigo;
    @JsonProperty("noProduto")
    private String numero;

    @JsonProperty("pcBase")
    private BigDecimal precoBase;

    @JsonProperty("pcBaseGeral")
    private BigDecimal precoBaseGeral;
    @JsonProperty("peVariacao")
    private BigDecimal percentualVariacao;

    @JsonProperty("pcReferencia")
    private BigDecimal precoReferencia;

    @JsonProperty("beneficios")
    private List<T3WCorreiosPrecoBeneficios> beneficios; //components/schemas/BeneficioResponse

    @JsonProperty("vlBaseCalculoImposto")
    private BigDecimal valorBaseCalculoImposto;

    @JsonProperty("nuRequisicao")
    private String numeroRequisicao;

    @JsonProperty("inPesoCubico")
    private boolean inPesoCubico;

    @JsonProperty("psCobrado")
    private BigDecimal pesoCobrado;

    @JsonProperty("servicoAdicional")
    private List<T3WCorreiosPrecoServicoAdicional> servicosAdicionais;

    @JsonProperty("peAdValorem")
    private BigDecimal percentualAdValorem;

    @JsonProperty("vlSeguroAutomatico")
    private BigDecimal valorSeguroAutomatico;

    @JsonProperty("qtAdicional")
    private String quantidadeAdicional;

    @JsonProperty("pcFaixa")
    private BigDecimal precoFaixa;

    @JsonProperty("pcCadaAdicional")
    private BigDecimal precoCadaAdicional;

    @JsonProperty("pcTotalAdicional")
    private BigDecimal precoTotalAdicional;

    @JsonProperty("pcFaixaVariacao")
    private BigDecimal precoFaixaVariacao;

    @JsonProperty("pcCadaAdicionalVariacao")
    private BigDecimal precoCadaAdicionalVariacao;

    @JsonProperty("pcTotalAdicionalVariacao")
    private BigDecimal precoTotalAdicionalVariacao;

    @JsonProperty("vlTotalDescVariacao")
    private BigDecimal valorTotalDescontosVariacao;

    @JsonProperty("vlTotalBeneficios")
    private BigDecimal valorTotalBeneficios;

    @JsonProperty("pcProduto")
    private BigDecimal precoProduto;

    @JsonProperty("pcTotalServicosAdicionais")
    private BigDecimal precoTotalServicosAdicionais;

    @JsonProperty("pcFinal")
    private BigDecimal precoFinal;

    @JsonProperty("txErro")
    private String txErro;

    @JsonProperty("infoAdicional")
    private List<T3WCorreiosPrecoInformacaoAdicional> informacoesAdicionais;

    @JsonProperty("nomeProduto")
    private String nomeProduto;

    @JsonProperty("peIsencaoArmazenagem")
    private BigDecimal percentualIsencaoServicosArmazenagem;

    @JsonProperty("taxaExtra")
    private List<T3WCorreiosPrecoTaxaExtra> taxaExtra;


    public T3WCorreiosPreco() {}

    public T3WCorreiosPreco(String codigo, String numero, BigDecimal precoBase, BigDecimal precoBaseGeral, BigDecimal percentualVariacao, BigDecimal precoReferencia, List<T3WCorreiosPrecoBeneficios> beneficios, BigDecimal valorBaseCalculoImposto, String numeroRequisicao, boolean inPesoCubico, BigDecimal pesoCobrado, List<T3WCorreiosPrecoServicoAdicional> servicosAdicionais, BigDecimal percentualAdValorem, BigDecimal valorSeguroAutomatico, String quantidadeAdicional, BigDecimal precoFaixa, BigDecimal precoCadaAdicional, BigDecimal precoTotalAdicional, BigDecimal precoFaixaVariacao, BigDecimal precoCadaAdicionalVariacao, BigDecimal precoTotalAdicionalVariacao, BigDecimal valorTotalDescontosVariacao, BigDecimal valorTotalBeneficios, BigDecimal precoProduto, BigDecimal precoTotalServicosAdicionais, BigDecimal precoFinal, String txErro, List<T3WCorreiosPrecoInformacaoAdicional> informacoesAdicionais, String nomeProduto, BigDecimal percentualIsencaoServicosArmazenagem, List<T3WCorreiosPrecoTaxaExtra> taxaExtra) {
        this.codigo = codigo;
        this.numero = numero;
        this.precoBase = precoBase;
        this.precoBaseGeral = precoBaseGeral;
        this.percentualVariacao = percentualVariacao;
        this.precoReferencia = precoReferencia;
        this.beneficios = beneficios;
        this.valorBaseCalculoImposto = valorBaseCalculoImposto;
        this.numeroRequisicao = numeroRequisicao;
        this.inPesoCubico = inPesoCubico;
        this.pesoCobrado = pesoCobrado;
        this.servicosAdicionais = servicosAdicionais;
        this.percentualAdValorem = percentualAdValorem;
        this.valorSeguroAutomatico = valorSeguroAutomatico;
        this.quantidadeAdicional = quantidadeAdicional;
        this.precoFaixa = precoFaixa;
        this.precoCadaAdicional = precoCadaAdicional;
        this.precoTotalAdicional = precoTotalAdicional;
        this.precoFaixaVariacao = precoFaixaVariacao;
        this.precoCadaAdicionalVariacao = precoCadaAdicionalVariacao;
        this.precoTotalAdicionalVariacao = precoTotalAdicionalVariacao;
        this.valorTotalDescontosVariacao = valorTotalDescontosVariacao;
        this.valorTotalBeneficios = valorTotalBeneficios;
        this.precoProduto = precoProduto;
        this.precoTotalServicosAdicionais = precoTotalServicosAdicionais;
        this.precoFinal = precoFinal;
        this.txErro = txErro;
        this.informacoesAdicionais = informacoesAdicionais;
        this.nomeProduto = nomeProduto;
        this.percentualIsencaoServicosArmazenagem = percentualIsencaoServicosArmazenagem;
        this.taxaExtra = taxaExtra;
    }

    public String getCodigo() {
        return codigo;
    }

    public T3WCorreiosPreco setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public T3WCorreiosPreco setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public T3WCorreiosPreco setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
        return this;
    }

    public BigDecimal getPrecoBaseGeral() {
        return precoBaseGeral;
    }

    public T3WCorreiosPreco setPrecoBaseGeral(BigDecimal precoBaseGeral) {
        this.precoBaseGeral = precoBaseGeral;
        return this;
    }

    public BigDecimal getPercentualVariacao() {
        return percentualVariacao;
    }

    public T3WCorreiosPreco setPercentualVariacao(BigDecimal percentualVariacao) {
        this.percentualVariacao = percentualVariacao;
        return this;
    }

    public BigDecimal getPrecoReferencia() {
        return precoReferencia;
    }

    public T3WCorreiosPreco setPrecoReferencia(BigDecimal precoReferencia) {
        this.precoReferencia = precoReferencia;
        return this;
    }

    public List<T3WCorreiosPrecoBeneficios> getBeneficios() {
        return beneficios;
    }

    public T3WCorreiosPreco setBeneficios(List<T3WCorreiosPrecoBeneficios> beneficios) {
        this.beneficios = beneficios;
        return this;
    }

    public BigDecimal getValorBaseCalculoImposto() {
        return valorBaseCalculoImposto;
    }

    public T3WCorreiosPreco setValorBaseCalculoImposto(BigDecimal valorBaseCalculoImposto) {
        this.valorBaseCalculoImposto = valorBaseCalculoImposto;
        return this;
    }

    public String getNumeroRequisicao() {
        return numeroRequisicao;
    }

    public T3WCorreiosPreco setNumeroRequisicao(String numeroRequisicao) {
        this.numeroRequisicao = numeroRequisicao;
        return this;
    }

    public boolean isInPesoCubico() {
        return inPesoCubico;
    }

    public T3WCorreiosPreco setInPesoCubico(boolean inPesoCubico) {
        this.inPesoCubico = inPesoCubico;
        return this;
    }

    @JsonProperty("inPesoCubico")
    private T3WCorreiosPreco setInPesoCubico(String inPesoCubico){
        this.setInPesoCubico(inPesoCubico.equals("S"));
        return this;
    }

    public BigDecimal getPesoCobrado() {
        return pesoCobrado;
    }

    public T3WCorreiosPreco setPesoCobrado(BigDecimal pesoCobrado) {
        this.pesoCobrado = pesoCobrado;
        return this;
    }

    public List<T3WCorreiosPrecoServicoAdicional> getServicosAdicionais() {
        return servicosAdicionais;
    }

    public T3WCorreiosPreco setServicosAdicionais(List<T3WCorreiosPrecoServicoAdicional> servicosAdicionais) {
        this.servicosAdicionais = servicosAdicionais;
        return this;
    }

    public BigDecimal getPercentualAdValorem() {
        return percentualAdValorem;
    }

    public T3WCorreiosPreco setPercentualAdValorem(BigDecimal percentualAdValorem) {
        this.percentualAdValorem = percentualAdValorem;
        return this;
    }


    public BigDecimal getValorSeguroAutomatico() {
        return valorSeguroAutomatico;
    }

    public T3WCorreiosPreco setValorSeguroAutomatico(BigDecimal valorSeguroAutomatico) {
        this.valorSeguroAutomatico = valorSeguroAutomatico;
        return this;
    }


    public String getQuantidadeAdicional() {
        return quantidadeAdicional;
    }

    public T3WCorreiosPreco setQuantidadeAdicional(String quantidadeAdicional) {
        this.quantidadeAdicional = quantidadeAdicional;
        return this;
    }

    public BigDecimal getPrecoFaixa() {
        return precoFaixa;
    }

    public T3WCorreiosPreco setPrecoFaixa(BigDecimal precoFaixa) {
        this.precoFaixa = precoFaixa;
        return this;
    }

    public BigDecimal getPrecoCadaAdicional() {
        return precoCadaAdicional;
    }

    public T3WCorreiosPreco setPrecoCadaAdicional(BigDecimal precoCadaAdicional) {
        this.precoCadaAdicional = precoCadaAdicional;
        return this;
    }

    public BigDecimal getPrecoTotalAdicional() {
        return precoTotalAdicional;
    }

    public T3WCorreiosPreco setPrecoTotalAdicional(BigDecimal precoTotalAdicional) {
        this.precoTotalAdicional = precoTotalAdicional;
        return this;
    }

    public BigDecimal getPrecoFaixaVariacao() {
        return precoFaixaVariacao;
    }

    public T3WCorreiosPreco setPrecoFaixaVariacao(BigDecimal precoFaixaVariacao) {
        this.precoFaixaVariacao = precoFaixaVariacao;
        return this;
    }

    public BigDecimal getPrecoCadaAdicionalVariacao() {
        return precoCadaAdicionalVariacao;
    }

    public T3WCorreiosPreco setPrecoCadaAdicionalVariacao(BigDecimal precoCadaAdicionalVariacao) {
        this.precoCadaAdicionalVariacao = precoCadaAdicionalVariacao;
        return this;
    }

    public BigDecimal getPrecoTotalAdicionalVariacao() {
        return precoTotalAdicionalVariacao;
    }

    public T3WCorreiosPreco setPrecoTotalAdicionalVariacao(BigDecimal precoTotalAdicionalVariacao) {
        this.precoTotalAdicionalVariacao = precoTotalAdicionalVariacao;
        return this;
    }

    public BigDecimal getValorTotalDescontosVariacao() {
        return valorTotalDescontosVariacao;
    }

    public T3WCorreiosPreco setValorTotalDescontosVariacao(BigDecimal valorTotalDescontosVariacao) {
        this.valorTotalDescontosVariacao = valorTotalDescontosVariacao;
        return this;
    }

    public BigDecimal getValorTotalBeneficios() {
        return valorTotalBeneficios;
    }

    public T3WCorreiosPreco setValorTotalBeneficios(BigDecimal valorTotalBeneficios) {
        this.valorTotalBeneficios = valorTotalBeneficios;
        return this;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public T3WCorreiosPreco setPrecoProduto(BigDecimal precoProduto) {
        this.precoProduto = precoProduto;
        return this;
    }

    public BigDecimal getPrecoTotalServicosAdicionais() {
        return precoTotalServicosAdicionais;
    }

    public T3WCorreiosPreco setPrecoTotalServicosAdicionais(BigDecimal precoTotalServicosAdicionais) {
        this.precoTotalServicosAdicionais = precoTotalServicosAdicionais;
        return this;
    }

    public BigDecimal getPrecoFinal() {
        return precoFinal;
    }

    public T3WCorreiosPreco setPrecoFinal(BigDecimal precoFinal) {
        this.precoFinal = precoFinal;
        return this;
    }

    public String getTxErro() {
        return txErro;
    }

    public T3WCorreiosPreco setTxErro(String txErro) {
        this.txErro = txErro;
        return this;
    }

    public List<T3WCorreiosPrecoInformacaoAdicional> getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public T3WCorreiosPreco setInformacoesAdicionais(List<T3WCorreiosPrecoInformacaoAdicional> informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
        return this;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public T3WCorreiosPreco setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
        return this;
    }

    public BigDecimal getPercentualIsencaoServicosArmazenagem() {
        return percentualIsencaoServicosArmazenagem;
    }

    public T3WCorreiosPreco setPercentualIsencaoServicosArmazenagem(BigDecimal percentualIsencaoServicosArmazenagem) {
        this.percentualIsencaoServicosArmazenagem = percentualIsencaoServicosArmazenagem;
        return this;
    }

    public List<T3WCorreiosPrecoTaxaExtra> getTaxaExtra() {
        return taxaExtra;
    }

    public T3WCorreiosPreco setTaxaExtra(List<T3WCorreiosPrecoTaxaExtra> taxaExtra) {
        this.taxaExtra = taxaExtra;
        return this;
    }
}
