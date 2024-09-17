package com.academy.utils.props;

public class ConfigProperties extends BaseProperties {
    public ConfigProperties() {
        super("credentials.properties");
    }
    
    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }
    public String getHomePageGreenCityUrl() {
        return getBaseUrl() + "/#/greenCity";
    }
    public String getNewsPageGreenCityUrl() {
        return getBaseUrl() + "/#/news";
    }

    public String getProfilePageGreenCityUrl() {
        return getBaseUrl() + "/#/profile";
    }

    public String getUserEmail() {
        return properties.getProperty("user.email");
    }

    public String getUserPassword() {
        return properties.getProperty("user.password");
    }

    public String getUserId() {
        return properties.getProperty("user.id");
    }

    public String getUserName() {
        return properties.getProperty("user.name");
    }

    public String getEmailToken() {
        return properties.getProperty("email.token");
    }

    public String getGoogleEmail() {
        return properties.getProperty("google.email");
    }

    public String getGooglePassword() {
        return properties.getProperty("google.password");
    }

    public String getGoogleName() {
        return properties.getProperty("google.name");
    }
}
