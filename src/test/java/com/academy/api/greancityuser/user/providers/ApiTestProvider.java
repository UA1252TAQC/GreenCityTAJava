package com.academy.api.greancityuser.user.providers;

import org.testng.annotations.DataProvider;

public class ApiTestProvider {

    @DataProvider(name = "verifyAchievementsData")
    public Object[][] verifyAchievementsData() {
        return new Object[][]{
                {"UNACHIEVED", 2, 10, "COMMENT_OR_REPLY_1_TIMES", "Comment or reply first time", "COMMENT_OR_REPLY", 1},
                {"UNACHIEVED", 1, 6, "CREATED_10_NEWS", "Created 10 news", "CREATE_NEWS", 10}
        };
    }
}
