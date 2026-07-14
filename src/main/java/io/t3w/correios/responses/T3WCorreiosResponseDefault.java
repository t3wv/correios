package io.t3w.correios.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosResponseDefault extends Throwable {
    @JsonProperty("msgs")
    private String[] msgs;

    @JsonProperty("date")
    private LocalDateTime date;

    @JsonProperty("method")
    private String method;

    @JsonProperty("path")
    private String path;

    @JsonProperty("causa")
    private String causa;

    @JsonProperty("stackTrace")
    private String apiStackTrace;

    public T3WCorreiosResponseDefault() {
    }

    public String getApiStackTrace() {
        return apiStackTrace;
    }

    public T3WCorreiosResponseDefault setApiStackTrace(String apiStackTrace) {
        this.apiStackTrace = apiStackTrace;
        return this;
    }

    @Override
    public String getMessage() {
        return this.msgs != null ? String.join(", ", this.msgs) : "???";
    }

    public String[] getMsgs() {
        return msgs;
    }

    @JsonSetter("msgs")
    public T3WCorreiosResponseDefault setMsgs(String[] msgs) {
        this.msgs = msgs;
        return this;
    }

    // Coisas da api dos correios... Acusa que erros 400 deveriam vir no padrão do objeto, mas em alguns casos retorna body com { "msg": "texto" }
    @JsonSetter("msg")
    public T3WCorreiosResponseDefault setMsg(String msg) {
        if (msg != null) {
            if (this.msgs == null) {
                this.msgs = new String[]{msg};
            } else {
                String[] newMsgs = new String[this.msgs.length + 1];
                System.arraycopy(this.msgs, 0, newMsgs, 0, this.msgs.length);
                newMsgs[this.msgs.length] = msg;
                this.msgs = newMsgs;
            }
        }
        return this;
    }
}
