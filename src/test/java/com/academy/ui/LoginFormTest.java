package com.academy.ui;

import com.academy.ui.pages.greenCity.ProfilePage;
import com.academy.ui.runners.TestRunnerMethodInitDriverHomePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class LoginFormTest extends TestRunnerMethodInitDriverHomePage {

    @DataProvider(name = "checkSuccessfulSignInWithValidCredentials")
    public Object[][] validUserDataProvider() {
        HashMap<String, String> validUserData = new <String, String>HashMap();
        validUserData.put("email", configProperties.getUserEmail());
        validUserData.put("password", configProperties.getUserPassword());
        validUserData.put("id", configProperties.getUserId());
        validUserData.put("name", configProperties.getUserName());
        return new Object[][]{
                {validUserData}
        };
    }

    @Test(dataProvider="checkSuccessfulSignInWithValidCredentials")
    public void checkSuccessfulSignInWithValidCredentials(HashMap<String, String> user) {

        ProfilePage profilePage = page
                .clickSignInLinkAndGetLoginForm()
                .enterEmail(user.get("email"))
                .enterPassword(user.get("password"))
                .clickSignInButtonSuccessfulLogin();


        String expectedUserName = user.get("name");
        String actualUserName = profilePage.getHeaderComponent().getUserNameText();
        String actualUrl = profilePage.getCurrentUrl();
        String expectedUrl = configProperties.getProfilePageGreenCityUrl() + "/" + user.get("id");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUrl, expectedUrl, "Wrong user profile page url");
        softAssert.assertEquals(actualUserName, expectedUserName,"User name doesn't match.");
        softAssert.assertAll();

        profilePage.sleep(3);   //for presentation only

    }

}
