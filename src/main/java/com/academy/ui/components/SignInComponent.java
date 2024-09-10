package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInComponent extends BaseComponent {

    // XPath constants
    private static final String EMAIL_FIELD_XPATH = ".//input[@id='email']";
    private static final String PASSWORD_FIELD_XPATH = ".//input[@id='password']";
    private static final String SIGN_IN_BUTTON_XPATH = ".//button[@type='submit']";
    private static final String ERROR_MESSAGE_XPATH = ".//div[contains(@class, 'alert-general-error')]";

    @FindBy(how = How.XPATH, using = EMAIL_FIELD_XPATH)
    protected WebElement emailField;

    @FindBy(how = How.XPATH, using = PASSWORD_FIELD_XPATH)
    protected WebElement passwordField;

    @FindBy(how = How.XPATH, using = SIGN_IN_BUTTON_XPATH)
    protected WebElement signInButton;

    @FindBy(how = How.XPATH, using = ERROR_MESSAGE_XPATH)
    protected WebElement errorMessageForInvalidPasswordInUa;

    public SignInComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    // Sends email to the email field and returns the current instance
    public SignInComponent sendEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    // Sends password to the password field and returns the current instance
    public SignInComponent sendPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    // Clicks the sign-in button and returns the current instance
    public SignInComponent sendForm() {
        signInButton.click();
        return this;
    }

    // Verifies if the error message is displayed and matches the expected message
    public boolean verifyErrorMessageForInvalidInputUa(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessageForInvalidPasswordInUa));
            return errorMessageForInvalidPasswordInUa.getText().equals(expectedMessage);
        } catch (Exception e) {
            return false;
        }
    }
}