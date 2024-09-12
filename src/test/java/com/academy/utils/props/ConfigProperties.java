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

    public String getBaseEmail() {
        return properties.getProperty("base.email");
    }
    public String getBasePassword() {
        return properties.getProperty("base.password");
    }
}
