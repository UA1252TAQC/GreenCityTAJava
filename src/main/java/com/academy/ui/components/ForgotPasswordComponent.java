package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordComponent extends SignInComponent{
    @FindBy(how = How.XPATH, using = ".//div[@class='restore-password-container']")
    protected WebElement forgotPassportWindow;
    public ForgotPasswordComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    public boolean isForgotPassportWindowDisplayed() {
        return forgotPassportWindow.isDisplayed();
    }
}
