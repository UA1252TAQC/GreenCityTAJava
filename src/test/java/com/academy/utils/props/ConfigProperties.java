package com.academy.utils.props;

public class ConfigProperties extends BaseProperties {
    public ConfigProperties() {
        super("credentials.properties");
    }
    
    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public String getHomePageUrlFragment() {
        return properties.getProperty("home.url.fragment");
    }

    public String getNewsPageUrlFragment() {
        return properties.getProperty("news.url.fragment");
    }

    public String getProfilePageUrlFragment() {
        return properties.getProperty("profile.url.fragment");
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

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
