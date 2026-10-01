package io.t3w.correios.prepostagem;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.prepostagem.enums.T3WCorreiosPrepostagemTipoDace;

import java.util.List;

/**
 * Requisição de impressão do DACE (Documento Auxiliar da Declaração de Conteúdo Eletrônica) de pré-postagens com DC-e emitida pelos Correios.
 *
 * <p>É obrigatório informar {@code codigosObjetos} ou {@code idsPrePostagens}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrepostagemRequisicaoDace {
    @JsonProperty("codigosObjetos")
    private List<String> codigosObjetos;
    @JsonProperty("idsPrePostagens")
    private List<String> idsPrePostagens;
    @JsonProperty("tipoDace")
    private T3WCorreiosPrepostagemTipoDace tipoDace;

    public T3WCorreiosPrepostagemRequisicaoDace() {
    }

    public T3WCorreiosPrepostagemRequisicaoDace(T3WCorreiosPrepostagemTipoDace tipoDace, List<String> codigosObjetos, List<String> idsPrePostagens) {
        this.tipoDace = tipoDace;
        this.codigosObjetos = codigosObjetos;
        this.idsPrePostagens = idsPrePostagens;
    }

    public List<String> getCodigosObjetos() {
        return codigosObjetos;
    }

    public T3WCorreiosPrepostagemRequisicaoDace setCodigosObjetos(List<String> codigosObjetos) {
        this.codigosObjetos = codigosObjetos;
        return this;
    }

    public List<String> getIdsPrePostagens() {
        return idsPrePostagens;
    }

    public T3WCorreiosPrepostagemRequisicaoDace setIdsPrePostagens(List<String> idsPrePostagens) {
        this.idsPrePostagens = idsPrePostagens;
        return this;
    }

    public T3WCorreiosPrepostagemTipoDace getTipoDace() {
        return tipoDace;
    }

    public T3WCorreiosPrepostagemRequisicaoDace setTipoDace(T3WCorreiosPrepostagemTipoDace tipoDace) {
        this.tipoDace = tipoDace;
        return this;
    }

    @Override
    public String toString() {
        return "T3WCorreiosPrepostagemRequisicaoDace{" +
               "codigosObjetos=" + codigosObjetos +
               ", idsPrePostagens=" + idsPrePostagens +
               ", tipoDace=" + tipoDace +
               '}';
    }
}
