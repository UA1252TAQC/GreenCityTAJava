package com.academy.cucumber.step_definitions;

import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.pages.greenCity.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class SignIn {
    private BaseDefinition baseDef;
    private PageContext pageContext;
    private final SoftAssert softAssert;
    private HomePage homePage;
    private LoginModalComponent loginForm;

    public SignIn(BaseDefinition baseDef) {
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

    @When("user enters a valid password {string}")
    public void userEntersValidPassword(String password) {
        loginForm.enterPassword(password);
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

    @Then("the {string} email error message is appeared")
    public void theEmailErrorMessageIsAppeared(String expected) {
        String actual = loginForm.getEmailField().getErrorMessage();
        Assert.assertEquals(actual, expected);
    }
}
