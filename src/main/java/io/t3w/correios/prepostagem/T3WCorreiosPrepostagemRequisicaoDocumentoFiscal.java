package io.t3w.correios.prepostagem;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Requisição de atualização de uma pré-postagem com a chave de um documento fiscal (NF-e ou DC-e).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrepostagemRequisicaoDocumentoFiscal {
    @JsonProperty("codigoObjeto")
    private String codigoObjeto;
    @JsonProperty("chaveNFe")
    private String chaveNFe;
    @JsonProperty("numeroNotaFiscal")
    private String numeroNotaFiscal;

    public T3WCorreiosPrepostagemRequisicaoDocumentoFiscal() {
    }

    /**
     * Construtor para criação de uma nova instância com parâmetros mínimos requeridos.
     *
     * @param codigoObjeto Código do objeto da pré-postagem a ser atualizada.
     * @param chaveNFe     Chave do documento fiscal: NF-e ou DC-e.
     */
    public T3WCorreiosPrepostagemRequisicaoDocumentoFiscal(String codigoObjeto, String chaveNFe) {
        this.codigoObjeto = codigoObjeto;
        this.chaveNFe = chaveNFe;
    }

    public String getCodigoObjeto() {
        return codigoObjeto;
    }

    public T3WCorreiosPrepostagemRequisicaoDocumentoFiscal setCodigoObjeto(String codigoObjeto) {
        this.codigoObjeto = codigoObjeto;
        return this;
    }

    public String getChaveNFe() {
        return chaveNFe;
    }

    public T3WCorreiosPrepostagemRequisicaoDocumentoFiscal setChaveNFe(String chaveNFe) {
        this.chaveNFe = chaveNFe;
        return this;
    }

    public String getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    public T3WCorreiosPrepostagemRequisicaoDocumentoFiscal setNumeroNotaFiscal(String numeroNotaFiscal) {
        this.numeroNotaFiscal = numeroNotaFiscal;
        return this;
    }

    @Override
    public String toString() {
        return "T3WCorreiosPrepostagemRequisicaoDocumentoFiscal{" +
               "codigoObjeto='" + codigoObjeto + '\'' +
               ", chaveNFe='" + chaveNFe + '\'' +
               ", numeroNotaFiscal='" + numeroNotaFiscal + '\'' +
               '}';
    }
}
