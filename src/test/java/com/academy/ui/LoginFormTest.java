package com.academy.ui;

import com.academy.ui.components.HeaderComponent;
import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.pages.BasePageGreenCity;
import com.academy.ui.pages.NewsPage;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginFormTestRunner;
import com.beust.ah.A;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class LoginFormTest extends LoginFormTestRunner {


    @Test(dataProvider = "validUserDataProvider")
    public void checkSuccessfulSignInWithValidCredentials(HashMap<String, String> user) {


        ProfilePage profilePage = new NewsPage(driver)
                .clickSignInButtonInHeader()
                .enterEmail(user.get("email"))
                .enterPassword(user.get("password"))
                .clickSignInButtonSuccessfulLogin();

        String profilePageUrl = configProperties.getBaseUrl() + configProperties.getProfilePageUrl() + "/" + user.get("id");

        profilePage.sleep(1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getCurrentUrl(), profilePageUrl, "Wrong destination page url");
        //softAssert.assertTrue(profilePage.getUserNameInHeader().startsWith(user.get("firstName")),"User name must match.");
        softAssert.assertAll();

        profilePage.sleep(3);
    }

    @Test
    public void checkErrorMessageForInvalidPassword() {
        BasePageGreenCity page = new BasePageGreenCity(driver);
        page.setLanguage("en");
        LoginModalComponent loginForm = page.clickSignInButtonInHeader();
        Boolean errorMessage = loginForm.enterEmail(configProperties.getRegisteredUserEmail())
                .enterPassword("******************")
                .clickSignInButton()
                .containsMessage("Bad email or password");
        Assert.assertTrue(errorMessage);
    }
}
