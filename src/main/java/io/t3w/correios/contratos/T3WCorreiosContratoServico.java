package io.t3w.correios.contratos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosContratoServico {
    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("coSegmento")
    private String coSegmento;

    @JsonProperty("descSegmento")
    private String descSegmento;

    public T3WCorreiosContratoServico() {}

    public T3WCorreiosContratoServico(String codigo, String descricao, String coSegmento, String descSegmento) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.coSegmento = coSegmento;
        this.descSegmento = descSegmento;
    }

    public String getCodigo() {
        return codigo;
    }

    public T3WCorreiosContratoServico setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public T3WCorreiosContratoServico setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getCoSegmento() {
        return coSegmento;
    }

    public T3WCorreiosContratoServico setCoSegmento(String coSegmento) {
        this.coSegmento = coSegmento;
        return this;
    }

    public String getDescSegmento() {
        return descSegmento;
    }

    public T3WCorreiosContratoServico setDescSegmento(String descSegmento) {
        this.descSegmento = descSegmento;
        return this;
    }
}
