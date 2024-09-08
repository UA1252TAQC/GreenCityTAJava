package com.academy.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigProperties {
    protected final Properties properties;

    public ConfigProperties() {
        properties = new Properties();
        try {
            var fileInputStream = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/resources/credentials.properties"), StandardCharsets.UTF_8));
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected ConfigProperties(String source) {
        properties = new Properties();
        try {
            var fileInputStream = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/resources/" + source), StandardCharsets.UTF_8));
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String getBaseUrl(){
        return properties.getProperty("base.url");
    }
}
