package com.academy.ui;

import com.academy.ui.pages.BasePage;
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
    public void checkSuccessfulLogin(HashMap<String,String> user){


        HomePage homePage = loadApplication()
                .openSignInModal()
                .successfulLogin(user.get("email"), user.get("password"));

        Assert.assertEquals(user.get("name"), homePage.getHeaderProfileLinkText(), "User name must match.");

        homePage.sleep(3); // for presentation only
    }
}
