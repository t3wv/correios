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
    private String codigoSegmento;

    @JsonProperty("descSegmento")
    private String descricaoSegmento;

    public T3WCorreiosContratoServico() {}

    public T3WCorreiosContratoServico(String codigo, String descricao, String codigoSegmento, String descricaoSegmento) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.codigoSegmento = codigoSegmento;
        this.descricaoSegmento = descricaoSegmento;
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

    public String getCodigoSegmento() {
        return codigoSegmento;
    }

    public T3WCorreiosContratoServico setCodigoSegmento(String codigoSegmento) {
        this.codigoSegmento = codigoSegmento;
        return this;
    }

    public String getDescricaoSegmento() {
        return descricaoSegmento;
    }

    public T3WCorreiosContratoServico setDescricaoSegmento(String descricaoSegmento) {
        this.descricaoSegmento = descricaoSegmento;
        return this;
    }
}
