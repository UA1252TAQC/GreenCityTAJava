package com.academy.ui;

import com.academy.ui.runners.BaseTestRunner;
import com.academy.ui.pages.SignInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignInTest extends BaseTestRunner {

    @Test
    public void verifySignInButtonActivation() {
        SignInPage signInPage = new SignInPage(driver);

        signInPage.openSignInWindow();
        signInPage.enterEmail("test@mail.com");
        signInPage.enterPassword("Test1234!");

        // Check if the 'Sign in' button is active and highlight color
        Assert.assertTrue(signInPage.isSubmitButtonActive(), "The 'Sign in' button should be active");

        // Use the actual color value obtained from the application
        String actualColor = signInPage.getSubmitButtonColor();
        String expectedColor = "rgba(19, 170, 87, 1)"; // Adjust this color based on actual application color
        Assert.assertEquals(actualColor, expectedColor, "The 'Sign in' button color should be as expected");

        // Optionally, submit and verify further
        signInPage.clickSubmit();
    }

    @Test
    public void verifySignInButtonInactiveWithOnlyEmail() {
        SignInPage signInPage = new SignInPage(driver);

        signInPage.openSignInWindow();
        signInPage.enterEmail("test@mail.com");

        // Проверьте, что кнопка 'Sign in' неактивна
        Assert.assertFalse(signInPage.isSubmitButtonActive(), "The 'Sign in' button should be inactive when only email is entered");
    }

    @Test
    public void verifySignInButtonInactiveWithOnlyPassword() {
        SignInPage signInPage = new SignInPage(driver);

        signInPage.openSignInWindow();
        signInPage.enterPassword("Test1234!");

        // Проверьте, что кнопка 'Sign in' неактивна
        Assert.assertFalse(signInPage.isSubmitButtonActive(), "The 'Sign in' button should be inactive when only password is entered");
    }
}
