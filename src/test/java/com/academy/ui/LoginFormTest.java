package com.academy.ui;

import com.academy.ui.components.ForgotPasswordModalComponent;
import com.academy.ui.components.GoogleAuthComponent;
import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.components.sub.form.EmailField;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.pages.greenCity.NewsPage;
import com.academy.ui.providers.LoginFormTestProvider;
import com.academy.ui.pages.greenCity.ProfilePage;
import com.academy.ui.runners.TestRunnerMethodInitDriverHomePage;
import com.academy.ui.styleConstants.Colors;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class LoginFormTest extends TestRunnerMethodInitDriverHomePage {
    @Test(dataProvider = "verifyPasswordLessThan8Characters", dataProviderClass = LoginFormTestProvider.class)
    public void verifyPasswordLessThan8CharactersTest(String language, String email, String password, String expectedErrorMessage) {
        LoginModalComponent logInModalComponent = page
                .setLanguage(language)
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

    @Test(dataProvider = "verifyLoginUnregisteredEmailData", dataProviderClass = LoginFormTestProvider.class)
    public void verifyLoginUnregisteredEmailData(String language, String email, String password, String expectedErrorMessage) {
        LoginModalComponent logInModalComponent = page
                .setLanguage(language)
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

    @Test(dataProvider = "checkForgotPasswordUnregisteredEmailData", dataProviderClass = LoginFormTestProvider.class)
    public void verifyErrorForUnregisteredEmailInForgotPassword(String email, String expectedErrorMessage) {
        ForgotPasswordModalComponent forgotPasswordModal = page
                .setLanguage("en")
                .getHeaderComponent()
                .openLoginForm()
                .clickForgotPasswordLink();

        forgotPasswordModal.enterEmail(email)
                .clickSignInButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(forgotPasswordModal.getEmailField().isHighlightedInColor(Colors.PRIMARY_RED),
                "The email field should be highlighted in red.");
        softAssert.assertEquals(forgotPasswordModal.getEmailField().getErrorMessage(), expectedErrorMessage,
                "The displayed error message is incorrect.");
        softAssert.assertAll();
    }

    @Test(dataProvider = "verifyErrorMessageForEmptyEmailAndOrPassword", dataProviderClass = LoginFormTestProvider.class)
    @Description("Verify Error Message for Empty Email and Password")
    @Feature("Login")
    @Issues({
            @Issue("131"),
            @Issue("132")
    })
    public void verifyErrorMessageForEmptyEmailAndOrPassword(String language, String email, String password, String expected) {
        String errorMessage = page.setLanguage(language)
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin()
                .getLoginErrorText();

        Assert.assertEquals(errorMessage, expected);
    }

    @Test(dataProvider = "verifyErrorMessageForEmptyEmail", dataProviderClass = LoginFormTestProvider.class)
    @Description("Verify Error Message for empty Email field Ua/En")
    @Feature("Login")
    @Issues({
            @Issue("82"),
            @Issue("83")
    })
    public void verifyErrorMessageForEmptyEmail(String language, String email, String expected) {
        String errorMessage = page.setLanguage(language)
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .clickInsideForm()
                .getEmailField()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, expected);
    }

    @Test(dataProvider = "verifyErrorMessageForEmptyPassword", dataProviderClass = LoginFormTestProvider.class)
    @Description("Verify Error Message for Empty Password Field")
    @Feature("Login")
    @Issues({
            @Issue("84"),
            @Issue("85")
    })
    public void verifyErrorMessageForEmptyPassword(String language, String password, String expected) {
        String errorMessage = page.setLanguage(language)
                .getHeaderComponent().openLoginForm()
                .enterPassword(password)
                .clickInsideForm()
                .getPasswordField()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, expected);
    }

    @Test(dataProvider = "verifyCssAndErrorIsDisplayedInForgotPasswordWithInvalidEmail", dataProviderClass = LoginFormTestProvider.class)
    @Description("Verify that the 'Email' field is highlighted with a red outline and error message is displayed after entering invalid data")
    @Feature("Forgot Password")
    @Issue("69")
    public void verifyCssAndErrorIsDisplayedInForgotPasswordWithInvalidEmail(String language, String email, String expected) {

        ForgotPasswordModalComponent forgotPasswordModal = page
                .setLanguage(language)
                .getHeaderComponent()
                .openLoginForm()
                .clickForgotPasswordLink();

        String errorMessage = forgotPasswordModal
                .enterEmail(email)
                .clickSignInButton()
                .getEmailField()
                .getErrorMessage();

        boolean isHighlightedInRed = forgotPasswordModal
                .getEmailField()
                .isHighlightedInColor(Colors.PRIMARY_RED);

        softAssert.assertEquals(errorMessage, expected + email);
        softAssert.assertTrue(isHighlightedInRed);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify opening of \"Forgot Password\" form after clicking the 'Forgot password' link")
    @Feature("Forgot password")
    @Issue("135")
    public void verifyOpeningForgotPasswordFormAfterClick() {

        boolean isDisplayed = page.getHeaderComponent()
                .openLoginForm()
                .clickForgotPasswordLink()
                .isForgotPasswordWindowDisplayed();

        Assert.assertTrue(isDisplayed);
    }

    @Test(dataProvider = "verifyErrorMessageForExceedingPasswordLength", dataProviderClass = LoginFormTestProvider.class)
    @Description("Verify error message for exceeding password length")
    @Feature("Login")
    @Issue("130")
    public void verifyErrorMessageForExceedingPasswordLength(String language, String email, String password, String expected) {

        String errorMessage = page.setLanguage(language)
                .getHeaderComponent()
                .openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickInsideForm()
                .getPasswordField()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, expected);
    }

    @Test(dataProvider = "verifyErrorMessageForInvalidPassword", dataProviderClass = LoginFormTestProvider.class)
    @Description("Verify Error Message for invalid password")
    @Feature("Login")
    @Issue("128")
    public void verifyErrorMessageForInvalidPassword(String language, String email, String password, String expected) {

        String errorMessage = page
                .setLanguage(language)
                .getHeaderComponent()
                .openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButton()
                .clickInsideForm()
                .getPasswordField()
                .getErrorMessage();
        Assert.assertEquals(errorMessage, expected);
    }

    @Test
    public void verifySignInBtnBecomesGreenByValidCreds() {
        LoginModalComponent logInModalComponent = page.getHeaderComponent()
                .openLoginForm();
        softAssert.assertFalse(logInModalComponent.isSignInButtonActive());
        logInModalComponent
                .enterEmail(configProperties.getRegisteredUserEmail())
                .enterPassword(configProperties.getRegisteredUserPassword());
        softAssert.assertTrue(logInModalComponent.isSignInButtonActive());
        softAssert.assertTrue(logInModalComponent.isHighlightedSignInBtnInColor(Colors.PRIMARY_GREEN));
        softAssert.assertAll();
    }

    @Test
    public void verifySignInBtnIsInactiveByEmptyFields() {
        LoginModalComponent logInModalComponent = page.getHeaderComponent()
                .openLoginForm();
        boolean isEmailEmpty = logInModalComponent.getEmailField().isEmailFieldEmpty();
        boolean isPassEmpty = logInModalComponent.getPasswordField().isPasswordFieldEmpty();
        softAssert.assertTrue(isEmailEmpty);
        softAssert.assertTrue(isPassEmpty);
        softAssert.assertFalse(logInModalComponent.isSignInButtonActive());
        softAssert.assertAll();
    }

    @Test
    public void verifyLinkSignInWithGoogle() {
        GoogleAuthComponent googleAuthComponent = page.getHeaderComponent()
                .openLoginForm()
                .clickSignInWithGoogleBtn();
        softAssert.assertTrue(googleAuthComponent.getCurrentUrl().contains("accounts.google.com"), "Wrong url");
        softAssert.assertTrue(googleAuthComponent.isEmailInputDisplayed(), "There is no element after following link");
        softAssert.assertAll();
    }

    @Test(dataProvider = "verifyMessageAfterRecoverPassDataProvider", dataProviderClass = LoginFormTestProvider.class)
    public void verifyMessageAfterRecoverPass(String email, String expectedMessage) {
        page.setLanguage("en")
                .getHeaderComponent()
                .openLoginForm()
                .clickForgotPasswordLink()
                .enterEmail(email)
                .clickSignInButton();
        driver.navigate().refresh();

        EmailField emailField = page.setLanguage("en")
                .getHeaderComponent()
                .openLoginForm()
                .clickForgotPasswordLink()
                .enterEmail(email)
                .clickSignInButton().getEmailField();

        String message = emailField.getErrorMessage();
        softAssert.assertEquals(message, expectedMessage + " " + email);

        boolean isHighlightedInRed = emailField
                .isHighlightedInColor(Colors.PRIMARY_RED);
        softAssert.assertTrue(isHighlightedInRed, "The color of email field is not " + Colors.PRIMARY_RED);
        softAssert.assertAll();
    }

    @Test(dataProvider = "verifySignInButtonRemainedInactivePassword", dataProviderClass = LoginFormTestProvider.class)
    public void verifySignInButtonRemainedInactiveWithFilledPassword(String password) {
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

    @Test(dataProvider = "verifyInSignInButtonRemainedInactiveEmail", dataProviderClass = LoginFormTestProvider.class)
    public void verifySignInButtonRemainedInactiveWithFilledEmail(String email) {
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

    @Test(dataProvider = "verifyInvalidEmailPassword", dataProviderClass = LoginFormTestProvider.class)
    public void verifyInvalidEmailPassword(String email, String password) {
        LoginModalComponent logInModalComponent = new HomePage(driver)
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin();

        logInModalComponent.sleep(3);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(logInModalComponent.isSignInButtonActive(),
                "The 'Login' button should be inactive when entering invalid email and password.");
        softAssert.assertAll();
    }

    @Test(dataProvider = "verifyInSignInButtonRemainedInactiveValidEmailInvalidPassword", dataProviderClass = LoginFormTestProvider.class)
    public void verifyValidEmailInvalidPassword(String email, String password) {
        LoginModalComponent logInModalComponent = new HomePage(driver)
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin();

        logInModalComponent.sleep(3);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(logInModalComponent.isSignInButtonActive(),
                "The 'Login' button should be inactive when entering invalid email and valid password.");
        softAssert.assertAll();
    }

    @Test(dataProvider = "verifyInSignInButtonRemainedInactiveValidPasswordInvalidEmail", dataProviderClass = LoginFormTestProvider.class)
    public void verifyValidPasswordInvalidEmail(String email, String password) {
        LoginModalComponent logInModalComponent = new HomePage(driver)
                .getHeaderComponent().openLoginForm()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButtonUnsuccessfulLogin();

        logInModalComponent.sleep(3);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(logInModalComponent.isSignInButtonActive(),
                "The 'Login' button should be inactive when entering valid email and invalid password.");
        softAssert.assertAll();
    }

    @Description("Verify the 'Sign-In' possibility with valid credentials")
    @Feature("Login")
    @Issue("81")
    @Test(dataProvider = "registeredUserCredentials", dataProviderClass = LoginFormTestProvider.class)
    public void verifySuccessfulSignInPossibilityWithValidCredentials(String email, String password, String name, String id) {

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

    @Description("Verify that the scrollbar is displayed on the 'Sign In' page at predefined resolution")
    @Feature("Login")
    @Issues({
        @Issue("98"),
        @Issue("99"),
        @Issue("100"),
        @Issue("101"),
        @Issue("102")
    })
    @Test(dataProvider = "widthResolutionPxAndZoomLevelsPercentage", dataProviderClass = LoginFormTestProvider.class)
    public void verifyScrollbarIsDisplayedOnPageTest(String language, int windowWidth, List<Integer> zoomValuesPercentage) {

        LoginModalComponent loginModalComponent = page.setLanguage(language)
                .getHeaderComponent()
                .openLoginForm();

        setWindowWidth(windowWidth);

        for (int zoomLevelPercentage : zoomValuesPercentage) {
            setZoomTo(zoomLevelPercentage);
            WebElement element = loginModalComponent.getMainWindow();
            boolean hasHorizontalScrollbar = hasHorizontalScrollbar(element);
            boolean shouldHaveHorizontalScrollBar = loginModalComponent.getWidth() * zoomLevelPercentage / 100 > windowWidth;

            softAssert.assertTrue(hasHorizontalScrollbar || !shouldHaveHorizontalScrollBar,
                    "Horizontal scrollbar should be displayed on page at " + windowWidth +
                            "px resolution with " + zoomLevelPercentage + "% zoom level");
        }

        softAssert.assertAll();

    }

    @Description("Verify the 'Sign-In' possibility with valid credentials")
    @Feature("Login")
    @Issue("92")
    @Test
    public void verifyUserIsDirectedBackToSignInPageAfterClickingTheBackToSignInLinkTest() {
        ForgotPasswordModalComponent forgotPasswordModalComponent = page
                .getHeaderComponent()
                .openLoginForm()
                .clickForgotPasswordLink();

        softAssert.assertTrue(forgotPasswordModalComponent.isForgotPasswordLinkDisplayed(),
                "There is no 'Back to Sign in' link on 'Forgot Password' form");

        LoginModalComponent loginModalComponent = forgotPasswordModalComponent
                .clickBackToSignInLink();

        softAssert.assertTrue(loginModalComponent.isForgotPasswordLinkDisplayed(),
                "The User isn't directed back to Sign in page after clicking the 'Back to Sign in' link " +
                        "on the Forgot Password page");

        softAssert.assertAll();
    }

    @Test(dataProvider = "verifyInvalidEmailWarningProvider", dataProviderClass = LoginFormTestProvider.class)
    @Description("Verify invalid email warning after entering not valid E-mail to the 'E-mail' field")
    @Feature("Login")
    @Issue("136")
    public void verifyInvalidEmailWarning(String email, String language, String expected, String color) {
        NewsPage newsPage = new NewsPage(driver);
        newsPage.getHeaderComponent().setLanguage(language);

        ForgotPasswordModalComponent forgotPasswordModalComponent = newsPage.getHeaderComponent()
                .openLoginForm()
                .clickForgotPasswordLink()
                .enterEmail(email)
                .clickSignInButton();

        String actual = forgotPasswordModalComponent
                .getErrorFieldMessage();


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(forgotPasswordModalComponent.isHighlightedInColor(color), "Field is not highlighted");
        softAssert.assertEquals(actual, expected, "Error message for invalid email does not match");

        softAssert.assertAll();
    }
}