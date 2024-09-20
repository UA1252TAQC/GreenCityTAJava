package com.academy.utils.jwt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

@Getter
@ToString
public class JwtPayload {
    private final String sub;
    private final List<String> role;
    private final Instant exp;
    private final Instant iat;

    @JsonCreator
    public JwtPayload(@JsonProperty("sub") String sub,
                      @JsonProperty("role") List<String> role,
                      @JsonProperty("exp") String exp,
                      @JsonProperty("iat") String iat) {
        this.sub = sub;
        this.role = role;
        this.exp = Instant.ofEpochSecond(Long.parseLong(exp));
        this.iat = Instant.ofEpochSecond(Long.parseLong(iat));
    }
}
