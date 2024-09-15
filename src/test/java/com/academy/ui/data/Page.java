package com.academy.ui.data;

import com.academy.utils.props.ConfigProperties;

public enum Page {

    DEFAULT("home.url.fragment"),
    HOME("home.url.fragment"),
    NEWS("news.url.fragment"),
    PROFILE("profile.url.fragment");

    private final String fragmentKey;

    Page(String fragmentKey) {
        this.fragmentKey = fragmentKey;
    }

    public String getUrl(ConfigProperties config) {
        String baseUrl = config.getBaseUrl();
        String fragment = config.getProperty(this.fragmentKey);
        return baseUrl + fragment;
    }
}
