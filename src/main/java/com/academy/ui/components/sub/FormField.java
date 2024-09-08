package com.academy.ui.components.sub;


import com.academy.ui.Base;
import org.openqa.selenium.WebElement;

public class FormField extends Base {
    protected final WebElement input;
    protected final WebElement error;
    protected WebElement dynamicError;

    public FormField(WebElement input, WebElement error) {
        super(null);
        this.input = input;
        this.error = error;
    }

    public FormField(WebElement input, WebElement error, WebElement dynamicError) {
        super(null);
        this.input = input;
        this.error = error;
        this.dynamicError = dynamicError;
    }

    public void enter(String text) {
        input.sendKeys(text);
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
}