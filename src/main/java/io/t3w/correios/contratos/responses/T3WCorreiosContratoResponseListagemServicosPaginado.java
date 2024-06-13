package io.t3w.correios.contratos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.contratos.T3WCorreiosContratoServico;
import io.t3w.correios.responses.T3WCorreiosResponseCustomPage;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosContratoResponseListagemServicosPaginado {
    @JsonProperty("itens")
    private List<T3WCorreiosContratoServico> itens;

    @JsonProperty("page")
    private T3WCorreiosResponseCustomPage page;

    public T3WCorreiosContratoResponseListagemServicosPaginado() {
    }

    public T3WCorreiosContratoResponseListagemServicosPaginado(T3WCorreiosResponseCustomPage page, List<T3WCorreiosContratoServico> itens) {
        this.page = page;
        this.itens = itens;
    }

    public List<T3WCorreiosContratoServico> getItens() {
        return itens;
    }

    public T3WCorreiosContratoResponseListagemServicosPaginado setItens(List<T3WCorreiosContratoServico> itens) {
        this.itens = itens;
        return this;
    }

    public T3WCorreiosResponseCustomPage getPage() {
        return page;
    }

    public T3WCorreiosContratoResponseListagemServicosPaginado setPage(T3WCorreiosResponseCustomPage page) {
        this.page = page;
        return this;
    }
}
