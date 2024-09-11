package com.academy.ui;

import com.academy.ui.components.SignInComponent;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.FormTestRunner;
import com.academy.utils.LocalizationUtils;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignInTest extends FormTestRunner {
    private SignInComponent form;
    private HomePage page;

    @BeforeClass
    @Parameters({"language"})
    public void setUp(@Optional("ua") String language) {
        page = new HomePage(driver).setLanguage(language);
        LocalizationUtils properties = new LocalizationUtils();
    }

    @BeforeMethod
    public void setUpMethod() {
        form = page.openSignInFormInHeader();
    }

    @Test
    public void verifyErrorMessageForInvalidPasswordInUa() {
        String errorMessage = form
                .sendEmail(configProperties.getTestEmail())
                .sendPassword(configProperties.getTestExceedingLongPassword())
                .randomClick()
                .getErrorMessageForExceedingPasswordLengthUa();

        Assert.assertEquals(errorMessage, "Пароль повинен містити менше 20 символів без пробілів.");
    }
}