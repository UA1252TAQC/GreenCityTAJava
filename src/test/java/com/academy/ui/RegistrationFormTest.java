package com.academy.ui;

import com.academy.ui.components.GoogleAuthComponent;
import com.academy.ui.components.RegistrationModalComponent;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.pages.greenCity.ProfilePage;
import com.academy.ui.pages.ubs.HomePageUbs;
import com.academy.ui.providers.RegistrationFormTestProvider;
import com.academy.ui.runners.MethodDriverRunner;
import com.academy.utils.MailUtils;
import com.academy.utils.mail.Mail;
import com.academy.utils.mail.MailBoxCredentials;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.*;

public class RegistrationFormTest extends MethodDriverRunner {
    private ImmutableMap<String, String> localizedMessages;
    private MailUtils mailUtils;
    private String language;

    @BeforeClass
    @Parameters({"language"})
    public void setUp(@Optional("Ua") String language) {
        this.localizedMessages = localizationUtils.getFormMessages(language);
        this.mailUtils = new MailUtils();
        this.language = language;
    }

    @Test(dataProvider = "testPopUpSignUpValidation", dataProviderClass = RegistrationFormTestProvider.class)
    public void testPopUpSignUpValidation(String expectedRegistrationSuccessMessage, String expectedAccountSubmitMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePage homePage = openHomePage();
        var form = homePage.getHeaderComponent().openRegistrationForm();

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
    public void testGoogleSignUp(String googleEmail, String googlePassword, String expectedGoogleName) {
        HomePage homePage = openHomePage();
        var form = homePage.getHeaderComponent().openRegistrationForm();

        GoogleAuthComponent googleForm = form.openAuthGoogleForm();
        googleForm.enterEmail(googleEmail)
                .clickEmailSubmitButton()
                .enterPassword(googlePassword)
                .clickPasswordSubmitButton();

        homePage.switchToActiveTab();

        ProfilePage profilePage = new ProfilePage(driver);
        softAssert.assertNotNull(profilePage.getAuthToken());
        softAssert.assertTrue(driver.getCurrentUrl().startsWith(configProperties.getBaseUrl() + "/#/profile"));
        softAssert.assertEquals(profilePage.getHeaderComponent().getUsername(), expectedGoogleName);
        softAssert.assertAll();
    }

    @Test(dataProvider = "testRegisteredGreenCity", dataProviderClass = RegistrationFormTestProvider.class)
    public void testRegisteredGreenCity(String expectedRegistrationSuccessMessage, String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword){
        HomePage homePage = openHomePage();
        var homeForm = homePage.getHeaderComponent().openRegistrationForm();

        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage));

        HomePageUbs ubsPage = openUbsPageInNewTab(homePage);
        RegistrationModalComponent ubsForm = ubsPage.openRegistrationFormInHeader();
        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationErrorMessage = ubsForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualRegistrationErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        softAssert.assertAll();
    }

    @Test(dataProvider = "testRegisteredUbs", dataProviderClass = RegistrationFormTestProvider.class)
    public void testRegisteredUbs(String expectedRegistrationSuccessMessage, String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword){
        HomePageUbs ubsPage = openUbsPage();
        var ubsForm = ubsPage.openRegistrationFormInHeader();

        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();


        String actualRegistrationSuccessMessage = ubsPage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage));

        HomePage homePage = openHomePageInNewTab(ubsPage);
        RegistrationModalComponent homeForm = homePage.getHeaderComponent().openRegistrationForm();

        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationErrorMessage = homeForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualRegistrationErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        softAssert.assertAll();
    }

    @Test (dataProvider = "testEmailAlreadyExists" , dataProviderClass = RegistrationFormTestProvider.class)
    public void testEmailAlreadyExists(String expectedRegistrationSuccessMessage, String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword){
        HomePage homePage = openHomePage();
        var homeForm = homePage.getHeaderComponent().openRegistrationForm();

        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage));

        homeForm = homePage.getHeaderComponent().openRegistrationForm();
        homeForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualErrorMessage = homeForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        softAssert.assertAll();
    }

    @Test(dataProvider = "testGreenCityRegisteredWithConfirmEmail", dataProviderClass = RegistrationFormTestProvider.class)
    public void testGreenCityRegisteredWithConfirmEmail(String expectedRegistrationSuccessMessage, String expectedAccountSubmitMessage, String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePage homePage = openHomePage();
        var greenCityForm = homePage.getHeaderComponent().openRegistrationForm();

        greenCityForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationSuccessMessage = homePage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage));


        Mail mail = mailUtils.getLastEmail(mailBox.getId());
        homePage.openUrlInNewTab(mail.extractActivationLink());

        String actualAccountSubmitMessage = homePage.getPopUpMessage();
        softAssert.assertEquals(actualAccountSubmitMessage, localizedMessages.get(expectedAccountSubmitMessage));

        HomePageUbs ubsPage = openUbsPageInNewTab(homePage);
        var ubsForm = ubsPage.openRegistrationFormInHeader();
        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationErrorMessage = ubsForm.getEmail().getErrorMessage();
        softAssert.assertEquals(actualRegistrationErrorMessage, localizedMessages.get(expectedRegistrationErrorMessage));

        softAssert.assertAll();
    }

    @Test(dataProvider = "testUbsRegisteredWithConfirmEmail", dataProviderClass = RegistrationFormTestProvider.class)
    public void testUbsRegisteredWithConfirmEmail(String expectedRegistrationSuccessMessage, String expectedAccountSubmitMessage, String expectedRegistrationErrorMessage, MailBoxCredentials mailBox, String username, String password, String repeatPassword) {
        HomePageUbs ubsPage = openUbsPage();
        RegistrationModalComponent ubsForm = ubsPage.openRegistrationFormInHeader();

        ubsForm.fillForm(mailBox.getAddress(), username, password, repeatPassword).submit();

        String actualRegistrationSuccessMessage = ubsPage.getPopUpMessage();
        softAssert.assertEquals(actualRegistrationSuccessMessage, localizedMessages.get(expectedRegistrationSuccessMessage));


        Mail mail = mailUtils.getLastEmail(mailBox.getId());
        ubsPage.openUrlInNewTab(mail.extractActivationLink());

        String actualAccountSubmitMessage = ubsPage.getPopUpMessage();
        softAssert.assertEquals(actualAccountSubmitMessage, localizedMessages.get(expectedAccountSubmitMessage));

        HomePage homePage = openHomePageInNewTab(ubsPage);
        RegistrationModalComponent greenCityForm = homePage.getHeaderComponent().openRegistrationForm();
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
