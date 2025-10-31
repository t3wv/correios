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
        return this.cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public String getNumero() {
        return this.numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public String getUf() {
        return this.uf;
    }

    public String getPais() {
        return this.pais;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
