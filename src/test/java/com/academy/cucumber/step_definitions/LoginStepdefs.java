package com.academy.cucumber.step_definitions;


import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.styleConstants.Colors;
import com.academy.utils.props.ConfigProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;


public class LoginStepdefs {
    private  BaseDefinition baseDef;
    private PageContext pageContext;
    private final SoftAssert softAssert;
    protected static ConfigProperties configProperties;
    private LoginModalComponent loginModalComponent;
    private HomePage homePage;
    String errorMessage;

    public LoginStepdefs(BaseDefinition baseDef)  {
        this.baseDef = baseDef;
        pageContext = new PageContext(baseDef.getDriver());
        softAssert = new SoftAssert();
        configProperties = new ConfigProperties();
    }

    @Given("I open the login form")
    public void iOpenTheLoginForm() {
        loginModalComponent = pageContext
                .getAllPages()
                .getHomePage()
                .getHeaderComponent()
                .openLoginForm();
    }

    @When("I enter a valid email")
    public void iEnterAValidEmail() {
        loginModalComponent.
                enterEmail(configProperties.getRegisteredUserEmail());
    }

    @And("I enter a valid password")
    public void iEnterAValidPassword() {
        loginModalComponent.enterPassword(configProperties.getRegisteredUserEmail());
    }

    @Then("the SignIn button should become active")
    public void theSignInButtonShouldBecomeActive() {
        softAssert.assertTrue(loginModalComponent.isSignInButtonActive());
    }

    @And("the SignIn button should be highlighted in green")
    public void theSignInButtonShouldBeHighlightedInGreen() {
        softAssert.assertTrue(loginModalComponent.isHighlightedSignInBtnInColor(Colors.PRIMARY_GREEN));
    }

    @Given("the application is set to {string} language")
    public void theApplicationIsSetToLanguage(String arg0) {
        homePage = pageContext.getAllPages().getHomePage().setLanguage(arg0);
    }

    @And("I enter the password")
    public void iEnterThePassword() {
        loginModalComponent.enterPassword(configProperties.getRegisteredUserPassword()+"abcdefg123456789");
    }

    @And("I click inside the form")
    public void iClickInsideTheForm() {
        loginModalComponent.clickInsideForm();
    }

    @Then("I should see an error message {string} in the password field")
    public void iShouldSeeAnErrorMessageInThePasswordField(String arg1) {
        errorMessage = loginModalComponent.getPasswordField().getErrorMessage();
        softAssert.assertEquals(errorMessage, arg1);
    }
}
