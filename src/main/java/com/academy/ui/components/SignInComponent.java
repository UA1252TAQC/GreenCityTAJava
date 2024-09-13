package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInComponent extends BaseComponent {

    @FindBy(id = "email")
    protected WebElement emailInput;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Password']")
    protected WebElement passwordInput;
    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    protected WebElement popUpSignInButton;

    public SignInComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public SignInComponent fillTheSignInForm(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        return this;
    }

    public SignInComponent submitSignInForm() {
        popUpSignInButton.click();
        return this;
    }


}
