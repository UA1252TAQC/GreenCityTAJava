package com.academy.api.clients;

import io.restassured.response.Response;

import java.io.IOException;

public class LanguageControllerClient extends BaseClient {
    public LanguageControllerClient() throws IOException {
        super();
        this.baseURL += "/languages/codes";
    }

    public Response getLanguages() throws IOException {
        return prepareRequest().get();
    }
}
