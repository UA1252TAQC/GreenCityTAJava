package com.academy.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class LocalizationUtils extends BaseJsonParser {

    public LocalizationUtils() {
        super("localization.json");
    }

    public ImmutableMap<String, String> getFormMessages(String language) {
        Map<String, String> localizedMessages = new HashMap<>();

        JsonNode formNode = rootNode.path("form").path(language);
        formNode.fieldNames().forEachRemaining(key -> {
            JsonNode messageNode = formNode.path(key);
            if (!messageNode.isMissingNode()) {
                localizedMessages.put(key, messageNode.asText());
            }
        });

        return ImmutableMap.copyOf(localizedMessages);
    }

}
