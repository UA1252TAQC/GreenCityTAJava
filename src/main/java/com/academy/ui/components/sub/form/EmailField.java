package com.academy.ui.components.sub.form;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailField extends BaseComponent {
    @FindBy(xpath = ".//input[@id='email']")
    private WebElement input;
    @FindBy(xpath = ".//div[contains(@class, 'error-message') or contains(@class, 'error-message-show')" +
            "or contains(@class, 'alert-general-error') or contains(@class, 'validation-email-error')]")
    private WebElement error;

    public EmailField(WebDriver driver, WebElement rootElement) {
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

    public boolean isHighlightedInRed() {
        String borderColor = input.getCssValue("border-color");
        return borderColor.equals("rgb(240, 49, 39)");
    }

    public boolean isValid() {
        return !isDisplayed(error);
    }

    public void clear() {
        click(input);
        clear(input);
    }
}
