package com.academy.ui.components.sub.form;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordField extends BaseComponent {
    @FindBy(xpath = ".//input[@id='password']")
    private WebElement input;
    @FindBy(xpath = ".//div[contains(@class, 'alert-general-error')] | .//div[@id='password-err-msg']/app-error")
    private WebElement error;

    public PasswordField(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void enter(String text) {
        if (text != null) {
            input.sendKeys(text);
        }
    }

    public boolean isDisplayed() {
        return isDisplayed(input);
    }

    public String getErrorMessage() {
        if (isDisplayed(error)) {
            return error.getText();
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

    public boolean isPasswordFieldEmpty(){
        return input.getAttribute("class").contains("ng-pristine");
    }
}