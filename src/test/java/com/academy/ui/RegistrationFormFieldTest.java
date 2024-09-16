package com.academy.ui;

import com.academy.ui.components.RegistrationModalComponent;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.providers.RegistrationFormFieldTestProvider;
import com.academy.ui.runners.TestRunnerClassInitDriver;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.*;

public class RegistrationFormFieldTest extends TestRunnerClassInitDriver {
    private ImmutableMap<String, String> localizedMessages;
    private HomePage page;
    private RegistrationModalComponent form;

    @BeforeClass
    @Parameters({"language"})
    public void setUp(@Optional("Ua") String language) {
        driver.get(configProperties.getHomePageGreenCityUrl());

        this.page = new HomePage(driver).setLanguage(language);
        this.localizedMessages = localizationUtils.getFormMessages(language);
    }

    @BeforeMethod
    public void setUpMethod() {
        form = page.getHeaderComponent().openRegistrationForm();
    }

    @AfterMethod
    public void tearDownMethod() {
        form.close();
    }

    @Test
    public void testFormIsDisplayedCorrectly() {
        softAssert.assertTrue(form.getEmail().isDisplayed());
        softAssert.assertTrue(form.getUsername().isDisplayed());
        softAssert.assertTrue(form.getPassword().isDisplayed());
        softAssert.assertTrue(form.getRepeatPassword().isDisplayed());

        softAssert.assertTrue(form.isRegistrationButtonDisplayed());
        softAssert.assertTrue(form.isGoogleButtonDisplayed());
        softAssert.assertTrue(form.isSignInLinkDisplayed());

        softAssert.assertAll();
    }

    @Test(dataProvider = "testButtonState", dataProviderClass = RegistrationFormFieldTestProvider.class)
    public void testButtonState(boolean isExpectedButtonEnabled, boolean isShouldClearPassword, String email, String username, String password, String repeatPassword) {
        boolean isActualButtonEnabled = form.fillForm(email, username, password, repeatPassword)
                .clearPasswordFieldIf(isShouldClearPassword).isRegistrationButtonEnabled();

        softAssert.assertEquals(isActualButtonEnabled, isExpectedButtonEnabled);
        softAssert.assertAll();
    }

    @Test(dataProvider = "testEmailValidation", dataProviderClass = RegistrationFormFieldTestProvider.class)
    public void testEmailValidation(boolean isExpectedValid, String expectedErrorMessage, boolean isShouldSubmitForm, String errorMessage, String email) {
        form.fillFormWithTestDataAndSubmitIf(isShouldSubmitForm, email, null, null, null);

        boolean isActualValid = form.getEmail().isValid();
        String actualErrorMessage = form.getEmail().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid);
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage));

        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testUsernameValidation", dataProviderClass = RegistrationFormFieldTestProvider.class)
    public void testUsernameValidation(boolean isExpectedValid, String expectedErrorMessage, String errorMessage, String username) {
        form.enterUsername(username).clickTitle();
        boolean isActualValid = form.getUsername().isValid();
        String actualErrorMessage = form.getUsername().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid);
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage));

        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testPasswordValidation", dataProviderClass = RegistrationFormFieldTestProvider.class)
    public void testPasswordValidation(boolean isExpectedValid, String expectedErrorMessage, String errorMessage, String password) {
        form.enterPassword(password).clickTitle();
        boolean isActualValid = form.getPassword().isValid();
        String actualErrorMessage = form.getPassword().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid);
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage));

        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testRepeatPasswordValidation", dataProviderClass = RegistrationFormFieldTestProvider.class)
    public void testRepeatPasswordValidation(boolean isExpectedValid, String expectedErrorMessage, String errorMessage, String password, String repeatPassword) {
        form.enterPassword(password).enterRepeatPassword(repeatPassword).clickTitle();
        boolean isActualValid = form.getRepeatPassword().isValid();
        String actualErrorMessage = form.getRepeatPassword().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid);
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage));

        softAssert.assertAll(errorMessage);
    }
}
