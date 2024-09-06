package com.academy.ui;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.academy.ui.components.RegistrationComponent;
import com.academy.ui.pages.HomePage;
import com.academy.ui.providers.RegistrationTestProvider;
import com.academy.ui.runners.BaseTestRunner;

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
    public void testEmailValidation() {

    }

    @Test(dataProvider = "testUsernameValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testUsernameValidation() {

    }

    @Test(dataProvider = "testPasswordValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testPasswordValidation() {

    }

    @Test(dataProvider = "testRepeatPasswordValidation", dataProviderClass = RegistrationTestProvider.class)
    public void testRepeatPasswordValidation() {

    }
}
