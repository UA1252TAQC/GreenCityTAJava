package com.academy.ui.components.sub.form;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RepeatPasswordField extends BaseComponent {
    @FindBy(xpath = ".//input[@id='repeatPassword']")
    private WebElement input;
    @FindBy(xpath = ".//div[@id='confirm-err-msg']//div")
    private WebElement error;

    public RepeatPasswordField(WebDriver driver, WebElement rootElement) {
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
}