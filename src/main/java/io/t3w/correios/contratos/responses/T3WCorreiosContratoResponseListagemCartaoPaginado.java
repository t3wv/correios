package io.t3w.correios.contratos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.contratos.T3WCorreiosContratoCartaoPostagem;
import io.t3w.correios.responses.T3WCorreiosResponseCustomPage;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosContratoResponseListagemCartaoPaginado {
    @JsonProperty("itens")
    private List<T3WCorreiosContratoCartaoPostagem> itens;

    @JsonProperty("page")
    private T3WCorreiosResponseCustomPage page;

    public T3WCorreiosContratoResponseListagemCartaoPaginado() {
    }

    public T3WCorreiosContratoResponseListagemCartaoPaginado(T3WCorreiosResponseCustomPage page, List<T3WCorreiosContratoCartaoPostagem> itens) {
        this.page = page;
        this.itens = itens;
    }

    public List<T3WCorreiosContratoCartaoPostagem> getItens() {
        return itens;
    }

    public T3WCorreiosContratoResponseListagemCartaoPaginado setItens(List<T3WCorreiosContratoCartaoPostagem> itens) {
        this.itens = itens;
        return this;
    }

    public T3WCorreiosResponseCustomPage getPage() {
        return page;
    }

    public T3WCorreiosContratoResponseListagemCartaoPaginado setPage(T3WCorreiosResponseCustomPage page) {
        this.page = page;
        return this;
    }
}
