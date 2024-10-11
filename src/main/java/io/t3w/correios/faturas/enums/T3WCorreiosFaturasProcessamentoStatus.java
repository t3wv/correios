package io.t3w.correios.faturas.enums;

public enum T3WCorreiosFaturasProcessamentoStatus {
    SOLICITADO("Solicitado"),
    EM_EXECUCAO("Em execução"),
    ERRO("Erro"),
    SUCESSO("Sucesso"),
    EXCLUIDO("Excluído");

    private final String descricao;

    T3WCorreiosFaturasProcessamentoStatus(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
