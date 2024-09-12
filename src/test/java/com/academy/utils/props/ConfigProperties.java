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
