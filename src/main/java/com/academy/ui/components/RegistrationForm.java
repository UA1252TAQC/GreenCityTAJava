package com.academy.ui.components;

import com.academy.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationForm extends BasePage {
    private final By userNameField = By.xpath("//*[@id='firstName']");
    private final By emailField = By.xpath("//*[@id='email']");
    private final By passwordField = By.xpath("//*[@id='password']");
    private final By confirmPasswordField = By.xpath("//*[@id='repeatPassword']");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By errorMessageLocator = By.xpath("//*[@id='firstname-err-msg']/app-error/div");

    public RegistrationForm(WebDriver driver) {
        super(driver);
    }

    public void enterUserName(String userName) {
        WebElement userNameInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(userNameField));
        userNameInput.clear();
        userNameInput.sendKeys(userName);
    }

    public void enterEmail(String email) {
        WebElement emailInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        WebElement confirmPasswordInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(confirmPassword);
    }

    public String getUserName() {
        WebElement userNameInput = driver.findElement(userNameField);
        return userNameInput.getAttribute("value");
    }

    public void submitForm() {
        WebElement submitBtn = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(submitButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
    }

    public String getUserNameValidationError() {
        WebElement errorElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorElement.getText();
    }
}