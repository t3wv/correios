package io.t3w.correios;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@JsonIgnoreProperties(ignoreUnknown = true)
class T3WCorreiosBearerToken {

    private String ip;
    private LocalDateTime emissao;
    private LocalDateTime expiraEm;
    private ZoneOffset zoneOffset;
    private String token;

    String getIp() {
        return ip;
    }

    T3WCorreiosBearerToken setIp(String ip) {
        this.ip = ip;
        return this;
    }

    LocalDateTime getEmissao() {
        return emissao;
    }

    T3WCorreiosBearerToken setEmissao(LocalDateTime emissao) {
        this.emissao = emissao;
        return this;
    }

    LocalDateTime getExpiraEm() {
        return expiraEm;
    }

    T3WCorreiosBearerToken setExpiraEm(LocalDateTime expiraEm) {
        this.expiraEm = expiraEm;
        return this;
    }

    ZoneOffset getZoneOffset() {
        return zoneOffset;
    }

    T3WCorreiosBearerToken setZoneOffset(ZoneOffset zoneOffset) {
        this.zoneOffset = zoneOffset;
        return this;
    }

    String getToken() {
        return token;
    }

    T3WCorreiosBearerToken setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
               "ip='" + ip + '\'' +
               ", emissao=" + emissao +
               ", expiraEm=" + expiraEm +
               ", zoneOffset=" + zoneOffset +
               ", token='" + token + '\'' +
               '}';
    }
}
