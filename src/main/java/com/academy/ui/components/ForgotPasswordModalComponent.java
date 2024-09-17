package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordModalComponent extends BaseComponent {
    private static final String FORGOT_PASSWORD_WINDOW_XPATH = ".//div[@class='restore-password-container']";

    public ForgotPasswordModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean isForgotPasswordWindowDisplayed() {
        return isDisplayed(findWithWaitElement(FORGOT_PASSWORD_WINDOW_XPATH, 5));
    }
}
