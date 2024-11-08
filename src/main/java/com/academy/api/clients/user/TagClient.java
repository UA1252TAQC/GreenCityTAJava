package com.academy.api.clients.user;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

public class TagClient extends BaseClientGreenCity{
    public TagClient() throws IOException {
        super();
        //this.baseURL += ;
    }

    public Response getTags(String type){
        RequestSpecification httpRequest = prepareRequest();
        return httpRequest.queryParam("type", type)
                .log().all()
                .get("/tags/v2/search");
    }
}
