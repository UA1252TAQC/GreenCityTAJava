package com.academy.ui.components;

import com.academy.ui.pages.HomePage;
import com.academy.ui.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInComponent extends BaseComponent {

    @FindBy(how = How.ID, using = "email")
    protected WebElement emailInput;

    @FindBy(how = How.ID, using = "password")
    protected WebElement passwordInput;

    @FindBy(how = How.CSS, using = "button.greenStyle")
    protected WebElement signInButton;


    public SignInComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    //emailInput
    private void clickEmailInput() {
        emailInput.click();
    }

    private void clearEmailInput() {
        emailInput.clear();
    }

    private void setEmailInput(String email) {
        emailInput.sendKeys(email);
    }

    //passwordInput
    private void clickPasswordInput() {
        passwordInput.click();
    }

    private void clearPasswordInput() {
        passwordInput.clear();
    }

    private void setPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    public boolean isSignButtonActive() {
        return signInButton.isEnabled();
    }

    public SignInComponent fillEmailInput(String email) {
        clickEmailInput();
        clearEmailInput();
        setEmailInput(email);
        return this;
    }

    public SignInComponent fillPasswordInput(String password) {
        clickPasswordInput();
        clearPasswordInput();
        setPasswordInput(password);
        return this;
    }

    public SignInComponent clickSignInUnSuccess() {
        signInButton.click();
        return this;
    }

    public ProfilePage clickSignInSuccess() {
        signInButton.click();
        return new ProfilePage(driver);
    }
}
