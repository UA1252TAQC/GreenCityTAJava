package com.academy.api.clients.user;

import com.academy.api.clients.BaseClient;

import java.io.IOException;

public class BaseClientGreenCityUser extends BaseClient {
    public BaseClientGreenCityUser() throws IOException
    {
        super();
        this.baseURL = valueProvider.getUserApiUrl();
    }

}
