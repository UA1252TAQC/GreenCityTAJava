package com.academy.ui;

import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.components.RegistrationModalComponent;
import com.academy.ui.pages.BasePageGreenCity;
import com.academy.ui.pages.HomePage;
import com.academy.ui.pages.NewsPage;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.BaseTestRunner;
import com.academy.ui.runners.LoginFormTestRunner;
import org.testng.Assert;
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
        softAssert.assertAll();

        profilePage.sleep(3);
    }

    @Test(dataProvider = "validUserDataProvider")
    public void checkSignInButtonRemainedInactiveWithFilledPassword(HashMap<String,String> user){
        LoginModalComponent loginModal = new HomePage(driver)
                .clickSignInButtonInHeader();

        loginModal.enterPassword(user.get("password"));
        loginModal.sleep(1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(loginModal.isSignInButtonActive(),
                "The 'Login' button should be inactive when entering only the password.");
        softAssert.assertAll();
        loginModal.sleep(3);
    }

    @Test(dataProvider = "validUserDataProvider")
    public void checkSignInButtonRemainedInactiveWithFilledEmail(HashMap<String,String> user){
        LoginModalComponent loginModal = new HomePage(driver)
                .clickSignInButtonInHeader();

        loginModal.enterEmail(user.get("email"));
        loginModal.sleep(1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(loginModal.isSignInButtonActive(),
                "The 'Login' button should be inactive when entering only the email.");
        softAssert.assertAll();
        loginModal.sleep(3);
    }
}
