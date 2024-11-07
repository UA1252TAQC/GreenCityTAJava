package com.academy.api.clients.user;

import com.academy.api.clients.BaseClient;

import java.io.IOException;

public class BaseClientGreenCity extends BaseClient {
    public BaseClientGreenCity() throws IOException
    {
        super();
        this.baseURL = valueProvider.getApiUrl();
    }

}
