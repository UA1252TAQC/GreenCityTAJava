package com.academy.ui;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.academy.ui.components.RegistrationComponent;
import com.academy.ui.pages.HomePage;
import com.academy.ui.providers.RegistrationTestProvider;
import com.academy.ui.runners.BaseTestRunner;

import static org.testng.Assert.*;

public class RegistrationTest extends BaseTestRunner {
    private RegistrationComponent form;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        final HomePage page = new HomePage(driver);
        page.openRegistrationFormInHeader();
        form = page.getRegistrationComponent();

        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "testEmailValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testEmailValidation(boolean isExpectedValid, String expectedErrorMessage, boolean isShouldSubmitForm, String errorMessage, String email) {
        form.fillFormWithTestDataAndSubmitIf(isShouldSubmitForm, email, null, null, null);

        boolean isActualValid = form.getEmail().isValid();
        String actualErrorMessage = form.getEmail().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid);
        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage);

        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testUsernameValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testUsernameValidation(boolean isExpectedValid, String expectedErrorMessage, String errorMessage, String username) {
        form.enterUsername(username).click();
        boolean isActualValid = form.getUsername().isValid();
        String actualErrorMessage = form.getUsername().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid);
        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage);
        
        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testPasswordValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testPasswordValidation(boolean isExpectedValid, String expectedErrorMessage, String errorMessage, String password) {
        form.enterPassword(password).click();
        boolean isActualValid = form.getPassword().isValid();
        String actualErrorMessage = form.getPassword().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid, expectedErrorMessage);
        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage);
        

        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testRepeatPasswordValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testRepeatPasswordValidation(boolean isExpectedValid, String expectedErrorMessage, String errorMessage, String password, String repeatPassword) {
        form.enterPassword(password)
                .enterRepeatPassword(repeatPassword)
                .click();
        boolean isActualValid = form.getRepeatPassword().isValid();
        String actualErrorMessage = form.getRepeatPassword().getErrorMessage();

        softAssert.assertEquals(isActualValid, isExpectedValid, expectedErrorMessage);
        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage);

        softAssert.assertAll();
    }
}
