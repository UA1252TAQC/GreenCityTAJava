package com.academy.api.clients;


import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class BaseClient {
    protected final String baseURL;
    protected ContentType contentType;
    protected String accessToken;

    public BaseClient(String url, ContentType contentType){
        this.baseURL = url;
        this.contentType = contentType;
        this.accessToken = null;
    }

    public RequestSpecification prepareRequest() {
        RequestSpecification requestSpecification = given()
                .relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(contentType)
                .accept(contentType);
        if (accessToken != null) {
            requestSpecification.header("Authorization", "Bearer " + accessToken);
        }
        return requestSpecification;
    }
}
