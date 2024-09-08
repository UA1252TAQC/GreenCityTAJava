package com.academy.utils;

import java.util.HashMap;
import java.util.Map;
import com.academy.ui.forms.Messages;
import com.google.common.collect.ImmutableMap;

public class LocalizationProperties extends ConfigProperties {

    public LocalizationProperties() {
        super("localization.properties");
    }

    public ImmutableMap<Messages, String> getRegistrationMessages(String language) {
        Map<Messages, String> localizedMessages = new HashMap<>();

        for (Messages message : Messages.values()) {
            localizedMessages.put(message, properties.getProperty(language + ".form." + message));
        }

        return ImmutableMap.copyOf(localizedMessages);
    }
}
