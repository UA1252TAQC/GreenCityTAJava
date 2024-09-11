package com.academy.ui;

import com.academy.ui.pages.BasePage;
import com.academy.ui.runners.BaseTestRunner;
import com.academy.utils.ConfigProperties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest extends BaseTestRunner {
    private BasePage basePage;

    @BeforeMethod
    public void setUpSignInTest() {
        basePage = new BasePage(driver);
    }

    @Test
    public void verifyErrorMessageForInvalidPasswordInUa() {
        ConfigProperties configProperties = new ConfigProperties();
        basePage.getHeaderComponent().selectLanguage("UA");

        String errorMessage = basePage
                .getHeaderComponent()
                .getSignInFormModal()
                .sendEmail(configProperties.getTestEmail())
                .sendPassword(configProperties.getTestPassword())
                .randomClick()
                .getErrorMessageForExceedingPasswordLengthUa();

        Assert.assertEquals(errorMessage, "Пароль повинен містити менше 20 символів без пробілів.");
    }
}
