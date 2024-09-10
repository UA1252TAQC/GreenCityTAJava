package com.academy.ui;

import com.academy.ui.providers.RegistrationFormTestProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.academy.ui.components.RegistrationComponent;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.FormTestRunner;
import com.academy.utils.MailUtils;
import com.academy.utils.mail.Mail;
import com.academy.utils.mail.MailBoxCredentials;
import com.academy.utils.props.LocalizationProperties;
import com.google.common.collect.ImmutableMap;

public class RegistrationFormTest extends FormTestRunner {
    private ImmutableMap<String, String> localizedMessages;
    private RegistrationComponent form;
    private SoftAssert softAssert;
    private MailUtils mailUtils;
    private HomePage page;

    @BeforeClass
    @Parameters({"language"})
    public void setUp(@Optional("ua") String language) {
        page = new HomePage(driver).setLanguage(language);

        LocalizationProperties properties = new LocalizationProperties();
        localizedMessages = properties.getRegistrationMessages(language);

        mailUtils = new MailUtils();
    }

    @BeforeMethod
    public void setUpMethod() {
        form = page.openRegistrationFormInHeader();
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "testPopUpSignUpValidation", dataProviderClass = RegistrationFormTestProvider.class)
    public void testPopUpSignUpValidation(String expectedRegistrationSuccessMessage, String expectedAccountSubmitMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        form.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationSuccessMessage = form.getSuccessRegisteredMessage();

        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage));

        Mail mail = mailUtils.getLastEmail(mailBox.getId());

        page.openUrlInNewTab(mail.extractActivationLink()); //TODO open ubs page

        String actualAccountSubmitMessage = null; // ubs.get pop up
        softAssert.assertEquals(actualAccountSubmitMessage, localizedMessages.get(expectedAccountSubmitMessage));
        
        // TODO add login & parse jwt & add 24 hours validation check
        softAssert.assertAll();
    }
}
