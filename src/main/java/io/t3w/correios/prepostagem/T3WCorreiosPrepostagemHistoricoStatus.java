package io.t3w.correios.prepostagem;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.t3w.correios.prepostagem.enums.T3WCorreiosPrepostagemStatus;

import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosPrepostagemHistoricoStatus {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private T3WCorreiosPrepostagemStatus status;

    @JsonProperty("dataHora")
    private LocalDateTime dataHora;

    public String getId() {
        return id;
    }

    public T3WCorreiosPrepostagemHistoricoStatus setId(String id) {
        this.id = id;
        return this;
    }

    public T3WCorreiosPrepostagemStatus getStatus() {
        return status;
    }

    public T3WCorreiosPrepostagemHistoricoStatus setStatus(T3WCorreiosPrepostagemStatus status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public T3WCorreiosPrepostagemHistoricoStatus setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
        return this;
    }

    @Override
    public String toString() {
        return "T3WCorreiosPrepostagemHistoricoStatus{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", dataHora=" + dataHora +
                '}';
    }
}
