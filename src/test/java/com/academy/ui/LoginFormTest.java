package com.academy.ui;

import com.academy.ui.components.ForgotPasswordModalComponent;
import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.pages.greenCity.NewsPage;
import com.academy.ui.providers.LoginFormTestProvider;
import com.academy.ui.pages.greenCity.ProfilePage;
import com.academy.ui.runners.TestRunnerMethodInitDriverHomePage;
import org.openqa.selenium.devtools.v127.page.Page;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class LoginFormTest extends TestRunnerMethodInitDriverHomePage {
    @Test(dataProvider = "emptyFields", dataProviderClass = LoginFormTestProvider.class)
    public void testErrorForEmptyFields(String language, String email, String password, String expected) {
        LoginModalComponent logInModalComponent = new HomePage(driver)
                .setLanguage(language)
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin();

        Assert.assertEquals(logInModalComponent.getLoginErrorText(), expected);
    }

    @Test(dataProvider = "verifyErrorMessageForExceedingPasswordLengthInUA", dataProviderClass = LoginFormTestProvider.class)
    public void verifyErrorMessageForExceedingPasswordLengthInUA(String email, String password, String expectedErrorMessage) {

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
    public void verifyErrorMessageForInvalidPasswordUA(String email, String password, String expectedErrorMessage) {

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

    @Test
    public void testErrorForInvalidPasswordEn() {
        String expectedMessage = "Bad email or password.";

        LoginModalComponent logInModalComponent = new HomePage(driver)
                .setLanguage("En")
                .getHeaderComponent().openLoginForm()
                .enterEmail(configProperties.getRegisteredUserEmail())
                .enterPassword("******************")
                .clickSignInButtonUnsuccessfulLogin();

        String errorMessage = logInModalComponent.getPasswordErrorMessage();

        Assert.assertEquals(errorMessage, expectedMessage);
    }

    @Test(dataProvider = "verifyCssAndErrorIsDisplayedInForgotPasswordWithInvalidEmail", dataProviderClass = LoginFormTestProvider.class)
    public void verifyCssAndErrorIsDisplayedInForgotPasswordWithInvalidEmail(String email, String expectedErrorMessage) {

        ForgotPasswordModalComponent forgotPasswordModal = page
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

    @Test(dataProvider="checkSuccessfulSignInDataProvider", dataProviderClass = LoginFormTestProvider.class)
    public void checkSuccessfulSignIn(String email, String password, String name, String id) {

        ProfilePage profilePage = page
                .getHeaderComponent()
                .openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonSuccessfulLogin();

        String actualUserName = profilePage.getHeaderComponent().getUserNameText();
        String actualUrl = profilePage.getCurrentUrl();
        String expectedUrl = configProperties.getProfilePageGreenCityUrl() + "/" + id;

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUrl, expectedUrl, "Wrong user profile page url");
        softAssert.assertEquals(actualUserName, name,"User name doesn't match.");
        softAssert.assertAll();

        //profilePage.sleep(3);   //for presentation only
    }

    @Test(dataProvider = "checkSignInButtonRemainedInactivePassword", dataProviderClass = LoginFormTestProvider.class)
    public void checkSignInButtonRemainedInactiveWithFilledPassword(String password, String expectedErrorMessage) {
        LoginModalComponent logInModalComponent = new HomePage(driver)
                .getHeaderComponent().openLoginForm()
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(logInModalComponent.isSignInButtonActive(),
                "The 'Login' button should be inactive when entering only the email.");
        softAssert.assertAll();
        logInModalComponent.sleep(5);
    }

    @Test(dataProvider = "checkInSignInButtonRemainedInactiveEmail", dataProviderClass = LoginFormTestProvider.class)
    public void checkSignInButtonRemainedInactiveWithFilledEmail(String email, String expected) {
        LoginModalComponent logInModalComponent = page
                .getHeaderComponent().openLoginForm()
                .enterPassword(email)
                .clickSignInButtonUnsuccessfulLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(logInModalComponent.isSignInButtonActive(),
                "The 'Login' button should be inactive when entering only the password.");
        softAssert.assertAll();
        logInModalComponent.sleep(5);
    }

}