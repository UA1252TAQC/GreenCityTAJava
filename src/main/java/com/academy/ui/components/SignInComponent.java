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
    @FindBy(how = How.XPATH, using = ".//input[@id='email']")
    protected WebElement emailField;

    @FindBy(how = How.XPATH, using = ".//input[@id='password']")
    protected WebElement passwordField;

    @FindBy(how = How.XPATH, using = ".//img[@class='main-picture']")
    protected WebElement mainPicture;

    public SignInComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public SignInComponent sendEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public SignInComponent sendPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public SignInComponent randomClick() {
        mainPicture.click();
        return this;
    }

    public String  getErrorMessageForExceedingPasswordLengthUa() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = driver.findElement(By.xpath(
                ".//div[@id='pass-err-msg']//div"));
        wait.until(ExpectedConditions.visibilityOf(error));
        return error.getText();
    }
}