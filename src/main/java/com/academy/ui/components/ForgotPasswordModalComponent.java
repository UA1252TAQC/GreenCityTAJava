package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.css.RGBColor;

public class ForgotPasswordModalComponent extends BaseComponent {
    private static final String FORGOT_PASSWORD_WINDOW_XPATH = ".//div[@class='restore-password-container']";

    @FindBy(xpath = ".//button[@type='submit']")
    protected WebElement signInButton;

    @Getter
    private final EmailField emailField;

    public ForgotPasswordModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        emailField = new EmailField(driver, rootElement);
    }

    public ForgotPasswordModalComponent enterEmail(String email) {
        this.emailField.enter(email);
        return this;
    }

    public ForgotPasswordModalComponent clickSignInButton() {
        click(signInButton);
        return this;
    }

    public boolean isForgotPasswordWindowDisplayed() {
        return isDisplayed(findWithWaitElement(FORGOT_PASSWORD_WINDOW_XPATH, 5));
    }

    public String getErrorFieldMessage(){
        return isForgotPasswordWindowDisplayed()?emailField.getErrorMessage():"";
    }
    public boolean isHighlightedInColor(String color){
        return emailField.isHighlightedInColor(color);
    }
}
