package com.academy.api.language_controller;

import com.academy.api.clients.LanguageControllerClient;
import com.academy.api.clients.user.AchievementClient;
import com.academy.api.models.user.Achievement;
import com.academy.utils.ValueProvider;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class TestLanguageControllerApi {
    String token = new ValueProvider().getToken();

    @Test
    public void verifyLanguages() throws IOException {
        Response response = new LanguageControllerClient().getLanguages();
        SoftAssert softAssert = new SoftAssert();
        List<String> languages = response.jsonPath().get();
        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertEquals(languages.get(0),"ua");
        softAssert.assertEquals(languages.get(1),"en");
        softAssert.assertAll();
    }
}
