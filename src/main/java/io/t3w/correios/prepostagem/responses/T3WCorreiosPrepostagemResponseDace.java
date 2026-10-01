package io.t3w.correios.prepostagem.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrepostagemResponseDace {

    /**
     * Códigos dos objetos relativos aos dados de impressão retornados.
     */
    @JsonProperty("objetos")
    private List<String> objetos;

    /**
     * Dados para impressão: PDF em base64 para os tipos completa e resumida, texto para impressão direta para o tipo térmica.
     */
    @JsonProperty("dados")
    private String dados;

    public T3WCorreiosPrepostagemResponseDace() {
    }

    public T3WCorreiosPrepostagemResponseDace(List<String> objetos, String dados) {
        this.objetos = objetos;
        this.dados = dados;
    }

    public List<String> getObjetos() {
        return objetos;
    }

    public T3WCorreiosPrepostagemResponseDace setObjetos(List<String> objetos) {
        this.objetos = objetos;
        return this;
    }

    public String getDados() {
        return dados;
    }

    public T3WCorreiosPrepostagemResponseDace setDados(String dados) {
        this.dados = dados;
        return this;
    }
}
