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

    public String getRegisteredUserEmail() {
        return properties.getProperty("registered.user.email");
    }

    public String getRegisteredUserPassword() {
        return properties.getProperty("registered.user.password");
    }

    public String getRegisteredUserId() {
        return properties.getProperty("registered.user.id");
    }

    public String getRegisteredUserName() {
        return properties.getProperty("registered.user.name");
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
