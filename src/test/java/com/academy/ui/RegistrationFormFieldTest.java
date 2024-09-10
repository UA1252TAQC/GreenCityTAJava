package com.academy.ui;

import com.academy.utils.props.LocalizationProperties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.academy.ui.components.RegistrationComponent;
import com.academy.ui.pages.HomePage;
import com.academy.ui.providers.RegistrationFormFieldTestProvider;
import com.academy.ui.runners.FormTestRunner;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;

public class RegistrationFormFieldTest extends FormTestRunner {
    private ImmutableMap<String, String> localizedMessages;
    private HomePage page;
    private RegistrationComponent form;
    private SoftAssert softAssert;

    @BeforeClass
    @Parameters({"language"})
    public void setUp(@Optional("ua") String language) {
        page = new HomePage(driver).setLanguage(language);
        try {
            LocalizationProperties properties = new LocalizationProperties();
            localizedMessages = properties.getRegistrationMessages(language);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
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
