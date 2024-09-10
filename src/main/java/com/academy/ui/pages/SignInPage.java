package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends BasePage {
    @FindBy(xpath = "//div[@class='header_container']")
    private WebElement signInButtonOnHeader;

    @FindBy(xpath = "//*[@id='email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@class='greenStyle']")
    private WebElement submitButton;

    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignInPage openSignInWindow() {
        signInButtonOnHeader.click();
        return this;
    }

    public SignInPage enterEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public SignInPage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public SignInPage clickSubmit() {
        submitButton.click();
        return this;
    }

    public boolean isSubmitButtonActive() {
        // Добавьте явное ожидание для кнопки
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        return submitButton.isEnabled();
    }


    public String getSubmitButtonColor() {
        // Get the background color of the submit button
        return submitButton.getCssValue("background-color");
    }
}