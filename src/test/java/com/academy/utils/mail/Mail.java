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

	public String extractActivationLink() {
		String startToken = "<p style=\"margin: 0;\">http";
		String endToken = "</p>";
		int startIndex = body.indexOf(startToken);
		if (startIndex != -1) {
			startIndex += startToken.length() - 4;
			int endIndex = body.indexOf(endToken, startIndex);
			if (endIndex == -1) {
				endIndex = body.indexOf("\"", startIndex);
			}
			if (startIndex < endIndex) {
				return body.substring(startIndex, endIndex).replaceAll("amp;", "");
			}
		}
		throw new IllegalStateException("Token cannot be parsed!");
	}
}
