package com.academy.ui;

import com.academy.ui.components.SignInComponent;
import com.academy.ui.pages.HomePage;
import com.academy.ui.pages.MyHabitsPage;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class SignInTest extends BaseTestRunner {

    protected MyHabitsPage loadApplication() {
        return new MyHabitsPage(driver);
    }

    @DataProvider(name = "validUserDataProvider")
    public Object[][] validUserDataProvider() {
        HashMap<String,String> validUserData = new <String,String>HashMap();
        validUserData.put("email", configProperties.getUserEmail());
        validUserData.put("password", configProperties.getUserPassword());
        validUserData.put("name", configProperties.getUserName());
        return new Object[][] {
                { validUserData}
        };
    }

    @Test(dataProvider = "validUserDataProvider")
    public void checkSuccessfulSignInWithValidCredentials(HashMap<String,String> user){

        ProfilePage profilePage = loadApplication()
                .openSignInComponent()
                .fillEmailInput(user.get("email"))
                .fillPasswordInput(user.get("password"))
                .clickSignInSuccess();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.getCurrentUrl().startsWith(configProperties.getBaseUrl() + "/profile"));
        softAssert.assertEquals(profilePage.getUserProfileButtonText(), user.get("name"), "User name must match.");
        softAssert.assertAll();
    }

    @Test(dataProvider = "validUserDataProvider")
    public void checkSignInButtonRemainedInactiveWithFilledPassword(HashMap<String,String> user){

        SignInComponent signInComponent = loadApplication()
                .openSignInComponent()
                .fillPasswordInput(user.get("password"));

        Assert.assertFalse(signInComponent.isSignButtonActive());
    }

    @Test(dataProvider = "validUserDataProvider")
    public void checkSignInButtonRemainedInactiveWithFilledEmail(HashMap<String,String> user){

        SignInComponent signInComponent = loadApplication()
                .openSignInComponent()
                .fillEmailInput(user.get("email"));

        Assert.assertFalse(signInComponent.isSignButtonActive());
    }

}