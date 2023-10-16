package io.t3w.correios.rastreamento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class T3WCorreiosSroPessoa {
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("documento")
    private String documento;
    @JsonProperty("email")
    private String email;
    @JsonProperty("comentario")
    private String comentario;
    @JsonProperty("endereco")
    private T3WCorreiosSroEndereco endereco;

    public String getNome() {
        return nome;
    }

    public T3WCorreiosSroPessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDocumento() {
        return documento;
    }

    public T3WCorreiosSroPessoa setDocumento(String documento) {
        this.documento = documento;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public T3WCorreiosSroPessoa setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getComentario() {
        return comentario;
    }

    public T3WCorreiosSroPessoa setComentario(String comentario) {
        this.comentario = comentario;
        return this;
    }

    public T3WCorreiosSroEndereco getEndereco() {
        return endereco;
    }

    public T3WCorreiosSroPessoa setEndereco(T3WCorreiosSroEndereco endereco) {
        this.endereco = endereco;
        return this;
    }
}
