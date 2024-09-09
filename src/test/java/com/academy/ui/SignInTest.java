package com.academy.ui;

import com.academy.ui.pages.BasePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest extends BaseTestRunner {

    private BasePage basePage;

    @BeforeMethod
    public void setUpSignInTest() {
        basePage = new BasePage(driver);
    }

    @Test
    public void verifyOpeningOfForgotPasswordAfterClickingTheLink() {
        boolean isWindowDisplayed = basePage
                .getHeaderComponent()
                .getSignInComponent()
                .getForgotPasswordComponent()
                .isForgotPassportWindowDisplayed();
        Assert.assertTrue(isWindowDisplayed);
    }
}