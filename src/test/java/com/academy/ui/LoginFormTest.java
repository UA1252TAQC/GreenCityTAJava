package com.academy.ui;

import com.academy.ui.components.ForgotPasswordModalComponent;
import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.providers.LoginFormTestProvider;
import com.academy.ui.pages.greenCity.ProfilePage;
import com.academy.ui.runners.TestRunnerMethodInitDriverHomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class LoginFormTest extends TestRunnerMethodInitDriverHomePage {
    @Test(dataProvider = "verifyErrorMessageForEmptyEmailAndOrPassword", dataProviderClass = LoginFormTestProvider.class)
    public void verifyErrorMessageForEmptyEmailAndOrPassword(String language, String email, String password, String expected) {
        String errorMessage = page.setLanguage(language)
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin()
                .getLoginErrorText();

        Assert.assertEquals(errorMessage, expected);
    }

    @Test(dataProvider = "verifyErrorMessageForEmptyEmailAndPasswordEng", dataProviderClass = LoginFormTestProvider.class)
    public void verifyErrorMessageForEmptyEmailAndPasswordEng(String email, String password, String expectedErrorMessage) {
        String errorMessage = page.setLanguage("en")
                .getHeaderComponent()
                .openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickInsideForm()
                .clickSignInButton()
                .getPasswordField()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, expectedErrorMessage);
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

    @Test(dataProvider = "registeredUserCredentials", dataProviderClass = LoginFormTestProvider.class)
    public void checkSuccessfulSignIn(String email, String password, String name, String id) {

        ProfilePage profilePage = page
                .getHeaderComponent()
                .openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonSuccessfulLogin();

        String actualUserName = profilePage
                .getHeaderComponent()
                .getUserNameText();

        String actualUrl = profilePage
                .getCurrentUrl();

        String expectedUrl = configProperties.getProfilePageGreenCityUrl() + "/" + id;

        softAssert.assertEquals(actualUrl, expectedUrl, "Wrong user profile page url");
        softAssert.assertEquals(actualUserName, name, "User name doesn't match.");
        softAssert.assertAll();
    }

    @Test(dataProvider = "checkSignInButtonRemainedInactivePassword", dataProviderClass = LoginFormTestProvider.class)
    public void checkSignInButtonRemainedInactiveWithFilledPassword(String password) {
        LoginModalComponent logInModalComponent = new HomePage(driver)
                .getHeaderComponent().openLoginForm()
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin();

        logInModalComponent.sleep(3);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(logInModalComponent.isSignInButtonActive(),
                "The 'Login' button should be inactive when entering only the email.");
        softAssert.assertAll();
    }

    @Test(dataProvider = "checkInSignInButtonRemainedInactiveEmail", dataProviderClass = LoginFormTestProvider.class)
    public void checkSignInButtonRemainedInactiveWithFilledEmail(String email) {
        LoginModalComponent logInModalComponent = new HomePage(driver)
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .clickSignInButtonUnsuccessfulLogin();

        logInModalComponent.sleep(3);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(logInModalComponent.isSignInButtonActive(),
                "The 'Login' button should be inactive when entering only the password.");
        softAssert.assertAll();
    }

    @Test(dataProvider = "checkPasswordLessThan8CharactersEN", dataProviderClass = LoginFormTestProvider.class)
    public void checkPasswordLessThan8CharactersTest(String email, String password, String expectedErrorMessage) {
        LoginModalComponent logInModalComponent = page
                .setLanguage("en")
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(logInModalComponent.isErrorMessageDisplayed(),
                "The error message should be displayed for a password with less than 8 characters.");
        softAssert.assertEquals(logInModalComponent.getErrorMessageText(), expectedErrorMessage,
                "The displayed error message is incorrect.");
        softAssert.assertAll();
    }

    @Test(dataProvider = "checkPasswordLessThan8CharactersUA", dataProviderClass = LoginFormTestProvider.class)
    public void checkPasswordLessThan8CharactersUATest(String email, String password, String expectedErrorMessage) {
        LoginModalComponent logInModalComponent = page
                .setLanguage("ua")
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(logInModalComponent.isErrorMessageDisplayed(),
                "The error message should be displayed for a password with less than 8 characters in UA localization.");
        softAssert.assertEquals(logInModalComponent.getErrorMessageText(), expectedErrorMessage,
                "The error message in UA localization is incorrect.");
        softAssert.assertAll();
    }

    @Test
    public void checkSignInBtnBecomesGreenByValidCreds() {
        LoginModalComponent logInModalComponent = page.getHeaderComponent()
                .openLoginForm();
        softAssert.assertFalse(logInModalComponent.isSignInButtonActive());
        logInModalComponent
                .enterEmail(configProperties.getRegisteredUserEmail())
                .enterPassword(configProperties.getRegisteredUserPassword());
        softAssert.assertTrue(logInModalComponent.isSignInButtonActive());
        softAssert.assertTrue(logInModalComponent.isHighlightedSignInBtnGreen());
        softAssert.assertAll();
    }

    @Test(dataProvider = "checkLoginUnregisteredEmailDataEN", dataProviderClass = LoginFormTestProvider.class)
    public void checkUnregisteredEmailTestEN(String email, String password, String expectedErrorMessage)  {
        LoginModalComponent logInModalComponent = page
                .setLanguage("en")
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(logInModalComponent.isErrorMessageDisplayedUnregistered(),
                "The error message should be displayed for unregistered email.");
        softAssert.assertEquals(logInModalComponent.getErrorMessageTextUnregistered(), expectedErrorMessage,
                "The displayed error message is incorrect.");
        softAssert.assertAll();
    }

    @Test(dataProvider = "checkLoginUnregisteredEmailDataUA", dataProviderClass = LoginFormTestProvider.class)
    public void checkUnregisteredEmailTestUA(String email, String password, String expectedErrorMessage) {
        LoginModalComponent logInModalComponent = page
                .setLanguage("ua")
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(logInModalComponent.isErrorMessageDisplayedUnregistered(),
                "The error message should be displayed for unregistered email.");
        softAssert.assertEquals(logInModalComponent.getErrorMessageTextUnregistered(), expectedErrorMessage,
                "The displayed error message is incorrect.");
        softAssert.assertAll(); // fixed
    }
  
    @Test
    public void verifySignInBtnIsEmptyByEmptyFields() {
        LoginModalComponent logInModalComponent = page.getHeaderComponent()
                .openLoginForm();
        boolean isEmailEmpty = logInModalComponent.getEmailField().isEmailFieldEmpty();
        boolean isPassEmpty = logInModalComponent.getPasswordField().isPasswordFieldEmpty();
        softAssert.assertTrue(isEmailEmpty);
        softAssert.assertTrue(isPassEmpty);
        softAssert.assertFalse(logInModalComponent.isSignInButtonActive());
        softAssert.assertAll();
    }

    @Test(dataProvider = "screenResolution320pxAndZoomLevelValuesPercentage", dataProviderClass = LoginFormTestProvider.class)
    public void checkScrollbarIsDisplayedOnPageAt320pxResolutionTest(int windowWidth, List<Integer> zoomValuesPercentage) {

        LoginModalComponent loginModalComponent = page
                .getHeaderComponent()
                .openLoginForm();

        setWindowWidth(windowWidth);

        for (int zoomLevelPercentage : zoomValuesPercentage) {
            setZoomTo(zoomLevelPercentage);
            WebElement element = loginModalComponent.getMainWindow();
            boolean hasHorizontalScrollbar = hasHorizontalScrollbar(element);
            boolean shouldHaveHorizontalScrollBar = loginModalComponent.getWidth()*zoomLevelPercentage/100 > windowWidth;

            softAssert.assertEquals(hasHorizontalScrollbar, shouldHaveHorizontalScrollBar,
                    "Horizontal scrollbar should be displayed on page at " + windowWidth + "px resolution with " +
                    zoomLevelPercentage + "% zoom level");
        }

    }
}