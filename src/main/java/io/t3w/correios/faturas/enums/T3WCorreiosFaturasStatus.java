package io.t3w.correios.faturas.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum T3WCorreiosFaturasStatus {
    // Fixme: Swagger dos correios está incompleto após alguma atualização, não mostrando mais os valores dos campos.
    //  Por hora, 'Paga', 'Estornada' e 'Em aberto' foram os valores que foi possível confirmar para o status, mas possivelmente existem outros.
    //  Adicionado desconhecido para não ficar vazio caso aconteça um novo tipo.
    @JsonEnumDefaultValue
    DESCONHECIDO(0,"Desconhecido"),
    PAGA(1,"Paga"),
    EM_ABERTO(2,"Em aberto"),
    ESTORNADA(3,"Estornada");

    private final int codigo;
    private final String descricao;

    T3WCorreiosFaturasStatus(final int codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public static T3WCorreiosFaturasStatus valueOfDescricao(final String descricao) {
        return Stream.of(values()).filter(g -> g.getDescricao().equalsIgnoreCase(descricao)).findFirst().orElse(null);
    }
}
