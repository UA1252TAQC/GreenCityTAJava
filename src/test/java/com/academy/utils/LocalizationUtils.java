package com.academy.utils;

import com.academy.ui.forms.Messages;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class LocalizationUtils extends BaseJsonUtils {

    public LocalizationUtils() {
        super("localization.json");
    }

    public ImmutableMap<String, String> getRegistrationMessages(String language) {
        Map<String, String> localizedMessages = new HashMap<>();

        for (Messages message : Messages.values()) {
            JsonNode messageNode = rootNode.path(language).path("form").path(message.toString());
            if (!messageNode.isMissingNode()) {
                localizedMessages.put(message.toString(), messageNode.asText());
            }
        }

        return ImmutableMap.copyOf(localizedMessages);
    }
}
