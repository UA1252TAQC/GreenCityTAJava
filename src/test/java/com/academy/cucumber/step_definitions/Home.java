package com.academy.cucumber.step_definitions;

import io.cucumber.java.en.Given;
import org.testng.asserts.SoftAssert;

public class Home {
    private  BaseDefinition baseDef;
    private PageContext pageContext;
    private final SoftAssert softAssert;
    public Home(BaseDefinition baseDef)  {
        this.baseDef = baseDef;
        pageContext = new PageContext(baseDef.getDriver());
        softAssert = new SoftAssert();
    }

    @Given("Log in as User")
    public void LoginAsUser(){
        pageContext
                .getAllPages()
                .getHomePage()
                .getHeaderComponent()
                .openLoginForm()
                .enterEmail("test")
                .enterPassword("test").clickSignInButton();
    }
}
