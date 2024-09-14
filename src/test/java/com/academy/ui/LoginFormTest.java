package com.academy.ui;

import com.academy.ui.pages.NewsPage;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.BaseTestRunner;
import com.academy.ui.runners.LoginFormTestRunner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.SQLOutput;
import java.util.HashMap;

public class LoginFormTest extends LoginFormTestRunner {




    @Test(dataProvider = "validUserDataProvider")
    public void checkSuccessfulSignInWithValidCredentials(HashMap<String, String> user) {



        ProfilePage profilePage = new NewsPage(driver)
                .clickSignInButtonInHeader()
                .enterEmail(user.get("email"))
                .enterPassword(user.get("password"))
                .clickSignInButtonSuccessfulLogin();

        String profilePageUrl = configProperties.getBaseUrl() + configProperties.getProfilePageUrl()+"/"+user.get("id");

        profilePage.sleep(1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getCurrentUrl(), profilePageUrl, "Wrong destination page url");
        //softAssert.assertTrue(profilePage.getUserNameInHeader().startsWith(user.get("firstName")),"User name must match.");
        softAssert.assertAll();

        profilePage.sleep(3);
    }
}
