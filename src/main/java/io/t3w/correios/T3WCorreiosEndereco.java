package io.t3w.correios;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosEndereco {

    @JsonProperty("cep")
    private String cep;
    @JsonProperty("logradouro")
    private String logradouro;
    @JsonProperty("numero")
    private String numero;
    @JsonProperty("complemento")
    private String complemento;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("cidade")
    private String cidade;
    @JsonProperty("uf")
    private String uf;
    @JsonProperty("regiao")
    private String regiao;
    @JsonProperty("pais")
    private String pais;

    public T3WCorreiosEndereco() {
    }

    public T3WCorreiosEndereco(String cep, String logradouro, String numero, String bairro, String cidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public T3WCorreiosEndereco(String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String uf, String pais) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public T3WCorreiosEndereco setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public T3WCorreiosEndereco setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public T3WCorreiosEndereco setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public T3WCorreiosEndereco setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public T3WCorreiosEndereco setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public T3WCorreiosEndereco setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getUf() {
        return uf;
    }

    public T3WCorreiosEndereco setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public String getRegiao() {
        return regiao;
    }

    public T3WCorreiosEndereco setRegiao(String regiao) {
        this.regiao = regiao;
        return this;
    }

    public String getPais() {
        return pais;
    }

    public T3WCorreiosEndereco setPais(String pais) {
        this.pais = pais;
        return this;
    }

    @Override
    public String toString() {
        return "T3WCorreiosEndereco{" +
               "cep='" + cep + '\'' +
               ", logradouro='" + logradouro + '\'' +
               ", numero='" + numero + '\'' +
               ", complemento='" + complemento + '\'' +
               ", bairro='" + bairro + '\'' +
               ", cidade='" + cidade + '\'' +
               ", uf='" + uf + '\'' +
               ", pais='" + pais + '\'' +
               '}';
    }
}
