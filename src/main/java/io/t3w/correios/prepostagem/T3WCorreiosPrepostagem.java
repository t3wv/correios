package io.t3w.correios.prepostagem;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.T3WCorreiosFormatoObjeto;
import io.t3w.correios.T3WCorreiosPessoa;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrepostagem {

    @JsonProperty("id")
    private String id;

    @JsonProperty("idCorreios")
    private String idCorreios;

    @JsonProperty("remetente")
    private T3WCorreiosPessoa remetente;

    @JsonProperty("destinatario")
    private T3WCorreiosPessoa destinatario;

    @JsonProperty("codigoServico")
    private String codigoServico;

    @JsonProperty("precoServico")
    private String precoServico;

    @JsonProperty("precoPrePostagem")
    private String precoPrePostagem;

    @JsonProperty("numeroNotaFiscal")
    private String numeroNotaFiscal;

    @JsonProperty("numeroCartaoPostagem")
    private String numeroCartaoPostagem;

    @JsonProperty("chaveNFe")
    private String chaveNFe;

    @JsonProperty("listaServicoAdicional")
    private List<T3WCorreiosPrepostagemServicoAdicional> listaServicoAdicional;

    @JsonProperty("itensDeclaracaoConteudo")
    private List<T3WCorreiosPrepostagemItemDeclaracaoConteudo> itensDeclaracaoConteudo;

    @JsonProperty("pesoInformado")
    private String pesoInformado;

    @JsonProperty("codigoFormatoObjetoInformado")
    private String codigoFormatoObjetoInformado;

    @JsonProperty("alturaInformada")
    private String alturaInformada;

    @JsonProperty("larguraInformada")
    private String larguraInformada;

    @JsonProperty("comprimentoInformado")
    private String comprimentoInformado;

    @JsonProperty("diametroInformado")
    private String diametroInformado;

    @JsonProperty("ncmObjeto")
    private String ncmObjeto;

    @JsonProperty("rfidObjeto")
    private String rfidObjeto;

    @JsonProperty("cienteObjetoNaoProibido")
    private String cienteObjetoNaoProibido;

    @JsonProperty("idAtendimento")
    private String idAtendimento;

    @JsonProperty("solicitarColeta")
    private String solicitarColeta;

    @JsonProperty("codigoObjeto")
    private String codigoObjeto;

    @JsonProperty("dataPrevistaPostagem")
    private String dataPrevistaPostagem;

    @JsonProperty("prazoPostagem")
    private String prazoPostagem;

    @JsonProperty("observacao")
    private String observacao;

    @JsonProperty("modalidadePagamento")
    private String modalidadePagamento;

    @JsonProperty("logisticaReversa")
    private String logisticaReversa;

    /*
        pattern: ^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$
        example: 01/01/2021 para primeiro de janeiro de 2021 (dd/MM/yyyy)
     */
    @JsonProperty("dataValidadeLogReversa")
    private String dataValidadeLogReversa;

    public T3WCorreiosPrepostagem() {}

    //todo: {@link T3WCorreiosFormatoObjeto}
    /**
     * Construtor para criação de uma nova instância com parâmetros mínimos requeridos.
     *
     * @param remetente Remetente do pacote.
     * @param destinatario Destinatario do pacote.
     * @param codigoServico Codigo de serviço - <b>Deve constar no cartão de postagem do cliente que está fazendo a pré-postagem</b>.
     * @param pesoInformado Peso em gramas do objeto - <b>0 < valor < limite definido pelo tipo de serviço</b>.
     * @param codigoFormatoObjetoInformado Formato do objeto - {@link T3WCorreiosFormatoObjeto}
     * @param cienteObjetoNaoProibido Indica que o objeto a ser pré-postado não é proibido no fluxo postal.
     */
    public T3WCorreiosPrepostagem(T3WCorreiosPessoa remetente, T3WCorreiosPessoa destinatario, String codigoServico, String pesoInformado, String codigoFormatoObjetoInformado, String cienteObjetoNaoProibido) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.codigoServico = codigoServico;
        this.pesoInformado = pesoInformado;
        this.codigoFormatoObjetoInformado = codigoFormatoObjetoInformado;
        this.cienteObjetoNaoProibido = cienteObjetoNaoProibido;
    }

    public T3WCorreiosPrepostagem(String idCorreios, T3WCorreiosPessoa remetente, T3WCorreiosPessoa destinatario, String codigoServico, String precoServico, String precoPrePostagem, String numeroNotaFiscal, String numeroCartaoPostagem, String chaveNFe, List<T3WCorreiosPrepostagemServicoAdicional> listaServicoAdicional, List<T3WCorreiosPrepostagemItemDeclaracaoConteudo> itensDeclaracaoConteudo, String pesoInformado, String codigoFormatoObjetoInformado, String alturaInformada, String larguraInformada, String comprimentoInformado, String diametroInformado, String ncmObjeto, String rfidObjeto, String cienteObjetoNaoProibido, String idAtendimento, String solicitarColeta, String codigoObjeto, String dataPrevistaPostagem, String observacao, String modalidadePagamento, String logisticaReversa, String dataValidadeLogReversa) {
        this.idCorreios = idCorreios;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.codigoServico = codigoServico;
        this.precoServico = precoServico;
        this.precoPrePostagem = precoPrePostagem;
        this.numeroNotaFiscal = numeroNotaFiscal;
        this.numeroCartaoPostagem = numeroCartaoPostagem;
        this.chaveNFe = chaveNFe;
        this.listaServicoAdicional = listaServicoAdicional;
        this.itensDeclaracaoConteudo = itensDeclaracaoConteudo;
        this.pesoInformado = pesoInformado;
        this.codigoFormatoObjetoInformado = codigoFormatoObjetoInformado;
        this.alturaInformada = alturaInformada;
        this.larguraInformada = larguraInformada;
        this.comprimentoInformado = comprimentoInformado;
        this.diametroInformado = diametroInformado;
        this.ncmObjeto = ncmObjeto;
        this.rfidObjeto = rfidObjeto;
        this.cienteObjetoNaoProibido = cienteObjetoNaoProibido;
        this.idAtendimento = idAtendimento;
        this.solicitarColeta = solicitarColeta;
        this.codigoObjeto = codigoObjeto;
        this.dataPrevistaPostagem = dataPrevistaPostagem;
        this.observacao = observacao;
        this.modalidadePagamento = modalidadePagamento;
        this.logisticaReversa = logisticaReversa;
        this.dataValidadeLogReversa = dataValidadeLogReversa;
    }

    public String getIdCorreios() {
        return idCorreios;
    }

    public T3WCorreiosPrepostagem setIdCorreios(String idCorreios) {
        this.idCorreios = idCorreios;
        return this;
    }

    public T3WCorreiosPessoa getRemetente() {
        return remetente;
    }

    public T3WCorreiosPrepostagem setRemetente(T3WCorreiosPessoa remetente) {
        this.remetente = remetente;
        return this;
    }

    public T3WCorreiosPessoa getDestinatario() {
        return destinatario;
    }

    public T3WCorreiosPrepostagem setDestinatario(T3WCorreiosPessoa destinatario) {
        this.destinatario = destinatario;
        return this;
    }

    public String getCodigoServico() {
        return codigoServico;
    }

    public T3WCorreiosPrepostagem setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
        return this;
    }

    public String getPrecoServico() {
        return precoServico;
    }

    public T3WCorreiosPrepostagem setPrecoServico(String precoServico) {
        this.precoServico = precoServico;
        return this;
    }

    public String getPrecoPrePostagem() {
        return precoPrePostagem;
    }

    public T3WCorreiosPrepostagem setPrecoPrePostagem(String precoPrePostagem) {
        this.precoPrePostagem = precoPrePostagem;
        return this;
    }

    public String getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    public T3WCorreiosPrepostagem setNumeroNotaFiscal(String numeroNotaFiscal) {
        this.numeroNotaFiscal = numeroNotaFiscal;
        return this;
    }

    public String getNumeroCartaoPostagem() {
        return numeroCartaoPostagem;
    }

    public T3WCorreiosPrepostagem setNumeroCartaoPostagem(String numeroCartaoPostagem) {
        this.numeroCartaoPostagem = numeroCartaoPostagem;
        return this;
    }

    public String getChaveNFe() {
        return chaveNFe;
    }

    public T3WCorreiosPrepostagem setChaveNFe(String chaveNFe) {
        this.chaveNFe = chaveNFe;
        return this;
    }

    public List<T3WCorreiosPrepostagemServicoAdicional> getListaServicoAdicional() {
        return listaServicoAdicional;
    }

    public T3WCorreiosPrepostagem setListaServicoAdicional(List<T3WCorreiosPrepostagemServicoAdicional> listaServicoAdicional) {
        this.listaServicoAdicional = listaServicoAdicional;
        return this;
    }

    public List<T3WCorreiosPrepostagemItemDeclaracaoConteudo> getItensDeclaracaoConteudo() {
        return itensDeclaracaoConteudo;
    }

    public T3WCorreiosPrepostagem setItensDeclaracaoConteudo(List<T3WCorreiosPrepostagemItemDeclaracaoConteudo> itensDeclaracaoConteudo) {
        this.itensDeclaracaoConteudo = itensDeclaracaoConteudo;
        return this;
    }

    public String getPesoInformado() {
        return pesoInformado;
    }

    public T3WCorreiosPrepostagem setPesoInformado(String pesoInformado) {
        this.pesoInformado = pesoInformado;
        return this;
    }

    public String getCodigoFormatoObjetoInformado() {
        return codigoFormatoObjetoInformado;
    }

    public T3WCorreiosPrepostagem setCodigoFormatoObjetoInformado(String codigoFormatoObjetoInformado) {
        this.codigoFormatoObjetoInformado = codigoFormatoObjetoInformado;
        return this;
    }

    public String getAlturaInformada() {
        return alturaInformada;
    }

    public T3WCorreiosPrepostagem setAlturaInformada(String alturaInformada) {
        this.alturaInformada = alturaInformada;
        return this;
    }

    public String getLarguraInformada() {
        return larguraInformada;
    }

    public T3WCorreiosPrepostagem setLarguraInformada(String larguraInformada) {
        this.larguraInformada = larguraInformada;
        return this;
    }

    public String getComprimentoInformado() {
        return comprimentoInformado;
    }

    public T3WCorreiosPrepostagem setComprimentoInformado(String comprimentoInformado) {
        this.comprimentoInformado = comprimentoInformado;
        return this;
    }

    public String getDiametroInformado() {
        return diametroInformado;
    }

    public T3WCorreiosPrepostagem setDiametroInformado(String diametroInformado) {
        this.diametroInformado = diametroInformado;
        return this;
    }

    public String getNcmObjeto() {
        return ncmObjeto;
    }

    public T3WCorreiosPrepostagem setNcmObjeto(String ncmObjeto) {
        this.ncmObjeto = ncmObjeto;
        return this;
    }

    public String getRfidObjeto() {
        return rfidObjeto;
    }

    public T3WCorreiosPrepostagem setRfidObjeto(String rfidObjeto) {
        this.rfidObjeto = rfidObjeto;
        return this;
    }

    public String getCienteObjetoNaoProibido() {
        return cienteObjetoNaoProibido;
    }

    public T3WCorreiosPrepostagem setCienteObjetoNaoProibido(String cienteObjetoNaoProibido) {
        this.cienteObjetoNaoProibido = cienteObjetoNaoProibido;
        return this;
    }

    public String getIdAtendimento() {
        return idAtendimento;
    }

    public T3WCorreiosPrepostagem setIdAtendimento(String idAtendimento) {
        this.idAtendimento = idAtendimento;
        return this;
    }

    public String getSolicitarColeta() {
        return solicitarColeta;
    }

    public T3WCorreiosPrepostagem setSolicitarColeta(String solicitarColeta) {
        this.solicitarColeta = solicitarColeta;
        return this;
    }

    public String getCodigoObjeto() {
        return codigoObjeto;
    }

    public T3WCorreiosPrepostagem setCodigoObjeto(String codigoObjeto) {
        this.codigoObjeto = codigoObjeto;
        return this;
    }

    public String getDataPrevistaPostagem() {
        return dataPrevistaPostagem;
    }

    public T3WCorreiosPrepostagem setDataPrevistaPostagem(String dataPrevistaPostagem) {
        this.dataPrevistaPostagem = dataPrevistaPostagem;
        return this;
    }

    public String getObservacao() {
        return observacao;
    }

    public T3WCorreiosPrepostagem setObservacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public String getModalidadePagamento() {
        return modalidadePagamento;
    }

    public T3WCorreiosPrepostagem setModalidadePagamento(String modalidadePagamento) {
        this.modalidadePagamento = modalidadePagamento;
        return this;
    }

    public String getId() {
        return id;
    }

    public T3WCorreiosPrepostagem setId(String id) {
        this.id = id;
        return this;
    }

    public String getLogisticaReversa() {
        return logisticaReversa;
    }

    public T3WCorreiosPrepostagem setLogisticaReversa(String logisticaReversa) {
        this.logisticaReversa = logisticaReversa;
        return this;
    }

    public String getDataValidadeLogReversa() {
        return dataValidadeLogReversa;
    }

    public T3WCorreiosPrepostagem setDataValidadeLogReversa(String dataValidadeLogReversa) {
        this.dataValidadeLogReversa = dataValidadeLogReversa;
        return this;
    }

    public String getPrazoPostagem() {
        return prazoPostagem;
    }

    public T3WCorreiosPrepostagem setPrazoPostagem(String prazoPostagem) {
        this.prazoPostagem = prazoPostagem;
        return this;
    }

    @Override
    public String toString() {
        return "T3WCorreiosPrepostagem{" +
               "id='" + id + '\'' +
               ", idCorreios='" + idCorreios + '\'' +
               ", remetente=" + remetente +
               ", destinatario=" + destinatario +
               ", codigoServico='" + codigoServico + '\'' +
               ", precoServico='" + precoServico + '\'' +
               ", precoPrePostagem='" + precoPrePostagem + '\'' +
               ", numeroNotaFiscal='" + numeroNotaFiscal + '\'' +
               ", numeroCartaoPostagem='" + numeroCartaoPostagem + '\'' +
               ", chaveNFe='" + chaveNFe + '\'' +
               ", listaServicoAdicional=" + listaServicoAdicional +
               ", itensDeclaracaoConteudo=" + itensDeclaracaoConteudo +
               ", pesoInformado='" + pesoInformado + '\'' +
               ", codigoFormatoObjetoInformado='" + codigoFormatoObjetoInformado + '\'' +
               ", alturaInformada='" + alturaInformada + '\'' +
               ", larguraInformada='" + larguraInformada + '\'' +
               ", comprimentoInformado='" + comprimentoInformado + '\'' +
               ", diametroInformado='" + diametroInformado + '\'' +
               ", ncmObjeto='" + ncmObjeto + '\'' +
               ", rfidObjeto='" + rfidObjeto + '\'' +
               ", cienteObjetoNaoProibido='" + cienteObjetoNaoProibido + '\'' +
               ", idAtendimento='" + idAtendimento + '\'' +
               ", solicitarColeta='" + solicitarColeta + '\'' +
               ", codigoObjeto='" + codigoObjeto + '\'' +
               ", dataPrevistaPostagem='" + dataPrevistaPostagem + '\'' +
               ", observacao='" + observacao + '\'' +
               ", modalidadePagamento='" + modalidadePagamento + '\'' +
               ", logisticaReversa='" + logisticaReversa + '\'' +
               ", dataValidadeLogReversa='" + dataValidadeLogReversa + '\'' +
               '}';
    }
}
