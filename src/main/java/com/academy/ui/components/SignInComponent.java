package com.academy.ui.components;

import org.openqa.selenium.By;
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
    private static final String FORGOT_PASSWORD_XPATH = ".//div[contains(@class, 'forgot-wrapper-ubs')]";
    private static final String FORGOT_PASSWORD_ROOT_XPATH = ".//div[@class='wrapper']";


    @FindBy(how = How.XPATH, using = FORGOT_PASSWORD_XPATH)
    protected WebElement forgotPasswordButton;

    @FindBy(how = How.XPATH, using = EMAIL_FIELD_XPATH)
    protected WebElement emailField;

    @FindBy(how = How.XPATH, using = PASSWORD_FIELD_XPATH)
    protected WebElement passwordField;

    @FindBy(how = How.XPATH, using = SIGN_IN_BUTTON_XPATH)
    protected WebElement signInButton;

    @FindBy(how = How.XPATH, using = ERROR_MESSAGE_XPATH)
    protected WebElement errorMessageForInvalidPasswordInUa;

    private ForgotPasswordComponent forgotPasswordComponent;

    public SignInComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    // Opens the forgot password window and returns the ForgotPasswordComponent
    public ForgotPasswordComponent openForgotPasswordAndGetComponent() {
        clickElement(forgotPasswordButton);
        WebElement forgotPasswordRootElement = getForgotPasswordRootElement();
        if (forgotPasswordComponent == null) {
            forgotPasswordComponent = new ForgotPasswordComponent(driver, forgotPasswordRootElement);
        }
        return forgotPasswordComponent;
    }

    // Waits for and returns the forgot password root element
    private WebElement getForgotPasswordRootElement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FORGOT_PASSWORD_ROOT_XPATH)));
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