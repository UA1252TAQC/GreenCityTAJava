package com.academy.ui.components.sub;


import lombok.AllArgsConstructor;
import org.openqa.selenium.WebElement;
import com.academy.ui.utils.WebElementUtils;

@AllArgsConstructor
public class FormField {
    protected final WebElement input;
    protected final WebElement error;
    protected final WebElement dynamicError;

    public void enter(String text) {
        input.sendKeys(text);
    }

    public boolean isDisplayed() {
        return WebElementUtils.isDisplayed(input);
    }

    public String getErrorMessage() {
        if (WebElementUtils.isDisplayed(dynamicError)) {
            return dynamicError.getText();
        }
        if (WebElementUtils.isDisplayed(error)) {
            return error.getText();
        }
        return null;
    }

    public boolean isValid() {
        return !((WebElementUtils.isDisplayed(error)) || (WebElementUtils.isDisplayed(dynamicError)));
    }
}