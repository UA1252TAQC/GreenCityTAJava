package com.academy.api.clients.core;

import io.restassured.response.Response;

import java.io.IOException;

public class EcoNewsCommentClient extends BaseClientGreenCity {

    public EcoNewsCommentClient() throws IOException {
            new EcoNewsCommentClient(null);
    }

    public EcoNewsCommentClient(String token) throws IOException {
        super();
        this.baseURL = "/eco-news";
        this.authToken = token;
    }

    public Response getEcoNewsCountComments(int newsId) {
        return prepareRequest()
                .log()
                .all()
                .when()
                .get(String.format("%s/eco-news/%d/comments/count",
                        baseURL, newsId));
    }
}
