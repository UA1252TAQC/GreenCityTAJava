package com.academy.api.greencity.core;

import com.academy.api.clients.core.AchievementClient;
import com.academy.api.models.user.Achievement;
import com.academy.utils.ValueProvider;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;



public class TestAchievementApi {
    String token = new ValueProvider().getToken();

    @Test
    public void verifyAchievements() throws IOException {
        Response response = new AchievementClient(token).getAchievements("UNACHIEVED", 2);
        SoftAssert softAssert = new SoftAssert();
        List <Achievement> achievements = response.jsonPath().getList("$", Achievement.class);
        softAssert.assertEquals(response.statusCode(), 200);
        for (Achievement item: achievements) {
            if (item.getId() == 10) {
                softAssert.assertEquals(item.getTitle(), "COMMENT_OR_REPLY_1_TIMES");
                softAssert.assertEquals(item.getNameEng(), "Comment or reply first time");
                softAssert.assertEquals(item.getAchievementCategory().getName(), "COMMENT_OR_REPLY");
                softAssert.assertEquals(item.getCondition(), 1);
            }
        }
        softAssert.assertAll();
    }
}

