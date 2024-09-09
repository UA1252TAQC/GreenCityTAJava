package com.academy.ui.components.sub;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormField extends BaseComponent {
    protected final WebElement input;
    protected final WebElement error;
    protected WebElement dynamicError;

    public FormField(WebDriver driver, WebElement rootElement, WebElement input, WebElement error) {
        super(driver, rootElement);
        this.input = input;
        this.error = error;
    }

    public FormField(WebDriver driver, WebElement rootElement, WebElement input, WebElement error, WebElement dynamicError) {
        super(driver, rootElement);
        this.input = input;
        this.error = error;
        this.dynamicError = dynamicError;
    }

    public FormField enter(String text) {
        input.sendKeys(text);
        return this;
    }

    public boolean isDisplayed() {
        return isDisplayed(input);
    }

    public String getErrorMessage() {
        if (isDisplayed(dynamicError)) {
            return dynamicError.getText();
        }
        if (isDisplayed(error)) {
            return error.getText();
        }
        return null;
    }

    public boolean isValid() {
        return !((isDisplayed(error)) || (isDisplayed(dynamicError)));
    }

    public FormField clearEnter(String text) {
        input.click();
        input.clear();
        return this.enter(text);
    }
}