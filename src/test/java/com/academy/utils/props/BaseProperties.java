package com.academy.utils.props;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class BaseProperties {
	protected final Properties properties;

    protected BaseProperties(String source) {
        properties = new Properties();
        try {
            var fileInputStream = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/resources/" + source), StandardCharsets.UTF_8));
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
