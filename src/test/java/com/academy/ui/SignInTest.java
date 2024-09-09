package com.academy.ui;

import com.academy.ui.pages.BasePage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import com.academy.utils.ConfigProperties;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest extends BaseTestRunner {

    private BasePage basePage;

    @BeforeMethod
    public void setUpSignInTest() {
        basePage = new HomePage(driver);
    }

    @Test
    public void verifyErrorMessageForInvalidPasswordInUa() {
        ConfigProperties configProperties = new ConfigProperties();
        basePage.getHeaderComponent().selectLanguage("ua");

        boolean isErrorDisplayed = basePage.getHeaderComponent().getSignInComponent()
                .sendEmail(configProperties.getTestEmail())
                .sendPassword(configProperties.getTestPassword())
                .sendForm()
                .verifyErrorMessageForInvalidInputUa("Введено невірний email або пароль");

        Assert.assertTrue(isErrorDisplayed, "Error message is not displayed as expected");
    }
}
