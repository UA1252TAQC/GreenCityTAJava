package com.academy.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SignInModal extends BasePage{

    @FindBy(how = How.ID, using = "email")
    protected WebElement emailInput;

    @FindBy(how = How.ID, using = "password")
    protected WebElement passwordInput;

    @FindBy(how = How.CSS, using = "button.greenStyle")
    protected WebElement signInButton;


    public SignInModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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

    //signInButton
    private void clickSignInButton() {
        signInButton.click();
    }

    // Functional

    private void enterEmailInput(String email) {
        clearEmailInput();
        clearEmailInput();
        setEmailInput(email);
    }

    private void enterPasswordInput(String password) {
        clickPasswordInput();
        clearPasswordInput();
        setPasswordInput(password);
    }

    private void fillLogin(String email, String password) {
        enterEmailInput(email);
        enterPasswordInput(password);
    }

    public HomePage successfulLogin(String email, String password) {
        fillLogin(email, password);
        clickSignInButton();
        return new HomePage(driver);
    }
}
