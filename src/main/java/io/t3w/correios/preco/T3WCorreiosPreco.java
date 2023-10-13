package io.t3w.correios.preco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.preco.enums.InformacaoAdicional;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPreco {

    @JsonProperty("coProduto")
    private String codigo;
    @JsonProperty("noProduto")
    private String numero;

    @JsonProperty("pcBase")
    private double precoBase;

    @JsonProperty("pcBaseGeral")
    private double precoBaseGeral;
    @JsonProperty("peVariacao")
    private double percentualVariacao;

    @JsonProperty("pcReferencia")
    private double precoReferencia;

    @JsonProperty("beneficios")
    private List<T3WCorreiosPrecoBeneficios> beneficios; //components/schemas/BeneficioResponse

    @JsonProperty("vlBaseCalculoImposto")
    private double valorBaseCalculoImposto;

    @JsonProperty("nuRequisicao")
    private String numeroRequisicao;

    @JsonProperty("inPesoCubico")
    private boolean inPesoCubico;

    @JsonProperty("psCobrado")
    private double pesoCobrado;

    @JsonProperty("servicoAdicional")
    private List<T3WCorreiosPrecoServicoAdicional> servicosAdicionais; // components/schemas/PrecoServicoAdicional

    @JsonProperty("peAdValorem")
    private double percentualAdValorem;

    @JsonProperty("vlSeguroAutomatico")
    private double valorSeguroAutomatico;

    @JsonProperty("qtAdicional")
    private String quantidadeAdicional;

    @JsonProperty("pcFaixa")
    private double precoFaixa;

    @JsonProperty("pcCadaAdicional")
    private double precoCadaAdicional;

    @JsonProperty("pcTotalAdicional")
    private double precoTotalAdicional;

    @JsonProperty("pcFaixaVariacao")
    private double precoFaixaVariacao;

    @JsonProperty("pcCadaAdicionalVariacao")
    private double precoCadaAdicionalVariacao;

    @JsonProperty("pcTotalAdicionalVariacao")
    private double precoTotalAdicionalVariacao;

    @JsonProperty("vlTotalDescVariacao")
    private double valorTotalDescontosVariacao;

    @JsonProperty("vlTotalBeneficios")
    private double valorTotalBeneficios;

    @JsonProperty("pcProduto")
    private double precoProduto;

    @JsonProperty("pcTotalServicosAdicionais")
    private double precoTotalServicosAdicionais;

    @JsonProperty("pcFinal")
    private double precoFinal;

    @JsonProperty("txErro")
    private String txErro;

    @JsonProperty("infoAdicional")
    private List<InformacaoAdicional> informacoesAdicionais; //components/schemas/InfoAdicionalResponse

    @JsonProperty("nomeProduto")
    private String nomeProduto;

    @JsonProperty("peIsencaoArmazenagem")
    private double percentualIsencaoServicosArmazenagem;

    @JsonProperty("taxaExtra")
    private List<T3WCorreiosPrecoTaxaExtra> taxaExtra; //components/schemas/TaxaExtraResponse


    public T3WCorreiosPreco() {}

    public T3WCorreiosPreco(String codigo, String numero, double precoBase, double precoBaseGeral, double percentualVariacao, double precoReferencia, List<T3WCorreiosPrecoBeneficios> beneficios, double valorBaseCalculoImposto, String numeroRequisicao, boolean inPesoCubico, double pesoCobrado, List<T3WCorreiosPrecoServicoAdicional> servicosAdicionais, double percentualAdValorem, double valorSeguroAutomatico, String quantidadeAdicional, double precoFaixa, double precoCadaAdicional, double precoTotalAdicional, double precoFaixaVariacao, double precoCadaAdicionalVariacao, double precoTotalAdicionalVariacao, double valorTotalDescontosVariacao, double valorTotalBeneficios, double precoProduto, double precoTotalServicosAdicionais, double precoFinal, String txErro, List<InformacaoAdicional> informacoesAdicionais, String nomeProduto, double percentualIsencaoServicosArmazenagem, List<T3WCorreiosPrecoTaxaExtra> taxaExtra) {
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

    public double getPrecoBase() {
        return precoBase;
    }

    public T3WCorreiosPreco setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
        return this;
    }

    public double getPrecoBaseGeral() {
        return precoBaseGeral;
    }

    public T3WCorreiosPreco setPrecoBaseGeral(double precoBaseGeral) {
        this.precoBaseGeral = precoBaseGeral;
        return this;
    }

    public double getPercentualVariacao() {
        return percentualVariacao;
    }

    public T3WCorreiosPreco setPercentualVariacao(double percentualVariacao) {
        this.percentualVariacao = percentualVariacao;
        return this;
    }

    public double getPrecoReferencia() {
        return precoReferencia;
    }

    public T3WCorreiosPreco setPrecoReferencia(double precoReferencia) {
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

    public double getValorBaseCalculoImposto() {
        return valorBaseCalculoImposto;
    }

    public T3WCorreiosPreco setValorBaseCalculoImposto(double valorBaseCalculoImposto) {
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

    public double getPesoCobrado() {
        return pesoCobrado;
    }

    public T3WCorreiosPreco setPesoCobrado(double pesoCobrado) {
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

    public double getPercentualAdValorem() {
        return percentualAdValorem;
    }

    public T3WCorreiosPreco setPercentualAdValorem(double percentualAdValorem) {
        this.percentualAdValorem = percentualAdValorem;
        return this;
    }


    public double getValorSeguroAutomatico() {
        return valorSeguroAutomatico;
    }

    public T3WCorreiosPreco setValorSeguroAutomatico(double valorSeguroAutomatico) {
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

    public double getPrecoFaixa() {
        return precoFaixa;
    }

    public T3WCorreiosPreco setPrecoFaixa(double precoFaixa) {
        this.precoFaixa = precoFaixa;
        return this;
    }

    public double getPrecoCadaAdicional() {
        return precoCadaAdicional;
    }

    public T3WCorreiosPreco setPrecoCadaAdicional(double precoCadaAdicional) {
        this.precoCadaAdicional = precoCadaAdicional;
        return this;
    }

    public double getPrecoTotalAdicional() {
        return precoTotalAdicional;
    }

    public T3WCorreiosPreco setPrecoTotalAdicional(double precoTotalAdicional) {
        this.precoTotalAdicional = precoTotalAdicional;
        return this;
    }

    public double getPrecoFaixaVariacao() {
        return precoFaixaVariacao;
    }

    public T3WCorreiosPreco setPrecoFaixaVariacao(double precoFaixaVariacao) {
        this.precoFaixaVariacao = precoFaixaVariacao;
        return this;
    }

    public double getPrecoCadaAdicionalVariacao() {
        return precoCadaAdicionalVariacao;
    }

    public T3WCorreiosPreco setPrecoCadaAdicionalVariacao(double precoCadaAdicionalVariacao) {
        this.precoCadaAdicionalVariacao = precoCadaAdicionalVariacao;
        return this;
    }

    public double getPrecoTotalAdicionalVariacao() {
        return precoTotalAdicionalVariacao;
    }

    public T3WCorreiosPreco setPrecoTotalAdicionalVariacao(double precoTotalAdicionalVariacao) {
        this.precoTotalAdicionalVariacao = precoTotalAdicionalVariacao;
        return this;
    }

    public double getValorTotalDescontosVariacao() {
        return valorTotalDescontosVariacao;
    }

    public T3WCorreiosPreco setValorTotalDescontosVariacao(double valorTotalDescontosVariacao) {
        this.valorTotalDescontosVariacao = valorTotalDescontosVariacao;
        return this;
    }

    public double getValorTotalBeneficios() {
        return valorTotalBeneficios;
    }

    public T3WCorreiosPreco setValorTotalBeneficios(double valorTotalBeneficios) {
        this.valorTotalBeneficios = valorTotalBeneficios;
        return this;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public T3WCorreiosPreco setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
        return this;
    }

    public double getPrecoTotalServicosAdicionais() {
        return precoTotalServicosAdicionais;
    }

    public T3WCorreiosPreco setPrecoTotalServicosAdicionais(double precoTotalServicosAdicionais) {
        this.precoTotalServicosAdicionais = precoTotalServicosAdicionais;
        return this;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public T3WCorreiosPreco setPrecoFinal(double precoFinal) {
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

    public List<InformacaoAdicional> getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public T3WCorreiosPreco setInformacoesAdicionais(List<InformacaoAdicional> informacoesAdicionais) {
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

    public double getPercentualIsencaoServicosArmazenagem() {
        return percentualIsencaoServicosArmazenagem;
    }

    public T3WCorreiosPreco setPercentualIsencaoServicosArmazenagem(double percentualIsencaoServicosArmazenagem) {
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
