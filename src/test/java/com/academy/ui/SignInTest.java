package com.academy.ui;

import com.academy.ui.pages.BasePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest extends BaseTestRunner {

    private BasePage homePage;

    @BeforeMethod
    public void setUpSignInTest() {
        homePage = new BasePage(driver);
    }

    @Test
    public void verifyForgotPasswordWindowIsDisplayed() {
        boolean isWindowDisplayed = homePage
                .getHeaderComponent()
                .openProfileOrSignInForm()
                .openForgotPasswordAndGetComponent()
                .isForgotPasswordWindowDisplayed();

        Assert.assertTrue(isWindowDisplayed, "Forgot password window is not displayed.");
    }
}