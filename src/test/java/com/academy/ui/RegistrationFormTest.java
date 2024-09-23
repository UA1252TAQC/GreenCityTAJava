package com.academy.ui;

import com.academy.ui.components.GoogleAuthComponent;
import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.components.RegistrationModalComponent;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.pages.greenCity.ProfilePage;
import com.academy.ui.pages.ubs.HomePageUbs;
import com.academy.ui.providers.RegistrationFormTestProvider;
import com.academy.ui.runners.TestRunnerRegistrationForm;
import com.academy.utils.jwt.JwtPayload;
import com.academy.utils.mail.Mail;
import com.academy.utils.mail.MailBoxCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;

@Slf4j
public class RegistrationFormTest extends TestRunnerRegistrationForm {

    @Test(dataProvider = "testPopUpSignUpValidation", dataProviderClass = RegistrationFormTestProvider.class)
    @Description("Tests the validation of the pop-up sign-up process.")
    @Feature("Registration")
    @Issue("12")
    public void testPopUpSignUpValidation(String expectedRegistrationSuccessMessage, String expectedAccountSubmitMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePage homePage = openHomePage();
        var form = homePage.getHeaderComponent().openRegistrationForm();

        form.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();
        String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage), 
            "Validating the success message after registration.");

        Mail mail = mailUtils.getLastEmail(mailBox.getId());

        homePage.openUrlInNewTab(mail.extractActivationLink());
        HomePageUbs ubsPage = new HomePageUbs(driver);

        String actualAccountSubmitMessage = ubsPage.getPopUpMessage();
        softAssert.assertEquals(actualAccountSubmitMessage, localizedMessages.get(expectedAccountSubmitMessage), 
            "Validating the account submission message after activation.");

        LoginModalComponent loginForm = ubsPage.getHeaderComponent().getCurrentLoginForm();
        ProfilePage profilePage = loginForm.fillForm(mailBox.getAddress(), password).clickSignInButtonSuccessfulLogin();

        JwtPayload jwtPayload = parseJwt(profilePage.getAuthToken());
        softAssert.assertEquals(jwtPayload.getExp(), jwtPayload.getIat().plus(Duration.ofHours(24)), 
            "Validating the JWT token expiration time is 24 hours.");

        softAssert.assertAll();
    }

    private JwtPayload parseJwt(String token) {
        if (token == null) {
            return null;
        }

        String[] parts = token.split("\\.");

        String payload = parts[1];

        byte[] decodedBytes = Base64.getUrlDecoder().decode(payload);
        String decodedPayload = new String(decodedBytes, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(decodedPayload, JwtPayload.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test(dataProvider = "testGoogleSignUp", dataProviderClass = RegistrationFormTestProvider.class)
    @Description("Tests the Google sign-up process.")
    @Feature("Registration")
    @Issue("13")
    public void testGoogleSignUp(String googleEmail, String googlePassword, String expectedGoogleName) {
        HomePage homePage = openHomePage();
        var form = homePage.getHeaderComponent().openRegistrationForm();

        GoogleAuthComponent googleForm = form.openAuthGoogleForm();
        googleForm.enterEmail(googleEmail)
                .clickEmailSubmitButton()
                .enterPassword(googlePassword)
                .clickPasswordSubmitButton();

        homePage.switchToActiveTab();

        ProfilePage profilePage = new ProfilePage(driver);
        softAssert.assertNotNull(profilePage.getAuthToken(), 
            "Validating that the JWT token is not null after Google sign-up.");
        softAssert.assertTrue(driver.getCurrentUrl().startsWith(configProperties.getBaseUrl() + "/#/profile"), 
            "Validating that the user is redirected to the profile page.");
        softAssert.assertEquals(profilePage.getHeaderComponent().getUserNameText(), expectedGoogleName, 
            "Validating that the Google account name matches the expected name.");
        softAssert.assertAll();
    }

    @Test(dataProvider = "testRegisteredGreenCity", dataProviderClass = RegistrationFormTestProvider.class)
    @Description("Tests registration with already existing Green City account.")
    @Feature("Registration")
    @Issue("12")
    public void testRegisteredGreenCity(String expectedRegistrationSuccessMessage, String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePage homePage = openHomePage();
        var homeForm = homePage.getHeaderComponent().openRegistrationForm();

        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage), 
            "Validating the success message after registration.");

        HomePageUbs ubsPage = openUbsPageInNewTab(homePage);
        RegistrationModalComponent ubsForm = ubsPage.getHeaderComponent().openRegistrationForm();
        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationErrorMessage = ubsForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualRegistrationErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage), 
            "Validating the error message for already registered email.");

        softAssert.assertAll();
    }

    @Test(dataProvider = "testRegisteredUbs", dataProviderClass = RegistrationFormTestProvider.class)
    @Description("Tests registration with an already existing UBS account.")
    @Feature("Registration")
    @Issue("12")
    public void testRegisteredUbs(String expectedRegistrationSuccessMessage, String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePageUbs ubsPage = openUbsPage();
        var ubsForm = ubsPage.getHeaderComponent().openRegistrationForm();

        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationSuccessMessage = ubsPage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage), 
            "Validating the success message after registration.");

        HomePage homePage = openHomePageInNewTab(ubsPage);
        RegistrationModalComponent homeForm = homePage.getHeaderComponent().openRegistrationForm();

        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationErrorMessage = homeForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualRegistrationErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage), 
            "Validating the error message for already registered email in UBS.");

        softAssert.assertAll();
    }

    @Test(dataProvider = "testEmailAlreadyExists", dataProviderClass = RegistrationFormTestProvider.class)
    @Description("Tests the behavior when attempting to register with an already existing email.")
    @Feature("Registration")
    @Issue("12")
    public void testEmailAlreadyExists(String expectedRegistrationSuccessMessage, String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePage homePage = openHomePage();
        var homeForm = homePage.getHeaderComponent().openRegistrationForm();

        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage), 
            "Validating the success message after registration.");

        homeForm = homePage.getHeaderComponent().openRegistrationForm();
        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualErrorMessage = homeForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage), 
            "Validating the error message for already registered email.");

        softAssert.assertAll();
    }

    @Test(dataProvider = "testGreenCityRegisteredWithConfirmEmail", dataProviderClass = RegistrationFormTestProvider.class)
    @Description("Tests the registration process when confirming an email for a Green City account.")
    @Feature("Registration")
    @Issue("12")
    public void testGreenCityRegisteredWithConfirmEmail(String expectedRegistrationSuccessMessage, String expectedAccountSubmitMessage, String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePage homePage = openHomePage();
        var greenCityForm = homePage.getHeaderComponent().openRegistrationForm();

        greenCityForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage), 
            "Validating the success message after registration.");

        Mail mail = mailUtils.getLastEmail(mailBox.getId());
        homePage.openUrlInNewTab(mail.extractActivationLink());

        String actualAccountSubmitMessage = homePage.getPopUpMessage();
        softAssert.assertEquals(actualAccountSubmitMessage, localizedMessages.get(expectedAccountSubmitMessage));

        HomePageUbs ubsPage = openUbsPageInNewTab(homePage);
        var ubsForm = ubsPage.getHeaderComponent().openRegistrationForm();
        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationErrorMessage = ubsForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualRegistrationErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        softAssert.assertAll();
    }

    @Test(dataProvider = "testUbsRegisteredWithConfirmEmail", dataProviderClass = RegistrationFormTestProvider.class)
    @Description("Tests the registration process when confirming an email for a UBS account.")
    @Feature("Registration")
    @Issue("12")
    public void testUbsRegisteredWithConfirmEmail(String expectedRegistrationSuccessMessage, String expectedAccountSubmitMessage, String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePageUbs ubsPage = openUbsPage();
        RegistrationModalComponent ubsForm = ubsPage.getHeaderComponent().openRegistrationForm();

        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationSuccessMessage = ubsPage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage));


        Mail mail = mailUtils.getLastEmail(mailBox.getId());
        ubsPage.openUrlInNewTab(mail.extractActivationLink());

        String actualAccountSubmitMessage = ubsPage.getPopUpMessage();
        softAssert.assertEquals(actualAccountSubmitMessage, localizedMessages.get(expectedAccountSubmitMessage), 
            "Validating the account submission message after email confirmation.");

        HomePage homePage = openHomePageInNewTab(ubsPage);
        RegistrationModalComponent greenCityForm = homePage.getHeaderComponent().openRegistrationForm();
        greenCityForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationErrorMessage = greenCityForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualRegistrationErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage), 
            "Validating the error message for already registered email in Green City.");

        softAssert.assertAll();
    }
}