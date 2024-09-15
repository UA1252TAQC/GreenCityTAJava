package com.academy.ui;

import com.academy.ui.data.Page;
import com.academy.ui.pages.NewsPage;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginFormTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class LoginFormTest extends LoginFormTestRunner {

    @Test(dataProvider = "validUserDataProvider")
    public void checkSuccessfulSignInWithValidCredentials(HashMap<String, String> user) {

        openPage(getNewsPageUrl());

        ProfilePage profilePage = new NewsPage(driver)
                .clickSignInButtonInHeader()
                .enterEmail(user.get("email"))
                .enterPassword(user.get("password"))
                .clickSignInButtonSuccessfulLogin();

        profilePage.sleep(1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(getCurrentUrl(), getProfilePageUrlByUserId(user.get("id")), "Wrong user profile page url");
        softAssert.assertTrue(profilePage.getUserNameInHeader().startsWith(user.get("name")),"User name doesn't match.");
        softAssert.assertAll();

        profilePage.sleep(3);
    }


}
