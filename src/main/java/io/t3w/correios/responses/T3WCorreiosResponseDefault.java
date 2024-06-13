package io.t3w.correios.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosResponseDefault extends Throwable {
    @JsonProperty("msgs")
    private  String[] msgs;

    @JsonProperty("date")
    private  LocalDateTime date;

    @JsonProperty("method")
    private  String method;

    @JsonProperty("path")
    private  String path;

    @JsonProperty("causa")
    private  String causa;

    public T3WCorreiosResponseDefault() {
    }

    @Override
    public String getMessage() {
        return this.msgs != null ? String.join(", ", this.msgs) : "???";
    }
}
