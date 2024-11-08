package com.academy.api.greencity.core;

import com.academy.api.clients.core.EcoNewsCommentClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class TestEcoNewsCommentApi {

    public static final int ECO_NEWS_ID = 1500;

    @Test
    public void verifyAllCommentsCountForEcoNews() throws IOException {

        Response response = new EcoNewsCommentClient().getEcoNewsCountComments(ECO_NEWS_ID);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertEquals(response.jsonPath().getInt(""), 10);
        softAssert.assertAll();
    }
}
