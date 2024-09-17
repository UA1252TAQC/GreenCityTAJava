package com.academy.ui.components.sub.form;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordField extends BaseComponent {
    @FindBy(xpath = ".//input[@id='password']")
    private WebElement input;
    @FindBy(xpath = ".//div[@id='pass-err-msg']//div")
    private WebElement error;
    @FindBy(xpath = ".//div[contains(@class, 'alert-general-error')]")
    private WebElement invalidCredentialsError;

    public PasswordField(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void enter(String text) {
        input.sendKeys(text);
    }

    public boolean isDisplayed() {
        return isDisplayed(input);
    }

    public String getErrorMessage() {
        if (isDisplayed(error)) {
            return error.getText();
        } else if(isDisplayed(invalidCredentialsError)){
            return invalidCredentialsError.getText();
        }
        return null;
    }

    public boolean isValid() {
        return !(isDisplayed(error));
    }

    public void clear() {
        click(input);
        clear(input);
    }
}