package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class SignInComponent extends BaseComponent {
    @FindBy(how = How.XPATH, using = "//input[@id='email']")
    protected WebElement emailField;

    public SignInComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void sendEmail(String email){
        emailField.sendKeys(email);
    }
}
