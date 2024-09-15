package com.academy.ui;

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

        profilePage.sleep(1);   //for presentation only

        String expectedUserProfilePageUrl = getProfilePageUrl()+user.get("id");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(getCurrentUrl(), expectedUserProfilePageUrl, "Wrong user profile page url");
        softAssert.assertTrue(profilePage.getUserNameInHeader().startsWith(user.get("name")),"User name doesn't match.");
        softAssert.assertAll();

        profilePage.sleep(3);   //for presentation only
    }


}
