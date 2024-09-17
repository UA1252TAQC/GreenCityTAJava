package com.academy.ui;

import com.academy.ui.components.ForgotPasswordModalComponent;
import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.providers.LoginFormTestProvider;
import com.academy.ui.runners.TestRunnerMethodInitDriverHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestRunnerMethodInitDriverHomePage {

    @Test(dataProvider = "verifyErrorMessageForExceedingPasswordLengthInUA", dataProviderClass = LoginFormTestProvider.class)
    public void verifyErrorMessageForExceedingPasswordLengthInUA(String email, String password,
                                                                 String expectedErrorMessage) {

        String errorMessage = page.getHeaderComponent()
                .openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickInsideForm()
                .getPasswordField()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, expectedErrorMessage);
    }

    @Test
    public void verifyOpeningForgotPasswordFormAfterClick() {

        boolean isDisplayed = page.getHeaderComponent()
                .openLoginForm()
                .clickForgotPasswordLink()
                .isForgotPasswordWindowDisplayed();

        Assert.assertTrue(isDisplayed);
    }

    @Test(dataProvider = "verifyErrorMessageForInvalidPasswordUA", dataProviderClass = LoginFormTestProvider.class)
    public void verifyErrorMessageForInvalidPasswordUA(String email, String password, String expectedErrorMessage){

        String errorMessage = page.getHeaderComponent()
                .openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButton()
                .clickInsideForm()
                .getPasswordField()
                .getErrorMessage();
        Assert.assertEquals(errorMessage, expectedErrorMessage);
    }

    @Test(dataProvider = "verifyErrorMessageForEmptyEmailAndPasswordEng", dataProviderClass = LoginFormTestProvider.class)
    public void verifyErrorMessageForEmptyEmailAndPasswordEng(String email, String password , String expectedErrorMessage) {
        LoginModalComponent loginForm = page.setLanguage("en")
                .getHeaderComponent()
                .openLoginForm();

        loginForm.enterEmail(email)
                .enterPassword(password)
                .clickInsideForm()
                .clickSignInButton();

        String errorMessage = "";

        if (email.isEmpty()) {
            errorMessage = loginForm.getEmailField().getErrorMessage();
        } else if (password.isEmpty()) {
            errorMessage = loginForm.getPasswordField().getErrorMessage();
        }

        Assert.assertEquals(errorMessage, expectedErrorMessage);
    }

    @Test(dataProvider = "verifyCssAndErrorIsDisplayedInForgotPasswordWithInvalidEmail", dataProviderClass = LoginFormTestProvider.class)
    public void verifyCssAndErrorIsDisplayedInForgotPasswordWithInvalidEmail (String email, String expectedErrorMessage) {

        ForgotPasswordModalComponent forgotPasswordModal= page
                .getHeaderComponent()
                .openLoginForm()
                .clickForgotPasswordLink();

        String errorMessage = forgotPasswordModal.enterEmail(email)
                .clickSignInButton()
                .getEmailField()
                .getErrorMessage();

        boolean isHighlightedInRed = forgotPasswordModal
                .getEmailField()
                .isHighlightedInRed();

        softAssert.assertEquals(errorMessage, expectedErrorMessage + email);
        softAssert.assertTrue(isHighlightedInRed);
        softAssert.assertAll();
    }
}
