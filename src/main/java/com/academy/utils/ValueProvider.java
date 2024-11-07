package com.academy.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ValueProvider {
    protected final Properties properties;

    public ValueProvider() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/data.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserApiUrl(){
        return properties.getProperty("GreencityUserAPI");
    }

    public String getApiUrl(){
        return properties.getProperty("GreencityAPI");
    }

    public String getToken(){
        return properties.getProperty("GreencityToken");
    }
}
