package com.academy.ui;

import com.academy.ui.components.RegistrationComponent;
import com.academy.ui.pages.HomePage;
import com.academy.ui.pages.UbsPage;
import com.academy.ui.providers.RegistrationFormTestProvider;
import com.academy.ui.runners.BaseTestRunner;
import com.academy.utils.LocalizationUtils;
import com.academy.utils.MailUtils;
import com.academy.utils.mail.Mail;
import com.academy.utils.mail.MailBoxCredentials;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationFormTest extends BaseTestRunner {
    private ImmutableMap<String, String> localizedMessages;
    private RegistrationComponent form;
    private SoftAssert softAssert;
    private MailUtils mailUtils;
    private HomePage page;

    @BeforeMethod
    @Parameters({"language"})
    public void setUp(@Optional("ua") String language) {
        page = new HomePage(driver).setLanguage(language);
        LocalizationUtils properties = new LocalizationUtils();
        localizedMessages = properties.getRegistrationMessages(language);

        mailUtils = new MailUtils();
        form = page.openRegistrationFormInHeader();
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "testPopUpSignUpValidation", dataProviderClass = RegistrationFormTestProvider.class)
    public void testPopUpSignUpValidation(String expectedRegistrationSuccessMessage, String expectedAccountSubmitMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        form.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        //String actualRegistrationSuccessMessage = page.getSuccessRegisteredMessage();
        //softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage));

        Mail mail = mailUtils.getLastEmail(mailBox.getId());

        page.openUrlInNewTab(mail.extractActivationLink());
        UbsPage ubsPage = new UbsPage(driver);

        String actualAccountSubmitMessage = ubsPage.getAccountSubmitPopUpMessage();
        softAssert.assertEquals(actualAccountSubmitMessage, localizedMessages.get(expectedAccountSubmitMessage));

        // TODO add login & parse jwt & add 24 hours validation check
        softAssert.assertAll();
    }
}
