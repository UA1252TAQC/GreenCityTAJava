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
    public void setUpPage() {
        var page = new HomePage(driver);
		page.openRegistrationFormInHeader();
        form = page.getRegistrationComponent();
        
        softAssert = new SoftAssert();
	}

    @Test(dataProvider = "testEmailValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testEmailValidation(boolean isExpectedError, String errorMessage, String email) {
        form.enterEmail(email);

        boolean isActualError = form.isEmailErrorDisplayed();
        assertEquals(isActualError, isExpectedError, errorMessage);
    }

    @Test(dataProvider = "testUsernameValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testUsernameValidation(boolean isExpectedError, String errorMessage, String username) {
        form.enterUsername(username);

        boolean isActualError = form.isUsernameErrorDisplayed();
        assertEquals(isActualError, isExpectedError, errorMessage);
    }

    @Test(dataProvider = "testPasswordValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testPasswordValidation(boolean isExpectedError, String errorMessage, String password) {
        form.enterPassword(password);

        boolean isActualError = form.isPasswordErrorDisplayed();
        assertEquals(isActualError, isExpectedError, errorMessage);
    }

    @Test(dataProvider = "testRepeatPasswordValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testRepeatPasswordValidation(boolean isExpectedError, String errorMessage, String repeatPassword) {
        form.enterRepeatPassword(repeatPassword);

        boolean isActualError = form.isRepeatPasswordErrorDisplayed();
        assertEquals(isActualError, isExpectedError, errorMessage);
    }
}
