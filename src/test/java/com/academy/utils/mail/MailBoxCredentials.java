package com.academy.utils.mail;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MailBoxCredentials {
	private final String id;
	private final String address;
	private final String expiresAt;

	@JsonCreator
	MailBoxCredentials(@JsonProperty("id") String id, 
			@JsonProperty("emailAddress") String emailAddress,
			@JsonProperty("expiresAt") String expiresAt) {
		this.id = id;
		this.address = emailAddress;
		this.expiresAt = expiresAt;
	}
}