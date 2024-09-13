package com.academy.ui.components.sub.form;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PasswordField extends BaseComponent {
    @FindBy(how = How.XPATH, using = ".//input[@id='password']")
    protected WebElement input;
    @FindBy(how = How.XPATH, using = ".//div[@id='password-err-msg']//div")
    protected WebElement error;

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