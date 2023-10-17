package io.t3w.correios.rastreamento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosSroObjetoEvento {

    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("tipo")
    private String tipo;

    @JsonProperty("dtHrCriado")
    private LocalDateTime data;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("estacao")
    private String estacao;

    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("detalhe")
    private String detalhe;

    @JsonProperty("descCodigo")
    private String descricaoCodigo;

    @JsonProperty("dtHrGravado")
    private String dataGravacao;

    @JsonProperty("recebedor")
    private T3WCorreiosSroPessoa recebedor;

    @JsonProperty("unidade")
    private T3WCorreiosSroUnidade unidadeTratamento;

    @JsonProperty("entregadorExterno")
    private T3WCorreiosSroPessoa entregador;

    @JsonProperty("remetente")
    private T3WCorreiosSroPessoa remetente;

    @JsonProperty("destinatario")
    private T3WCorreiosSroPessoa destinatario;

    @JsonProperty("dtLimiteRetirada")
    private LocalDate dataLimiteRetirada;
    @JsonProperty("comentario")
    private String comentario;

    @JsonProperty("unidadeDestino")
    private T3WCorreiosSroUnidade unidadeDestino;

    @JsonProperty("codLista")
    private String codigoListaRecipiente;


    public T3WCorreiosSroObjetoEvento() {
    }

    public T3WCorreiosSroObjetoEvento(String codigo, String tipo, LocalDateTime data, String descricao, T3WCorreiosSroUnidade unidade) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.data = data;
        this.descricao = descricao;
        this.unidadeTratamento = unidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public T3WCorreiosSroObjetoEvento setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public T3WCorreiosSroObjetoEvento setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public LocalDateTime getData() {
        return data;
    }

    public T3WCorreiosSroObjetoEvento setData(LocalDateTime data) {
        this.data = data;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public T3WCorreiosSroObjetoEvento setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public T3WCorreiosSroUnidade getUnidadeTratamento() {
        return unidadeTratamento;
    }

    public T3WCorreiosSroObjetoEvento setUnidadeTratamento(T3WCorreiosSroUnidade unidadeTratamento) {
        this.unidadeTratamento = unidadeTratamento;
        return this;
    }

    public String getEstacao() {
        return estacao;
    }

    public T3WCorreiosSroObjetoEvento setEstacao(String estacao) {
        this.estacao = estacao;
        return this;
    }

    public String getUsuario() {
        return usuario;
    }

    public T3WCorreiosSroObjetoEvento setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public T3WCorreiosSroObjetoEvento setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public T3WCorreiosSroObjetoEvento setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public T3WCorreiosSroObjetoEvento setDetalhe(String detalhe) {
        this.detalhe = detalhe;
        return this;
    }

    public String getDescricaoCodigo() {
        return descricaoCodigo;
    }

    public T3WCorreiosSroObjetoEvento setDescricaoCodigo(String descricaoCodigo) {
        this.descricaoCodigo = descricaoCodigo;
        return this;
    }

    public String getDataGravacao() {
        return dataGravacao;
    }

    public T3WCorreiosSroObjetoEvento setDataGravacao(String dataGravacao) {
        this.dataGravacao = dataGravacao;
        return this;
    }

    public T3WCorreiosSroPessoa getRecebedor() {
        return recebedor;
    }

    public T3WCorreiosSroObjetoEvento setRecebedor(T3WCorreiosSroPessoa recebedor) {
        this.recebedor = recebedor;
        return this;
    }

    public T3WCorreiosSroPessoa getEntregador() {
        return entregador;
    }

    public T3WCorreiosSroObjetoEvento setEntregador(T3WCorreiosSroPessoa entregador) {
        this.entregador = entregador;
        return this;
    }

    public T3WCorreiosSroPessoa getRemetente() {
        return remetente;
    }

    public T3WCorreiosSroObjetoEvento setRemetente(T3WCorreiosSroPessoa remetente) {
        this.remetente = remetente;
        return this;
    }

    public T3WCorreiosSroPessoa getDestinatario() {
        return destinatario;
    }

    public T3WCorreiosSroObjetoEvento setDestinatario(T3WCorreiosSroPessoa destinatario) {
        this.destinatario = destinatario;
        return this;
    }

    public LocalDate getDataLimiteRetirada() {
        return dataLimiteRetirada;
    }

    public T3WCorreiosSroObjetoEvento setDataLimiteRetirada(LocalDate dataLimiteRetirada) {
        this.dataLimiteRetirada = dataLimiteRetirada;
        return this;
    }

    public String getComentario() {
        return comentario;
    }

    public T3WCorreiosSroObjetoEvento setComentario(String comentario) {
        this.comentario = comentario;
        return this;
    }

    public T3WCorreiosSroUnidade getUnidadeDestino() {
        return unidadeDestino;
    }

    public T3WCorreiosSroObjetoEvento setUnidadeDestino(T3WCorreiosSroUnidade unidadeDestino) {
        this.unidadeDestino = unidadeDestino;
        return this;
    }

    public String getCodigoListaRecipiente() {
        return codigoListaRecipiente;
    }

    public T3WCorreiosSroObjetoEvento setCodigoListaRecipiente(String codigoListaRecipiente) {
        this.codigoListaRecipiente = codigoListaRecipiente;
        return this;
    }
}
