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

        assertEquals(isActualValid, isExpectedValid, "Field expectation failed");
        //softAssert.assertEquals(actualErrorMessage, expectedErrorMessage);

        softAssert.assertAll(errorMessage);
    }

    @Test(dataProvider = "testUsernameValidation",
            dataProviderClass = RegistrationTestProvider.class)
    public void testUsernameValidation(boolean isExpectedValid, String errorMessage,
            String username) {
        form.getUsername().enter(username);
        assertEquals(form.getUsername().isValid(), isExpectedValid, errorMessage);
    }

    @Test(dataProvider = "testPasswordValidation",
            dataProviderClass = RegistrationTestProvider.class)
    public void testPasswordValidation(boolean isExpectedValid, String errorMessage,
            String password) {
        form.getPassword().enter(password);
        assertEquals(form.getPassword().isValid(), isExpectedValid, errorMessage);
    }

    @Test(dataProvider = "testRepeatPasswordValidation",
            dataProviderClass = RegistrationTestProvider.class)
    public void testRepeatPasswordValidation(boolean isExpectedValid, String errorMessage,
            String repeatPassword) {
        form.getRepeatPassword().enter(repeatPassword);
        assertEquals(form.getRepeatPassword().isValid(), isExpectedValid, errorMessage);
    }
}
