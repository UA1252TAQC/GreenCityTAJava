package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class RegistrationComponent extends BaseComponent {

    @Getter
    @AllArgsConstructor
    public static class RegistrationField {
        private WebElement label;
        private WebElement input;
        private WebElement error;
    }

    @FindBy(how = How.XPATH, using = ".//label[@for='email']")
    private WebElement emailLabel;
    @FindBy(how = How.XPATH, using = ".//input[@id='email']")
    private WebElement emailInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='email-err-msg']//div")
    private WebElement emailError;

    @FindBy(how = How.XPATH, using = ".//label[@for='firstName']")
    private WebElement usernameLabel;
    @FindBy(how = How.XPATH, using = ".//input[@id='firstName']")
    private WebElement usernameInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='firstname-err-msg']//div")
    private WebElement usernameError;

    @FindBy(how = How.XPATH, using = ".//label[@for='password']")
    private WebElement passwordLabel;
    @FindBy(how = How.XPATH, using = ".//input[@id='password']")
    private WebElement passwordInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='password-err-msg']//div")
    private WebElement passwordError;

    @FindBy(how = How.XPATH, using = ".//label[@for='repeatPassword']")
    private WebElement repeatPasswordLabel;
    @FindBy(how = How.XPATH, using = ".//input[@id='repeatPassword']']")
    private WebElement repeatPasswordInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='confirm-err-msg']//div")
    private WebElement repeatPasswordError;


    public RegistrationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public RegistrationField getEmail() {
        return new RegistrationField(emailLabel, emailInput, emailError);
    }

    public RegistrationField getUsername() {
        return new RegistrationField(usernameLabel, usernameInput, usernameError);
    }

    public RegistrationField getPassword() {
        return new RegistrationField(passwordLabel, passwordInput, passwordError);
    }

    public RegistrationField getRepeatPassword() {
        return new RegistrationField(repeatPasswordLabel, repeatPasswordInput, repeatPasswordError);
    }
}
