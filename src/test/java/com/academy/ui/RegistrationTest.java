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
    private static String[] validData =
            new String[] {"mail@gmail.com", "Denys1", "Password1!", "Password1!"};
    private RegistrationComponent form;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUpPage() {
        var page = new HomePage(driver);
        page.openRegistrationFormInHeader();
        form = page.getRegistrationComponent();

        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "testEmailValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testEmailValidation(boolean isShouldSubmitForm, boolean isExpectedValid,
            String errorMessage, String email) {
        if (!isShouldSubmitForm) {
            form.enterEmail(email);
            assertEquals(form.isEmailValid(), isExpectedValid, errorMessage + ": " + form.getEmailErrorMessage());
            return;
        }

        boolean isRegistered = form.fillForm(email, validData[1], validData[2], validData[3]).submitIfEnable();
        if (isRegistered) {
            assertEquals(isRegistered, isExpectedValid, errorMessage);
        } else {
            assertEquals(form.isEmailValid(), isExpectedValid, errorMessage + ": " + form.getEmailErrorMessage());
        }
    }

    @Test(dataProvider = "testUsernameValidation",
            dataProviderClass = RegistrationTestProvider.class)
    public void testUsernameValidation(boolean isExpectedValid, String errorMessage,
            String username) {
        form.enterUsername(username);
        assertEquals(form.isUsernameValid(), isExpectedValid, errorMessage);
    }

    @Test(dataProvider = "testPasswordValidation",
            dataProviderClass = RegistrationTestProvider.class)
    public void testPasswordValidation(boolean isExpectedValid, String errorMessage,
            String password) {
        form.enterPassword(password);
        assertEquals(form.isPasswordValid(), isExpectedValid, errorMessage);
    }

    @Test(dataProvider = "testRepeatPasswordValidation",
            dataProviderClass = RegistrationTestProvider.class)
    public void testRepeatPasswordValidation(boolean isExpectedValid, String errorMessage,
            String repeatPassword) {
        form.enterRepeatPassword(repeatPassword);
        assertEquals(form.isRepeatPasswordValid(), isExpectedValid, errorMessage);
    }
}
