package com.academy.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInComponent extends HeaderComponent {
    @FindBy(how = How.XPATH, using = ".//div[contains(@class, 'forgot-wrapper-ubs')]")
    protected WebElement forgotPasswordButton;

    private ForgotPasswordComponent forgotPasswordComponent;

    public SignInComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public ForgotPasswordComponent getForgotPasswordComponent() {
        if (forgotPasswordComponent == null) {
            forgotPasswordComponent = new ForgotPasswordComponent(driver, getForgotPasswordRootElement());
            return forgotPasswordComponent;
        }
        return forgotPasswordComponent;
    }

    public WebElement getForgotPasswordRootElement() {
        openForgotPasswordWindow();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//div[@class='wrapper']")));
    }


    public void openForgotPasswordWindow() {
        clickElement(forgotPasswordButton);
    }
}