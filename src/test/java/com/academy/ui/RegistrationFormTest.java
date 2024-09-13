package com.academy.ui;

import com.academy.ui.components.GoogleAuthComponent;
import com.academy.ui.components.RegistrationModalComponent;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.pages.greenCity.ProfilePage;
import com.academy.ui.pages.ubs.HomePageUbs;
import com.academy.ui.providers.RegistrationFormTestProvider;
import com.academy.ui.runners.FormTestRunner;
import com.academy.utils.LocalizationUtils;
import com.academy.utils.MailUtils;
import com.academy.utils.mail.Mail;
import com.academy.utils.mail.MailBoxCredentials;
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
    public void setUp(@Optional("Ua") String language) {
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
        HomePage homePage = openHomePage();
        var form = homePage.openRegistrationFormInHeader();

        form.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage));

        Mail mail = mailUtils.getLastEmail(mailBox.getId());

        homePage.openUrlInNewTab(mail.extractActivationLink());
        HomePageUbs ubsPage = new HomePageUbs(driver);

        String actualAccountSubmitMessage = ubsPage.getPopUpMessage();
        softAssert.assertEquals(actualAccountSubmitMessage, localizedMessages.get(expectedAccountSubmitMessage));

        // TODO add login page & parse jwt & add 24 hours validation check
        softAssert.assertAll();
    }

    @Test(dataProvider = "testGoogleSignUp", dataProviderClass = RegistrationFormTestProvider.class)
    public void testGoogleSignUp(String googleEmail, String googlePassword) {
        HomePage homePage = openHomePage();
        var form = homePage.openRegistrationFormInHeader();

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
    public void testRegisteredGreenCity(String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword){
        HomePage homePage = openHomePage();
        var homeForm = homePage.openRegistrationFormInHeader();

        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        // String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        // softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        HomePageUbs ubsPage = openUbsPageInNewTab(homePage);
        RegistrationModalComponent ubsForm = ubsPage.openRegistrationFormInHeader();
        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationErrorMessage = ubsForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualRegistrationErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        softAssert.assertAll();
    }

    @Test(dataProvider = "testRegisteredUbs", dataProviderClass = RegistrationFormTestProvider.class)
    public void testRegisteredUbs(String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword){
        HomePageUbs ubsPage = openUbsPage();
        var ubsForm = ubsPage.openRegistrationFormInHeader();

        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();


        // String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        // softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        HomePage homePage = openHomePageInNewTab(ubsPage);
        RegistrationModalComponent homeForm = homePage.openRegistrationFormInHeader();

        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationErrorMessage = homeForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualRegistrationErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        softAssert.assertAll();
    }

    @Test (dataProvider = "testEmailAlreadyExists" , dataProviderClass = RegistrationFormTestProvider.class)
    public void testEmailAlreadyExists (String expectedRegistrationSuccessMessage , String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword){
        HomePage homePage = openHomePage();
        var homeForm = homePage.openRegistrationFormInHeader();


        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        // String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        // softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        homeForm.sleep(5);
        homeForm = homePage.openRegistrationFormInHeader();
        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualErrorMessage = homeForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        softAssert.assertAll();
    }

    @Test(dataProvider = "testGreenCityRegisteredWithConfirmEmail", dataProviderClass = RegistrationFormTestProvider.class)
    public void testGreenCityRegisteredWithConfirmEmail(String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePage homePage = openHomePage();
        var greenCityForm = homePage.openRegistrationFormInHeader();

        greenCityForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        // String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        // softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        greenCityForm.sleep(5);

        Mail mail = mailUtils.getLastEmail(mailBox.getId());
        homePage.openUrlInNewTab(mail.extractActivationLink());
        greenCityForm.sleep(5);

        HomePageUbs ubsPage = openUbsPageInNewTab(homePage);
        var ubsForm = ubsPage.openRegistrationFormInHeader();
        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationErrorMessage = ubsForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualRegistrationErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        softAssert.assertAll();
    }

    @Test(dataProvider = "testUbsRegisteredWithConfirmEmail", dataProviderClass = RegistrationFormTestProvider.class)
    public void testUbsRegisteredWithConfirmEmail(String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePageUbs ubsPage = openUbsPage();
        RegistrationModalComponent ubsForm = ubsPage.openRegistrationFormInHeader();

        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        
        // String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        // softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationErrorMessage));
        ubsForm.sleep(5);

        Mail mail = mailUtils.getLastEmail(mailBox.getId());
        ubsPage.openUrlInNewTab(mail.extractActivationLink());
        ubsForm.sleep(5);

        HomePage homePage = openHomePageInNewTab(ubsPage);
        RegistrationModalComponent greenCityForm = homePage.openRegistrationFormInHeader();
        greenCityForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationErrorMessage = greenCityForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualRegistrationErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        softAssert.assertAll();
    }



    private HomePageUbs openUbsPageInNewTab(HomePage homePage) {
        homePage.openUrlInNewTab(configProperties.getBaseUrl() + "/#/ubs");
        HomePageUbs page = new HomePageUbs(driver);
        return page;
    }

    private HomePage openHomePageInNewTab(HomePageUbs ubsPage) {
        ubsPage.openUrlInNewTab(configProperties.getBaseUrl() + "/#/greenCity");
        HomePage page = new HomePage(driver);
        return page;
    }

    private HomePageUbs openUbsPage() {
        driver.get(configProperties.getBaseUrl() + "/#/ubs");
        return new HomePageUbs(driver).setLanguage(language);
    }

    private HomePage openHomePage() {
        driver.get(configProperties.getBaseUrl() + "/#/greenCity");
        return new HomePage(driver).setLanguage(language);
    }
}
