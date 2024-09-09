package com.academy.utils.mail;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.academy.utils.ConfigProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MailHttpClient {
	private static final String BASE_URL = "https://api.mailslurp.com";
	private final HttpClient httpClient;
	private final String apiKey;
	private final ObjectMapper objectMapper;

	public MailHttpClient() {
		var properties = new ConfigProperties();
		this.apiKey = properties.getEmailToken();

		this.httpClient = HttpClient.newBuilder().build();
		this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public List<MailBoxCredentials> getAllInboxes() throws Exception {
		String endpoint = "/inboxes";
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(BASE_URL + endpoint))
				.header("x-api-key", apiKey).GET().build();

		HttpResponse<String> response =
				httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		if (response.statusCode() == 200) {
			return objectMapper.readValue(response.body(), new TypeReference<List<MailBoxCredentials>>() {});
		} else {
			throw new RuntimeException("Failed to get inboxes: " + response.body());
		}
	}

	public MailBoxCredentials createInbox() throws Exception {
		String endpoint = "/inboxes";

		ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime expiration = now.plusHours(24);
        String expiresAt = expiration.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        
		String payload = String.format("{\"expiresAt\": \"%s\"}", expiresAt);

		HttpRequest request = HttpRequest.newBuilder().uri(new URI(BASE_URL + endpoint))
				.header("x-api-key", apiKey)
				.POST(HttpRequest.BodyPublishers.ofString(payload))
				.build();
				

		HttpResponse<String> response =
				httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		if (response.statusCode() == 201) {
			return objectMapper.readValue(response.body(), MailBoxCredentials.class);
		} else {
			throw new RuntimeException("Failed to create inbox: " + response.body());
		}
	}

	public Mail getLastMail(String inboxId, Long timeoutMillis) throws Exception {
		String endpoint = "/waitForLatestEmail?inboxId=" + inboxId + "&timeout=" + timeoutMillis;
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(BASE_URL + endpoint))
				.header("x-api-key", apiKey).GET().build();

		HttpResponse<String> response =
				httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		if (response.statusCode() == 200) {
			return objectMapper.readValue(response.body(), Mail.class);
		} else {
			throw new RuntimeException("Failed to get last email: " + response.body());
		}
	}
}
