package com.academy.ui;

import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.providers.LoginFormFieldTestProvider;
import com.academy.ui.runners.TestRunnerMethodInitDriverHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginFormTest extends TestRunnerMethodInitDriverHomePage {
    @Test(dataProvider = "emptyFields", dataProviderClass = LoginFormFieldTestProvider.class)
    public void testErrorForEmptyFields(String language, String email, String password, String expected) {
        LoginModalComponent logInModalComponent = new HomePage(driver)
                .setLanguage(language)
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin();

        Assert.assertEquals(logInModalComponent.getLoginErrorText(), expected);
    }
}
