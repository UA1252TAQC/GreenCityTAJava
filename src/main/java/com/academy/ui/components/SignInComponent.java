package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInComponent extends BaseComponent {
    @FindBy(how = How.XPATH, using = ".//input[@id='email']")
    protected WebElement emailField;

    @FindBy(how = How.XPATH, using = ".//input[@id='password']")
    protected WebElement passwordField;

    @FindBy(how = How.XPATH, using = ".//button[@type='submit']")
    protected WebElement signInButton;

    @FindBy(how = How.XPATH, using = ".//div[contains(@class, 'alert-general-error')]")
    protected WebElement errorMessageForInvalidPasswordInUa;

    public SignInComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public SignInComponent sendEmail(String email){
        emailField.sendKeys(email);
        return this;
    }

    public SignInComponent sendPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public SignInComponent sendForm(){
        signInButton.click();
        return this;
    }

    public boolean verifyErrorMessageForInvalidInputUa(String expectedMessage){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(errorMessageForInvalidPasswordInUa));
        return errorMessageForInvalidPasswordInUa.getText().equals(expectedMessage);
    }

}
