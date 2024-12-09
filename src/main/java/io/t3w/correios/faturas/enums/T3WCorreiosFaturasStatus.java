package io.t3w.correios.faturas.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum T3WCorreiosFaturasStatus {
    // Fixme: Swagger dos correios está incompleto após alguma atualização, não mostrando mais os valores dos campos.
    //  Por hora, 'Paga' e 'Estornada' foram os valores que foi possível confirmar para o status, mas possivelmente existem outros.
    PAGA("Paga"),
    ESTORNADA("Estornada");

    private final String descricao;

    T3WCorreiosFaturasStatus(final String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    public static T3WCorreiosFaturasStatus valueOfDescricao(final String descricao) {
        return Stream.of(values()).filter(g -> g.getDescricao().equalsIgnoreCase(descricao)).findFirst().orElse(null);
    }
}
