package com.academy.utils.mail;

import com.academy.utils.props.ConfigProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public List<MailBoxCredentials> getAllInboxes() {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(BASE_URL + "/inboxes"))
                    .header("x-api-key", apiKey).GET().build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to get inboxes: " + response.body());
            }

            return objectMapper.readValue(response.body(),
                    new TypeReference<List<MailBoxCredentials>>() {
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MailBoxCredentials createInbox() {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime expiration = now.plusHours(1);
        String expiresAt = expiration.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        String payload = String.format("{\"expiresAt\": \"%s\"}", expiresAt);

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(BASE_URL + "/inboxes"))
                    .header("x-api-key", apiKey).POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 201) {
                throw new RuntimeException("Failed to create inbox: " + response.body());
            }

            return objectMapper.readValue(response.body(), MailBoxCredentials.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Mail getLastMail(String inboxId, Long timeoutMillis) {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder().uri(new URI(BASE_URL + "/waitForLatestEmail?inboxId=" + inboxId
                            + "&timeout=" + timeoutMillis))
                    .header("x-api-key", apiKey).GET().build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to get last email: " + response.body());
            }

            return objectMapper.readValue(response.body(), Mail.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
