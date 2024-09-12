package com.academy.ui;

import com.academy.ui.components.GoogleAuthComponent;
import com.academy.ui.components.RegistrationComponent;
import com.academy.ui.components.UbsHeaderComponent;
import com.academy.ui.pages.BasePage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.pages.UbsPage;
import com.academy.ui.providers.RegistrationFormTestProvider;
import com.academy.ui.runners.FormTestRunner;
import com.academy.utils.LocalizationUtils;
import com.academy.utils.MailUtils;
import com.academy.utils.mail.Mail;
import com.academy.utils.mail.MailBoxCredentials;
import com.academy.utils.props.ConfigProperties;
import com.academy.utils.props.ConfigProperties;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class RegistrationFormTest extends FormTestRunner {
    private ImmutableMap<String, String> localizedMessages;
    private ConfigProperties configProperties;
    private SoftAssert softAssert;
    private MailUtils mailUtils;
    private String language;

    @BeforeClass
    @Parameters({"language"})
    public void setUp(@Optional("ua") String language) {
        this.localizedMessages =  new LocalizationUtils().getRegistrationMessages(language);
        this.mailUtils = new MailUtils();
        this.language = language;
        this.configProperties = new ConfigProperties();
    }

    @BeforeMethod
    public void setUpAssert() {
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "testPopUpSignUpValidation", dataProviderClass = RegistrationFormTestProvider.class)
    public void testPopUpSignUpValidation(String expectedRegistrationSuccessMessage, String expectedAccountSubmitMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePage homePage = getHomePage();
        RegistrationComponent form = homePage.openRegistrationFormInHeader();

        form.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        //String actualRegistrationSuccessMessage = page.getSuccessRegisteredMessage(); doesnt work on prod
        //softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage));

        Mail mail = mailUtils.getLastEmail(mailBox.getId());

        homePage.openUrlInNewTab(mail.extractActivationLink());
        UbsPage ubsPage = new UbsPage(driver);

        String actualAccountSubmitMessage = ubsPage.getAccountSubmitPopUpMessage();
        softAssert.assertEquals(actualAccountSubmitMessage, localizedMessages.get(expectedAccountSubmitMessage));

        // TODO add login page & parse jwt & add 24 hours validation check
        softAssert.assertAll();
    }

    @Test(dataProvider = "testGoogleSignUp", dataProviderClass = RegistrationFormTestProvider.class)
    public void testGoogleSignUp(String googleEmail, String googlePassword) {
        HomePage homePage = getHomePage();
        RegistrationComponent form = homePage.openRegistrationFormInHeader();


        GoogleAuthComponent googleForm = form.openAuthGoogleForm();
        googleForm.enterEmail(googleEmail)
                .clickEmailSubmitButton()
                .enterPassword(googlePassword)
                .clickPasswordSubmitButton();

        homePage.switchToActiveTab();

        ProfilePage profilePage = new ProfilePage(driver);
        softAssert.assertNotNull(profilePage.getAuthToken());
        softAssert.assertAll();

    }

    @Test(dataProvider = "testRegisteredGreenCity", dataProviderClass = RegistrationFormTestProvider.class)
    public void testRegisteredGreenCity(String expectedRegistrationErrorMessage,MailBoxCredentials mailBox, String username, String password, String repeatPassword){
        page.openUrl(configProperties.getBaseUrl() + "/#/greenCity");
        HomePage homePage = new HomePage(driver).setLanguage(language);

        RegistrationComponent form = homePage.openRegistrationFormInHeader();


        form.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        page.openUrlInNewTab(configProperties.getBaseUrl() + "/#/ubs");
        UbsPage ubsPage = new UbsPage(driver);
        UbsHeaderComponent headerComponent = ubsPage.getHeaderComponent();
        RegistrationComponent registrationForm = headerComponent.openRegistrationForm();
        registrationForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualAccountSubmitMessage = ubsPage.getAccountSubmitPopUpMessage();
        softAssert.assertEquals(actualAccountSubmitMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        softAssert.assertAll();

    }

    private HomePage getHomePage() {
        driver.get(configProperties.getBaseUrl() + "/#/greenCity");
        return new HomePage(driver).setLanguage(language);
    }
}
