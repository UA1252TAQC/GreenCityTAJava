package com.academy.ui.components.sub;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordModalComponent extends BaseComponent {
    private static final String FORGOT_PASSWORD_WINDOW_XPATH = ".//div[@class='restore-password-container']";

    public ForgotPasswordModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean isForgotPasswordWindowDisplayed() {
        return isDisplayed(findWithWaitElement(FORGOT_PASSWORD_WINDOW_XPATH, 5));
    }
}
