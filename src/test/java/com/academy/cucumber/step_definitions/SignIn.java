package com.academy.cucumber.step_definitions;

import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.pages.greenCity.ProfilePage;
import com.academy.utils.props.ConfigProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class SignIn {
    private  BaseDefinition baseDef;
    private PageContext pageContext;
    private final SoftAssert softAssert;
    private HomePage homePage;
    private ProfilePage profilePage;
    private LoginModalComponent loginForm;


    public SignIn(BaseDefinition baseDef)  {
        this.baseDef = baseDef;
        pageContext = new PageContext(baseDef.getDriver());
        softAssert = new SoftAssert();
    }

    @Given("the interface language is set to {string}")
    public void interfaceLanguageIsSetTo(String language) {
        homePage.setLanguage(language);
    }
    @When("user clicks the {string} Tab in the header")
    public void userClicksTabInHeader(String string) {
        loginForm = homePage.getHeaderComponent().openLoginForm();
    }
    @When("user enters a valid email {string}")
    public void userEntersValidEmail(String email) {
        loginForm.enterEmail(email);
    }
    @When("user enters a invalid password {string}")
    public void userEntersInvalidPassword(String password) {
        loginForm.enterPassword(password);
    }
    @When("user clicks {string} button")
    public void userClicksButton(String string) {
        loginForm.clickSignInButton();
    }

    @Given("GreenCity is open")
    public void greencityIsOpen() {
        homePage = pageContext.getAllPages().getHomePage();
    }

    @Then("the {string} error message is appeared")
    public void theErrorMessageIsAppeared(String expected) {
        String actual = loginForm.getPasswordErrorMessage();
        Assert.assertEquals(actual, expected);
    }

    @When("user enter a registered user email")
    public void userEnterARegisteredUserEmail() {
        String email = BaseDefinition.getTestValueProvider().getRegisteredUserEmail();
        loginForm.enterEmail(email);
    }

    @And("user enter a registered user password")
    public void userEnterARegisteredUserPassword() {
        String password = BaseDefinition.getTestValueProvider().getRegisteredUserPassword();
        loginForm.enterPassword(password);
    }

    @Then("user should be redirected to the user profile page")
    public void userShouldBeRedirectedToTheUserProfilePage() {
        String expectedUrl = BaseDefinition.getTestValueProvider().getProfilePageGreenCityUrl()
                + "/" + BaseDefinition.getTestValueProvider().getRegisteredUserId();
        String actualUrl = baseDef.getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @And("user clicks Sign In button with successful login")
    public void userClicksSignInButtonWithSuccessfulLogin() {
        loginForm.clickSignInButtonSuccessfulLogin();
    }

    @And("user name should be in the header")
    public void userNameShouldBeInTheHeader() {
        String expectedUserName = BaseDefinition.getTestValueProvider().getRegisteredUserName();
        String actualUserName = pageContext
                .getAllPages()
                .getProfilePage()
                .getHeaderComponent()
                .getUserNameText();
        Assert.assertEquals(actualUserName, expectedUserName);
    }
}
