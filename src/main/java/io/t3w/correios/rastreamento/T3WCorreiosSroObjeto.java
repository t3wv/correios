package io.t3w.correios.rastreamento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosSroObjeto {

    @JsonProperty("codObjeto")
    private String codigo;

    @JsonProperty("dtPrevista")
    private LocalDateTime dataPrevistaEntrega;

    @JsonProperty("contrato")
    private String contrato;

    @JsonProperty("peso")
    private double peso;

    @JsonProperty("formato")
    private String formato;

    @JsonProperty("modalidade")
    private String modalidade;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("tipoPostal")
    private T3WCorreiosSroTipoPostal tipoPostal;

    @JsonProperty("eventos")
    private List<T3WCorreiosSroObjetoEvento> eventos;

    public String getCodigo() {
        return codigo;
    }

    public T3WCorreiosSroObjeto setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public LocalDateTime getDataPrevistaEntrega() {
        return dataPrevistaEntrega;
    }

    public T3WCorreiosSroObjeto setDataPrevistaEntrega(LocalDateTime dataPrevistaEntrega) {
        this.dataPrevistaEntrega = dataPrevistaEntrega;
        return this;
    }

    public String getContrato() {
        return contrato;
    }

    public T3WCorreiosSroObjeto setContrato(String contrato) {
        this.contrato = contrato;
        return this;
    }

    public double getPeso() {
        return peso;
    }

    public T3WCorreiosSroObjeto setPeso(double peso) {
        this.peso = peso;
        return this;
    }

    public String getFormato() {
        return formato;
    }

    public T3WCorreiosSroObjeto setFormato(String formato) {
        this.formato = formato;
        return this;
    }

    public String getModalidade() {
        return formato;
    }

    public T3WCorreiosSroObjeto setModalidade(String modalidade) {
        this.formato = modalidade;
        return this;
    }

    public T3WCorreiosSroTipoPostal getTipoPostal() {
        return tipoPostal;
    }

    public T3WCorreiosSroObjeto setTipoPostal(T3WCorreiosSroTipoPostal tipoPostal) {
        this.tipoPostal = tipoPostal;
        return this;
    }

    public List<T3WCorreiosSroObjetoEvento> getEventos() {
        return eventos;
    }

    public T3WCorreiosSroObjeto setEventos(List<T3WCorreiosSroObjetoEvento> eventos) {
        this.eventos = eventos;
        return this;
    }

    public String getMensagem(){
        return this.mensagem;
    }

    public boolean isValido(){
      return this.mensagem == null;
    };


    // todo: ver melhor forma de fazer isso
    @JsonProperty("tipoPostal")
    private void unpackTipoPostal(Map<String, String> tipoPostal){
        this.tipoPostal = new ObjectMapper().convertValue(tipoPostal, T3WCorreiosSroTipoPostal.class);
    }

    //TODO: revisar criacao dos objectmappers
    @JsonProperty("eventos")
    private void unpackEventos(ArrayNode eventos){
        this.eventos = Arrays.stream(new ObjectMapper().registerModule(new JavaTimeModule()).convertValue(eventos, T3WCorreiosSroObjetoEvento[].class)).toList();
    }

}
