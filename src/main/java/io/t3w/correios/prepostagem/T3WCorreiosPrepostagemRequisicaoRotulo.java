package io.t3w.correios.prepostagem;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrepostagemRequisicaoRotulo {
    @JsonProperty("codigosObjeto")
    private List<String> codigosObjeto;
    @JsonProperty("idCorreios")
    private String idCorreios;
    @JsonProperty("numeroCartaoPostagem")
    private String numeroCartaoPostagem;
    @JsonProperty("tipoRotulo")
    private String tipoRotulo;
    @JsonProperty("formatoRotulo")
    private String formatoRotulo;
    @JsonProperty("idAtendimento")
    private String idAtendimento;
    @JsonProperty("imprimeRemetente")
    private String imprimeRemetente;
    @JsonProperty("idsPrePostagem")
    private List<String> idsPrePostagem;
    @JsonProperty("layoutImpressao")
    private String layoutImpressao;

    public T3WCorreiosPrepostagemRequisicaoRotulo() {
        this.tipoRotulo = "P";
        this.formatoRotulo = "ET";
        this.imprimeRemetente = "S";
        this.layoutImpressao = "PADRAO";
    }

    public T3WCorreiosPrepostagemRequisicaoRotulo(String idCorreios, String numeroCartaoPostagem, String formatoRotulo, String tipoRotulo, List<String> idsPrePostagem) {
        this.idCorreios = idCorreios;
        this.numeroCartaoPostagem = numeroCartaoPostagem;
        this.formatoRotulo = formatoRotulo;
        this.tipoRotulo = tipoRotulo;
        this.idsPrePostagem = idsPrePostagem;
    }

    public String getIdCorreios() {
        return idCorreios;
    }

    public T3WCorreiosPrepostagemRequisicaoRotulo setIdCorreios(String idCorreios) {
        this.idCorreios = idCorreios;
        return this;
    }

    public List<String> getCodigosObjeto() {
        return codigosObjeto;
    }

    public T3WCorreiosPrepostagemRequisicaoRotulo setCodigosObjeto(List<String> codigosObjeto) {
        this.codigosObjeto = codigosObjeto;
        return this;
    }

    public String getNumeroCartaoPostagem() {
        return numeroCartaoPostagem;
    }

    public T3WCorreiosPrepostagemRequisicaoRotulo setNumeroCartaoPostagem(String numeroCartaoPostagem) {
        this.numeroCartaoPostagem = numeroCartaoPostagem;
        return this;
    }

    public String getFormatoRotulo() {
        return formatoRotulo;
    }

    public T3WCorreiosPrepostagemRequisicaoRotulo setFormatoRotulo(String formatoRotulo) {
        this.formatoRotulo = formatoRotulo;
        return this;
    }

    public String getIdAtendimento() {
        return idAtendimento;
    }

    public T3WCorreiosPrepostagemRequisicaoRotulo setIdAtendimento(String idAtendimento) {
        this.idAtendimento = idAtendimento;
        return this;
    }

    public String getImprimeRemetente() {
        return imprimeRemetente;
    }

    public T3WCorreiosPrepostagemRequisicaoRotulo setImprimeRemetente(String imprimeRemetente) {
        this.imprimeRemetente = imprimeRemetente;
        return this;
    }

    public List<String> getIdsPrePostagem() {
        return idsPrePostagem;
    }

    public T3WCorreiosPrepostagemRequisicaoRotulo setIdsPrePostagem(List<String> idsPrePostagem) {
        this.idsPrePostagem = idsPrePostagem;
        return this;
    }

    public String getLayoutImpressao() {
        return layoutImpressao;
    }

    public T3WCorreiosPrepostagemRequisicaoRotulo setLayoutImpressao(String layoutImpressao) {
        this.layoutImpressao = layoutImpressao;
        return this;
    }

    public String getTipoRotulo() {
        return tipoRotulo;
    }

    public T3WCorreiosPrepostagemRequisicaoRotulo setTipoRotulo(String tipoRotulo) {
        this.tipoRotulo = tipoRotulo;
        return this;
    }

    @Override
    public String toString() {
        return "T3WCorreiosPrepostagemRotulo{" +
               "codigosObjeto=" + codigosObjeto +
               ", idCorreios='" + idCorreios + '\'' +
               ", numeroCartaoPostagem='" + numeroCartaoPostagem + '\'' +
               ", formatoRotulo='" + formatoRotulo + '\'' +
               ", idAtendimento='" + idAtendimento + '\'' +
               ", imprimeRemetente='" + imprimeRemetente + '\'' +
               ", idsPrePostagem=" + idsPrePostagem +
               ", layoutImpressao='" + layoutImpressao + '\'' +
               '}';
    }
}
