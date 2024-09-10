package com.academy.utils.props;

import java.util.HashMap;
import java.util.Map;
import com.academy.ui.forms.Messages;
import com.google.common.collect.ImmutableMap;

public class LocalizationProperties extends BaseProperties {
    public LocalizationProperties() {
        super("localization.properties");
    }

    public ImmutableMap<String, String> getRegistrationMessages(String language) {
        Map<String, String> localizedMessages = new HashMap<>();

        for (Messages message : Messages.values()) {
            localizedMessages.put(message.toString(), properties.getProperty(language + ".form." + message));
        }

        return ImmutableMap.copyOf(localizedMessages);
    }
}
