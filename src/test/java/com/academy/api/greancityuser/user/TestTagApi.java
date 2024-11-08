package com.academy.api.greancityuser.user;

import com.academy.api.clients.user.TagClient;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class TestTagApi {

    @DataProvider(name = "categories")
    public Object[][] createCategories() {
        return new Object[][]{
                {"ECO_NEWS"},
                {"HABIT"},
                {"EVENT"},
                {"PLACES_FILTER"},
                {"FACT_OF_THE_DAY"}
        };
    }

    @Test(dataProvider = "categories")
    public void verifyAchievements(String category) throws IOException {
        Response response = new TagClient().getTags(category);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, "Status code for category: " + category);
        softAssert.assertAll();
    }
}
