package com.academy.utils.props;

public class ConfigProperties extends BaseProperties {
    public ConfigProperties() {
        super("credentials.properties");
    }
    
    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public String getUsername() {
        return properties.getProperty("valid.username");
    }

    public String getPassword() {
        return properties.getProperty("valid.password");
    }

    public String getEmailToken() {
        return properties.getProperty("email.token");
    }
}
