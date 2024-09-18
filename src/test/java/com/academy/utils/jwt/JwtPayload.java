package com.academy.utils.jwt;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JwtPayload {
    private String sub;
    private List<String> role;
    private Instant exp;
    private Instant iat;

    @JsonCreator
	public JwtPayload(@JsonProperty("sub") String sub,
			@JsonProperty("role") List<String> role,
			@JsonProperty("exp") String exp,
            @JsonProperty("iat") String iat) {
                System.out.println(sub + " " + role + " " + exp + " " + iat);
		this.sub = sub;
		this.role = role;
		this.exp = Instant.ofEpochSecond(Long.parseLong(exp));
		this.iat = Instant.ofEpochSecond(Long.parseLong(iat));
	}
}
