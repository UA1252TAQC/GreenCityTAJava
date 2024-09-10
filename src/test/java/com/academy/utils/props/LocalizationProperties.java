package com.academy.utils.props;

import com.academy.ui.forms.Messages;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class LocalizationProperties {
    private final JsonNode rootNode;

    public LocalizationProperties() throws IOException {
        // Используем ObjectMapper для работы с JSON
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("localization.json")) {
            if (is == null) {
                throw new IOException("Файл localization.json не найден");
            }
            rootNode = mapper.readTree(is);
        }
    }

    public ImmutableMap<String, String> getRegistrationMessages(String language) {
        Map<String, String> localizedMessages = new HashMap<>();

        for (Messages message : Messages.values()) {
            // Извлекаем сообщение для каждого ключа из JSON по языку
            JsonNode messageNode = rootNode.path(language).path("form").path(message.toString());
            if (!messageNode.isMissingNode()) {
                localizedMessages.put(message.toString(), messageNode.asText());
            }
        }

        return ImmutableMap.copyOf(localizedMessages);
    }
}
