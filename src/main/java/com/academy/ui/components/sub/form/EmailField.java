package com.academy.ui.components.sub.form;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EmailField extends BaseComponent {
    @FindBy(how = How.XPATH, using = ".//input[@id='email']")
    protected WebElement input;
    @FindBy(how = How.XPATH, using = ".//div[@id='email-err-msg']//div")
    protected WebElement error;
    @FindBy(how = How.XPATH, using = ".//div[@class='error-message error-message-show']")
    protected WebElement dynamicError;
    @FindBy(how = How.XPATH, using = ".//div[@class='error-message error-message-show ng-star-inserted']")
    protected WebElement dynamicErrorUbs;

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
        if (isDisplayed(dynamicError)) {
            return dynamicError.getText();
        }
        if (isDisplayed(error)) {
            return error.getText();
        }
        if (isDisplayed(dynamicErrorUbs)) {
            return dynamicErrorUbs.getText();
        }
        return null;
    }

    public boolean isValid() {
        return !((isDisplayed(error)) || (isDisplayed(dynamicError)));
    }

    public void clear() {
        click(input);
        clear(input);
    }
}
