package com.academy.api.clients.user;

import io.restassured.response.Response;

import java.io.IOException;

public class GreenCityUserClient extends BaseClientGreenCityUser{
    private String token;
    public GreenCityUserClient() throws IOException {
        super();
        this.baseURL += "/user";
    }
    public GreenCityUserClient(String token) throws IOException {
        super();
        this.baseURL += "/user";
        this.authToken = token;
    }

    public Response getProfile(int userId){
        return prepareRequest()
                .log()
                .all()
                .when()
                .get(String.format("%s/%d/profile/", baseURL,  userId));
    }
}
