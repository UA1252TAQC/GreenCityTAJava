package com.academy.ui.components.sub.form;

import com.academy.ui.BaseElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UsernameField extends BaseElement {
    @FindBy(how = How.XPATH, using = ".//input[@id='firstName']")
    private WebElement input;
    @FindBy(how = How.XPATH, using = ".//div[@id='firstname-err-msg']//div")
    private WebElement error;

    public UsernameField(WebDriver driver, WebElement rootElement) {
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
}
