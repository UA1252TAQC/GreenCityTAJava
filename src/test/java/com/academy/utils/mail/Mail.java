package com.academy.utils.mail;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Mail {
	private final String id;
	private final String subject;
	private final String body;

	@JsonCreator
	public Mail(@JsonProperty("id") String id,
			@JsonProperty("subject") String subject,
			@JsonProperty("body") String body) {

		this.id = id;
		this.subject = subject;
		this.body = body;
	}
}
