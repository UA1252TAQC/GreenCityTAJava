package com.academy.utils.props;

public class ConfigProperties extends BaseProperties {
    public ConfigProperties() {
        super("credentials.properties");
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public String getEmailToken() {
        return properties.getProperty("email.token");
    }

    public String getEmail() {
        return properties.getProperty("email");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}
