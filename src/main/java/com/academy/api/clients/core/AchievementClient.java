package com.academy.api.clients.core;

import io.restassured.response.Response;

import java.io.IOException;

public class AchievementClient extends BaseClientGreenCity {

    public AchievementClient(String token) throws IOException {
        super();
        this.baseURL += "/achievements";
        this.authToken = token;
    }

    public Response getAchievements(String achievementStatus, int achievementCategoryId){
        return prepareRequest()
                .log()
                .all()
                .when()
                .get(String.format("%s?achievementStatus=%s&achievementCategoryId=%d",
                        baseURL, achievementStatus,achievementCategoryId));
    }
}


