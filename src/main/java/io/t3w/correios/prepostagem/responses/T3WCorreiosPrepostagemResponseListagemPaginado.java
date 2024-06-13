package io.t3w.correios.prepostagem.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.prepostagem.T3WCorreiosPrepostagem;
import io.t3w.correios.responses.T3WCorreiosResponseCustomPage;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrepostagemResponseListagemPaginado {

    @JsonProperty("itens")
    private List<T3WCorreiosPrepostagem> itens;

    @JsonProperty("page")
    private T3WCorreiosResponseCustomPage page;

    public T3WCorreiosPrepostagemResponseListagemPaginado() {
    }

    public T3WCorreiosPrepostagemResponseListagemPaginado(List<T3WCorreiosPrepostagem> itens, T3WCorreiosResponseCustomPage page) {
        this.itens = itens;
        this.page = page;
    }

    public List<T3WCorreiosPrepostagem> getItens() {
        return itens;
    }

    public T3WCorreiosPrepostagemResponseListagemPaginado setItens(List<T3WCorreiosPrepostagem> itens) {
        this.itens = itens;
        return this;
    }

    public T3WCorreiosResponseCustomPage getPage() {
        return page;
    }

    public T3WCorreiosPrepostagemResponseListagemPaginado setPage(T3WCorreiosResponseCustomPage page) {
        this.page = page;
        return this;
    }
}
