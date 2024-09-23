package com.academy.ui;

import com.academy.ui.providers.RegistrationFormFieldTestProvider;
import com.academy.ui.runners.TestRunnerRegistrationFormField;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;

import org.testng.annotations.Test;

public class RegistrationFormFieldTest extends TestRunnerRegistrationFormField {

    @Test
    @Description("Verifies that the registration form is displayed correctly.")
    @Feature("Form Display")
    @Step("Verify registration form components are displayed")
    public void testFormIsDisplayedCorrectly() {
        softAssert.assertTrue(form.getEmail().isDisplayed(), "Email field is not displayed");
        softAssert.assertTrue(form.getUsername().isDisplayed(), "Username field is not displayed");
        softAssert.assertTrue(form.getPassword().isDisplayed(), "Password field is not displayed");
        softAssert.assertTrue(form.getRepeatPassword().isDisplayed(), "Repeat Password field is not displayed");

        softAssert.assertTrue(form.isRegistrationButtonDisplayed(), "Registration button is not displayed");
        softAssert.assertTrue(form.isGoogleButtonDisplayed(), "Google Sign-In button is not displayed");
        softAssert.assertTrue(form.isSignInLinkDisplayed(), "Sign-In link is not displayed");

        softAssert.assertAll();
    }

    @Test(dataProvider = "testButtonState", dataProviderClass = RegistrationFormFieldTestProvider.class)
    @Description("Tests the state of the registration button based on form input.")
    @Feature("Button State")
    @Step("Test registration button state with email: {email}, username: {username}, password: {password}, repeat password: {repeatPassword}")
    public void testButtonState(boolean isExpectedButtonEnabled, boolean isShouldClearPassword, String email, String username, String password, String repeatPassword) {
        boolean isActualButtonEnabled = form.fillForm(email, username, password, repeatPassword)
                .clearPasswordFieldIf(isShouldClearPassword).isRegistrationButtonEnabled();

        softAssert.assertEquals(isActualButtonEnabled, isExpectedButtonEnabled, "Button state mismatch");
        softAssert.assertAll();
    }

    @Test(dataProvider = "testEmailValidation", dataProviderClass = RegistrationFormFieldTestProvider.class)
    @Description("Validates the email field of the registration form.")
    @Feature("Email Validation")
    @Step("Test email validation with email: {email}")
    public void testEmailValidation(boolean isExpectedValid, String expectedErrorMessage, boolean isShouldSubmitForm, String errorMessage,
                                    String email, String username, String password, String repeatPassword) {
        form.fillForm(email, username, password, repeatPassword).submitIf(isShouldSubmitForm);

        boolean isActualValid = form.getEmail().isValid();
        String actualErrorMessage = form.getEmail().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid, "Email validation failed");
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage), "Error message mismatch");

        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testUsernameValidation", dataProviderClass = RegistrationFormFieldTestProvider.class)
    @Description("Validates the username field of the registration form.")
    @Feature("Username Validation")
    @Step("Test username validation with username: {username}")
    public void testUsernameValidation(boolean isExpectedValid, String expectedErrorMessage, String errorMessage, String username) {
        form.enterUsername(username).clickTitle();
        boolean isActualValid = form.getUsername().isValid();
        String actualErrorMessage = form.getUsername().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid, "Username validation failed");
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage), "Error message mismatch");

        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testPasswordValidation", dataProviderClass = RegistrationFormFieldTestProvider.class)
    @Description("Validates the password field of the registration form.")
    @Feature("Password Validation")
    @Step("Test password validation with password: {password}")
    public void testPasswordValidation(boolean isExpectedValid, String expectedErrorMessage, String errorMessage, String password) {
        form.enterPassword(password).clickTitle();
        boolean isActualValid = form.getPassword().isValid();
        String actualErrorMessage = form.getPassword().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid, "Password validation failed");
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage), "Error message mismatch");

        softAssert.assertAll(errorMessage);
    }
    @Test(dataProvider = "testRepeatPasswordValidation", dataProviderClass = RegistrationFormFieldTestProvider.class)
    @Description("Validates the repeat password field of the registration form.")
    @Feature("Repeat Password Validation")
    @Step("Test repeat password validation with password: {password} and repeat password: {repeatPassword}")
    public void testRepeatPasswordValidation(boolean isExpectedValid, String expectedErrorMessage, String errorMessage, String password, String repeatPassword) {
        form.enterPassword(password).enterRepeatPassword(repeatPassword).clickTitle();
        boolean isActualValid = form.getRepeatPassword().isValid();
        String actualErrorMessage = form.getRepeatPassword().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid, "Repeat password validation failed");
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage), "Error message mismatch");

        softAssert.assertAll(errorMessage);
    }
}
