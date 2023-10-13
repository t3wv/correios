package io.t3w.correios.rastreamento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosSroEndereco {

    private String cep;

    private String logradouro;

    private String complemento;

    private String numero;

    private String bairro;

    private String cidade;

    private String uf;

    private String pais;

    private String telefone;


    public String getCep() {
        return cep;
    }

    public T3WCorreiosSroEndereco setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public T3WCorreiosSroEndereco setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public T3WCorreiosSroEndereco setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public T3WCorreiosSroEndereco setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public T3WCorreiosSroEndereco setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public T3WCorreiosSroEndereco setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getUf() {
        return uf;
    }

    public T3WCorreiosSroEndereco setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public String getPais() {
        return pais;
    }

    public T3WCorreiosSroEndereco setPais(String pais) {
        this.pais = pais;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public T3WCorreiosSroEndereco setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }
}
