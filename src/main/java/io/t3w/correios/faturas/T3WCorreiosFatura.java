package io.t3w.correios.faturas;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.faturas.enums.T3WCorreiosFaturasStatus;

import java.math.BigDecimal;

public class T3WCorreiosFatura {

    @JsonProperty("id")
    private int id;

    @JsonProperty("contrato")
    private String contrato;

    @JsonProperty("nomeContrato")
    private String nomeContrato;

    @JsonProperty("centroCusto")
    private int centroCusto;

    @JsonProperty("nomeCentroCusto")
    private String nomeCentroCusto;

    @JsonProperty("razaoSocial")
    private String razaoSocial;

    @JsonProperty("cnpjCentroCusto")
    private String cnpjCentroCusto;

    @JsonProperty("status")
    private T3WCorreiosFaturasStatus status;

    @JsonProperty("vencimento")
    private String vencimento;

    @JsonProperty("valor")
    private BigDecimal valor;

    @JsonProperty("itemFatura")
    private String itemFatura;

    @JsonProperty("drFatura")
    private String drFatura;

    @JsonProperty("valorEmAberto")
    private String valorEmAberto;

    /*
     * fixme: Esse campo possivelmente deve ser um enum, porém, não consta informações sobre em lugar nenhum.
     * retirado da descrição no swagger: "Tipo do documento. Exemplo: RE (equivale ao R&)"
     */
    @JsonProperty("tipoDocumento")
    private String tipoDocumento;

    @JsonProperty("dataCancelamento")
    private String dataCancelamento;

    @JsonProperty("drContrato")
    private String drContrato;

    @JsonProperty("dataFechamento")
    private String dataFechamento;

    @JsonProperty("sedexLogico")
    private String sedexLogico;

    @JsonProperty("dataPagamento")
    private String dataPagamento;

    // Fixme: Consta na documentação, mas não vem no retorno
    //  @JsonProperty("fatura")
    //  private String fatura;

    @JsonProperty("dr")
    private String dr;

    @JsonProperty("nmCentroCustos")
    private String nmCentroCustos;

    @JsonProperty("codigoCliente")
    private int codigoCliente;

    @JsonProperty("tipoPagamento")
    private String tipoPagamento;

    @JsonProperty("an8Cliente")
    private int an8Cliente;

    @JsonProperty("lote")
    private String lote;

    public T3WCorreiosFatura() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getNomeContrato() {
        return nomeContrato;
    }

    public void setNomeContrato(String nomeContrato) {
        this.nomeContrato = nomeContrato;
    }

    public int getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(int centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getNomeCentroCusto() {
        return nomeCentroCusto;
    }

    public void setNomeCentroCusto(String nomeCentroCusto) {
        this.nomeCentroCusto = nomeCentroCusto;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpjCentroCusto() {
        return cnpjCentroCusto;
    }

    public void setCnpjCentroCusto(String cnpjCentroCusto) {
        this.cnpjCentroCusto = cnpjCentroCusto;
    }

    public T3WCorreiosFaturasStatus getStatus() { return status; }

    public void setStatus(T3WCorreiosFaturasStatus status) {
        this.status = status;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getItemFatura() {
        return itemFatura;
    }

    public void setItemFatura(String itemFatura) {
        this.itemFatura = itemFatura;
    }

    public String getDrFatura() {
        return drFatura;
    }

    public void setDrFatura(String drFatura) {
        this.drFatura = drFatura;
    }

    public String getValorEmAberto() {
        return valorEmAberto;
    }

    public void setValorEmAberto(String valorEmAberto) {
        this.valorEmAberto = valorEmAberto;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(String dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getDrContrato() {
        return drContrato;
    }

    public void setDrContrato(String drContrato) {
        this.drContrato = drContrato;
    }

    public String getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getSedexLogico() {
        return sedexLogico;
    }

    public void setSedexLogico(String sedexLogico) {
        this.sedexLogico = sedexLogico;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

//    public String getFatura() { return fatura; }

//    public void setFatura(String fatura) {
//        this.fatura = fatura;
//    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getNmCentroCustos() {
        return nmCentroCustos;
    }

    public void setNmCentroCustos(String nmCentroCustos) {
        this.nmCentroCustos = nmCentroCustos;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public int getAn8Cliente() {
        return an8Cliente;
    }

    public void setAn8Cliente(int an8Cliente) {
        this.an8Cliente = an8Cliente;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
