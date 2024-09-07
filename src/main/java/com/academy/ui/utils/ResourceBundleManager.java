package com.academy.ui.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleManager {
    private final ResourceBundle resourceBundle;
    public ResourceBundleManager(Locale locale){
        resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public String getValue(String key){
        return resourceBundle.getString(key);
    }
}
