package com.academy.ui;

import com.academy.ui.components.SignInComponent;
import com.academy.ui.pages.HomePage;
import com.academy.ui.pages.MyHabitsPage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

        HomePage homePage = loadApplication()
                .openSignInComponent()
                .fillEmailInput(user.get("email"))
                .fillPasswordInput(user.get("password"))
                .clickSignInButton()
                .successfulSignIn();

        Assert.assertEquals(homePage.getUserProfileButtonText(), user.get("name"), "User name must match.");
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
it