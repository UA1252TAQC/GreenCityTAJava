package com.academy.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordComponent extends BaseComponent {
    private static final String FORGOT_PASSWORD_WINDOW_XPATH = ".//div[@class='restore-password-container']";

    @FindBy(how = How.XPATH, using = FORGOT_PASSWORD_WINDOW_XPATH)
    private WebElement forgotPasswordWindow;

    public ForgotPasswordComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    // Checks if the forgot password window is displayed
    public boolean isForgotPasswordWindowDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(forgotPasswordWindow));
        return forgotPasswordWindow.isDisplayed();
    }
}