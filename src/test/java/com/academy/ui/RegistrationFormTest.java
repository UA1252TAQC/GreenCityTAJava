package com.academy.ui;

import com.academy.ui.providers.RegistrationFormTestProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.academy.ui.providers.RegistrationFormFieldTestProvider;
import com.academy.ui.runners.FormTestRunner;
import com.academy.utils.LocalizationProperties;
import com.google.common.collect.ImmutableMap;

import java.time.Duration;

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


    @Test(dataProvider = "testPopUpSignUpValidation", dataProviderClass = RegistrationFormTestProvider.class)
    public void testSuccessfulRegistrationPopup( Messages expectedMessage, String email, String username, String password, String repeatPassword) {
        form.fillForm(email,username,password,repeatPassword).submit();
        String actualMessage = form.getPopUpMessage();
        String expectedLocalizedMessage = localizedMessages.get(expectedMessage);
        softAssert.assertEquals(actualMessage, expectedLocalizedMessage);
        softAssert.assertAll();
    }










}
