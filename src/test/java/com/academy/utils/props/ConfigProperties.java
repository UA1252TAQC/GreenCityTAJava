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

    public String getTestEmail(){
        return properties.getProperty("test.email");
    }

    public String getTestExceedingLongPassword(){
        return properties.getProperty("test.exceeding.long.password");
    }
}
