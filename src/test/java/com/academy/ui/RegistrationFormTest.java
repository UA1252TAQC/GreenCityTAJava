package com.academy.ui;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.academy.ui.components.RegistrationComponent;
import com.academy.ui.forms.Messages;
import com.academy.ui.pages.HomePage;
import com.academy.ui.providers.RegistrationFormTestProvider;
import com.academy.ui.runners.FormTestRunner;
import com.academy.utils.LocalizationProperties;
import com.google.common.collect.ImmutableMap;

public class RegistrationFormTest extends FormTestRunner {
    private ImmutableMap<Messages, String> localizedMessages;
    private HomePage page;
    private RegistrationComponent form;
    private SoftAssert softAssert;

    @BeforeClass
    @Parameters({"language"})
    public void setUp(@Optional("ua") String language) {
        page = new HomePage(driver).setLanguage(language);

        LocalizationProperties properties = new LocalizationProperties();
        localizedMessages = properties.getRegistrationMessages(language);
    }
    
    @BeforeMethod
    public void setUpMethod() {
        form = page.openRegistrationFormInHeader();
        softAssert = new SoftAssert();
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

        softAssert.assertTrue(form.isRegisterButtonDisplayed());
        softAssert.assertTrue(form.isGoogleButtonDisplayed());
        softAssert.assertTrue(form.isSignInLinkDisplayed());

        softAssert.assertAll();
    }

    @Test(dataProvider = "testEmailValidation", dataProviderClass = RegistrationFormTestProvider.class)
    public void testEmailValidation(boolean isExpectedValid, Messages expectedErrorMessage, boolean isShouldSubmitForm, String errorMessage, String email) {
        form.fillFormWithTestDataAndSubmitIf(isShouldSubmitForm, email, null, null, null);

        boolean isActualValid = form.getEmail().isValid();
        String actualErrorMessage = form.getEmail().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid);
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage));

        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testUsernameValidation", dataProviderClass = RegistrationFormTestProvider.class)
    public void testUsernameValidation(boolean isExpectedValid, Messages expectedErrorMessage, String errorMessage, String username) {
        form.enterUsername(username).clickTitle();
        boolean isActualValid = form.getUsername().isValid();
        String actualErrorMessage = form.getUsername().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid);
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage));

        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testPasswordValidation", dataProviderClass = RegistrationFormTestProvider.class)
    public void testPasswordValidation(boolean isExpectedValid, Messages expectedErrorMessage, String errorMessage, String password) {
        form.enterPassword(password).clickTitle();
        boolean isActualValid = form.getPassword().isValid();
        String actualErrorMessage = form.getPassword().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid);
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage));
        
        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testRepeatPasswordValidation", dataProviderClass = RegistrationFormTestProvider.class)
    public void testRepeatPasswordValidation(boolean isExpectedValid, Messages expectedErrorMessage, String errorMessage, String password, String repeatPassword) {
        form.enterPassword(password)
            .enterRepeatPassword(repeatPassword)
            .clickTitle();
        boolean isActualValid = form.getRepeatPassword().isValid();
        String actualErrorMessage = form.getRepeatPassword().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid);
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedErrorMessage));

        softAssert.assertAll(errorMessage);
    }
}
