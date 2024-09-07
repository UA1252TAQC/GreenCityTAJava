package com.academy.ui.components;


import lombok.AllArgsConstructor;
import org.openqa.selenium.WebElement;

import static com.academy.ui.utils.WebElementUtils.isDisplayed;

@AllArgsConstructor
public class FormField {
    private final WebElement input;
    private final WebElement error;
    private final WebElement dynamicError;

    public void enter(String text) {
        input.sendKeys(text);
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