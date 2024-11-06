package com.academy.cucumber.step_definitions;


import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.styleConstants.Colors;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;


public class FOMyStepdefs{
    private  BaseDefinition baseDef;
    private PageContext pageContext;
    private final SoftAssert softAssert;
    public FOMyStepdefs(BaseDefinition baseDef)  {
        this.baseDef = baseDef;
        pageContext = new PageContext(baseDef.getDriver());
        softAssert = new SoftAssert();
    }


    private LoginModalComponent loginModalComponent;


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
                enterEmail("alexsasha878794@gmail.com");
    }

    @And("I enter a valid password")
    public void iEnterAValidPassword() {
        loginModalComponent.enterPassword("P@$$w0rd");
    }

    @Then("the {string} button should become active")
    public void theButtonShouldBecomeActive(String arg0) {
        softAssert.assertTrue(loginModalComponent.isSignInButtonActive());
    }

    @And("the {string} button should be highlighted in green")
    public void theButtonShouldBeHighlightedInGreen(String arg0) {
        softAssert.assertTrue(loginModalComponent.isHighlightedSignInBtnInColor(Colors.PRIMARY_GREEN));
    }
}
