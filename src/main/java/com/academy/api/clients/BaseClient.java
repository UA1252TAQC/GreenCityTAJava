package com.academy.api.clients;


import com.academy.utils.ValueProvider;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class BaseClient {
    protected static ValueProvider valueProvider;
    protected String baseURL;
    protected ContentType contentType;
    protected String accessToken;
    protected String authToken;

    public  BaseClient(){
        if (valueProvider ==null){
            valueProvider = new ValueProvider();
        }
        contentType = ContentType.JSON;
    }

    public RequestSpecification prepareRequest() {
        RequestSpecification requestSpecification = given()
                .baseUri(baseURL)
                .contentType(contentType)
                .accept(contentType);
        if(authToken !=null){
            requestSpecification = requestSpecification.header("Authorization", String.format("Bearer %s", authToken));
        }
        return requestSpecification;
    }
}
