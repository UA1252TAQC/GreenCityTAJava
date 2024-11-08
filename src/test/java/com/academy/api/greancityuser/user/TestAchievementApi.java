package com.academy.api.greancityuser.user;

import com.academy.api.clients.user.AchievementClient;
import com.academy.api.greancityuser.user.providers.ApiTestProvider;
import com.academy.api.models.user.Achievement;
import com.academy.api.models.user.ErrorAchievement;
import com.academy.utils.ValueProvider;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.util.List;


public class TestAchievementApi {
    String token = new ValueProvider().getToken();
    Gson gson = new Gson();

    @Test(dataProvider = "verifyAchievementsData", dataProviderClass = ApiTestProvider.class)
    public void verifyAchievementsByStatusAndCategoryId(String achievementStatus, int achievementCategoryId, int id,
                                                        String title, String nameEng, String achievementCategoryName,
                                                        int condition) throws IOException {
        Response response = new AchievementClient(token).getAchievements(achievementStatus, achievementCategoryId);
        System.out.println(response.getBody().asString());
        SoftAssert softAssert = new SoftAssert();
        List <Achievement> achievements = response.jsonPath().getList("$", Achievement.class);
        softAssert.assertEquals(response.statusCode(), 200);
        for (Achievement item: achievements) {
            if (item.getId() == id) {
                softAssert.assertEquals(item.getTitle(), title);
                softAssert.assertEquals(item.getNameEng(), nameEng);
                softAssert.assertEquals(item.getAchievementCategory().getName(), achievementCategoryName);
                softAssert.assertEquals(item.getCondition(), condition);
            }
        }
        softAssert.assertAll();
    }


    @Test
    public void verifyUnauthorizedErrorInAchievements() throws IOException {
        Response response = new AchievementClient(token+"123").getAchievements("UNACHIEVED", 2);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 401);
        String responseBody = response.getBody().asString();
        ErrorAchievement error = gson.fromJson(responseBody, ErrorAchievement.class);
        softAssert.assertEquals(error.getError(), "Unauthorized");
        softAssert.assertEquals(error.getPath(), "/achievements");
        softAssert.assertAll();
    }
}

