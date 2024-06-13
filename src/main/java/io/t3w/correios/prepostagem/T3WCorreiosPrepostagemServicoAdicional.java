package io.t3w.correios.prepostagem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class T3WCorreiosPrepostagemServicoAdicional {

    @JsonProperty("codigoServicoAdicional")
    private String codigoServicoAdicional;

    @JsonProperty("tipoServicoAdicional")
    private String tipoServicoAdicional;

    @JsonProperty("nomeServicoAdicional")
    private String nomeServicoAdicional;

    @JsonProperty("valorServicoAdicional")
    private String valorServicoAdicional;

    @JsonProperty("valorDeclarado")
    private String valorDeclarado;

    @JsonProperty("siglaServicoAdicional")
    private String siglaServicoAdicional;

    @JsonProperty("orientacaoEntregaVizinho")
    private String orientacaoEntregaVizinho;

    @JsonProperty("tipoChecklist")
    private String tipoChecklist;

    // todo:
    //    @JsonProperty("subitensCheckList")
    //    private List<SubitemCheckList> subitensCheckList = null;

    public T3WCorreiosPrepostagemServicoAdicional() {
    }

    public T3WCorreiosPrepostagemServicoAdicional(String codigoServicoAdicional, String tipoServicoAdicional, String nomeServicoAdicional, String valorServicoAdicional, String valorDeclarado, String siglaServicoAdicional, String orientacaoEntregaVizinho, String tipoChecklist) {
        this.codigoServicoAdicional = codigoServicoAdicional;
        this.tipoServicoAdicional = tipoServicoAdicional;
        this.nomeServicoAdicional = nomeServicoAdicional;
        this.valorServicoAdicional = valorServicoAdicional;
        this.valorDeclarado = valorDeclarado;
        this.siglaServicoAdicional = siglaServicoAdicional;
        this.orientacaoEntregaVizinho = orientacaoEntregaVizinho;
        this.tipoChecklist = tipoChecklist;
    }

    public String getCodigoServicoAdicional() {
        return codigoServicoAdicional;
    }

    public T3WCorreiosPrepostagemServicoAdicional setCodigoServicoAdicional(String codigoServicoAdicional) {
        this.codigoServicoAdicional = codigoServicoAdicional;
        return this;
    }

    public String getTipoServicoAdicional() {
        return tipoServicoAdicional;
    }

    public T3WCorreiosPrepostagemServicoAdicional setTipoServicoAdicional(String tipoServicoAdicional) {
        this.tipoServicoAdicional = tipoServicoAdicional;
        return this;
    }

    public String getNomeServicoAdicional() {
        return nomeServicoAdicional;
    }

    public T3WCorreiosPrepostagemServicoAdicional setNomeServicoAdicional(String nomeServicoAdicional) {
        this.nomeServicoAdicional = nomeServicoAdicional;
        return this;
    }

    public String getValorServicoAdicional() {
        return valorServicoAdicional;
    }

    public T3WCorreiosPrepostagemServicoAdicional setValorServicoAdicional(String valorServicoAdicional) {
        this.valorServicoAdicional = valorServicoAdicional;
        return this;
    }

    public String getValorDeclarado() {
        return valorDeclarado;
    }

    public T3WCorreiosPrepostagemServicoAdicional setValorDeclarado(String valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
        return this;
    }

    public String getSiglaServicoAdicional() {
        return siglaServicoAdicional;
    }

    public T3WCorreiosPrepostagemServicoAdicional setSiglaServicoAdicional(String siglaServicoAdicional) {
        this.siglaServicoAdicional = siglaServicoAdicional;
        return this;
    }

    public String getOrientacaoEntregaVizinho() {
        return orientacaoEntregaVizinho;
    }

    public T3WCorreiosPrepostagemServicoAdicional setOrientacaoEntregaVizinho(String orientacaoEntregaVizinho) {
        this.orientacaoEntregaVizinho = orientacaoEntregaVizinho;
        return this;
    }

    public String getTipoChecklist() {
        return tipoChecklist;
    }

    public T3WCorreiosPrepostagemServicoAdicional setTipoChecklist(String tipoChecklist) {
        this.tipoChecklist = tipoChecklist;
        return this;
    }
}
