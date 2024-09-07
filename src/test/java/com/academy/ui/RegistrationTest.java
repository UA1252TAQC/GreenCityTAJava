package com.academy.ui;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.academy.ui.components.RegistrationComponent;
import com.academy.ui.pages.HomePage;
import com.academy.ui.providers.RegistrationTestProvider;
import com.academy.ui.runners.BaseTestRunner;

import static org.testng.Assert.*;

public class RegistrationTest extends BaseTestRunner {
    private static final String[] validData = new String[] {"mail@gmail.com", "Denys1", "Password1!", "Password1!"};
    private RegistrationComponent form;

    @BeforeMethod
    public void setUp() {
        final HomePage page = new HomePage(driver);
        page.openRegistrationFormInHeader();
        form = page.getRegistrationComponent();
    }

    @Test(dataProvider = "testEmailValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testEmailValidation(boolean isShouldSubmitForm, boolean isExpectedValid, String errorMessage, String email) {
        if (!isShouldSubmitForm) {
            form.getEmail().enter(email);
            assertEquals(form.getEmail().isValid(), isExpectedValid, errorMessage + ": " + form.getEmail().getErrorMessage());
            return;
        }

        final boolean isRegistered =
                form
                        .fillForm(email, validData[1], validData[2], validData[3])
                        .submitIfEnable();
        if (isRegistered) {
            assertTrue(isExpectedValid, errorMessage);
        } else {
            assertEquals(form.getEmail().isValid(), isExpectedValid, errorMessage + ": " + form.getEmail().getErrorMessage());
        }
    }

    @Test(dataProvider = "testUsernameValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testUsernameValidation(boolean isExpectedValid, String errorMessage, String username) {
        form.getUsername().enter(username);
        assertEquals(form.getUsername().isValid(), isExpectedValid, errorMessage);
    }

    @Test(dataProvider = "testPasswordValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testPasswordValidation(boolean isExpectedValid, String errorMessage, String password) {
        form.getPassword().enter(password);
        assertEquals(form.getPassword().isValid(), isExpectedValid, errorMessage);
    }

    @Test(dataProvider = "testRepeatPasswordValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testRepeatPasswordValidation(boolean isExpectedValid, String errorMessage, String repeatPassword) {
        form.getRepeatPassword().enter(repeatPassword);
        assertEquals(form.getRepeatPassword().isValid(), isExpectedValid, errorMessage);
    }
}
