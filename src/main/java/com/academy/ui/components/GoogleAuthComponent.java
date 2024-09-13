package com.academy.ui.components;

import com.academy.ui.pages.BasePageGreenCity;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GoogleAuthComponent extends BasePageGreenCity {

    @FindBy(how = How.XPATH, using = ".//*[@id=\"identifierId\"]")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = ".//*[@id=\"identifierNext\"]/div/button")
    private WebElement emailNextButton;

    @FindBy(how = How.XPATH, using = ".//*[@id=\"password\"]/div[1]/div/div[1]/input")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = ".//*[@id=\"passwordNext\"]/div/button")
    private WebElement passwordNextButton;

    public GoogleAuthComponent(WebDriver driver) {
        super(driver);
    }

    public GoogleAuthComponent enterEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public GoogleAuthComponent clickEmailSubmitButton() {
        click(emailNextButton);
        sleep(6);
        return this;
    }

    public GoogleAuthComponent enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public GoogleAuthComponent clickPasswordSubmitButton() {
        click(passwordNextButton);
        sleep(1);
        return this;
    }

    public void signInWithGoogle(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }


}
