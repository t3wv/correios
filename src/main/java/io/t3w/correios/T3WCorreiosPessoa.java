package io.t3w.correios;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPessoa {
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("dddTelefone")
    private String dddTelefone;

    @JsonProperty("telefone")
    private String telefone;

    @JsonProperty("dddCelular")
    private String dddCelular;

    @JsonProperty("celular")
    private String celular;

    @JsonProperty("email")
    private String email;

    @JsonProperty("cpfCnpj")
    private String cpfCnpj;

    @JsonProperty("documentoEstrangeiro")
    private String documentoEstrangeiro;

    @JsonProperty("obs")
    private String obs;

    @JsonProperty("endereco")
    private T3WCorreiosEndereco endereco;

    public T3WCorreiosPessoa() {
    }

    public T3WCorreiosPessoa(String nome, T3WCorreiosEndereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public T3WCorreiosPessoa(String nome, String dddTelefone, String telefone, String dddCelular, String celular, String email, String cpfCnpj, String documentoEstrangeiro, String obs, T3WCorreiosEndereco endereco) {
        this.nome = nome;
        this.dddTelefone = dddTelefone;
        this.telefone = telefone;
        this.dddCelular = dddCelular;
        this.celular = celular;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.documentoEstrangeiro = documentoEstrangeiro;
        this.obs = obs;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public T3WCorreiosPessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDddTelefone() {
        return dddTelefone;
    }

    public T3WCorreiosPessoa setDddTelefone(String dddTelefone) {
        this.dddTelefone = dddTelefone;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public T3WCorreiosPessoa setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getDddCelular() {
        return dddCelular;
    }

    public T3WCorreiosPessoa setDddCelular(String dddCelular) {
        this.dddCelular = dddCelular;
        return this;
    }

    public String getCelular() {
        return celular;
    }

    public T3WCorreiosPessoa setCelular(String celular) {
        this.celular = celular;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public T3WCorreiosPessoa setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public T3WCorreiosPessoa setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
        return this;
    }

    public String getDocumentoEstrangeiro() {
        return documentoEstrangeiro;
    }

    public T3WCorreiosPessoa setDocumentoEstrangeiro(String documentoEstrangeiro) {
        this.documentoEstrangeiro = documentoEstrangeiro;
        return this;
    }

    public String getObs() {
        return obs;
    }

    public T3WCorreiosPessoa setObs(String obs) {
        this.obs = obs;
        return this;
    }

    public T3WCorreiosEndereco getEndereco() {
        return endereco;
    }

    public T3WCorreiosPessoa setEndereco(T3WCorreiosEndereco endereco) {
        this.endereco = endereco;
        return this;
    }

    @Override
    public String toString() {
        return "T3WCorreiosPessoa{" +
               "nome='" + nome + '\'' +
               ", dddTelefone='" + dddTelefone + '\'' +
               ", telefone='" + telefone + '\'' +
               ", dddCelular='" + dddCelular + '\'' +
               ", celular='" + celular + '\'' +
               ", email='" + email + '\'' +
               ", cpfCnpj='" + cpfCnpj + '\'' +
               ", documentoEstrangeiro='" + documentoEstrangeiro + '\'' +
               ", obs='" + obs + '\'' +
               ", endereco=" + endereco +
               '}';
    }
}
