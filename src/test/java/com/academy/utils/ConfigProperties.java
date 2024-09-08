package com.academy.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private final Properties properties;

    public ConfigProperties() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/credentials.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }
    public String getBaseUrl(){
        return properties.getProperty("base.url");
    }

    public String getUserEmail(){
        return properties.getProperty("user.email");
    }

    public String getUserPassword(){
        return properties.getProperty("user.password");
    }

    public String getUserName(){
        return properties.getProperty("user.name");
    }
}
