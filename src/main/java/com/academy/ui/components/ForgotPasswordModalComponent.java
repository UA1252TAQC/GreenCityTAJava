package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.css.RGBColor;

public class ForgotPasswordModalComponent extends BaseComponent {
    private static final String FORGOT_PASSWORD_WINDOW_XPATH = ".//div[@class='restore-password-container']";
    private static final String BACK_TO_SIGN_IN_LINK_XPATH = ".//div[@class='mentioned-password']//a[@class='green-link']";

    @FindBy(xpath = ".//button[@type='submit']")
    protected WebElement signInButton;

    @FindBy(xpath = BACK_TO_SIGN_IN_LINK_XPATH)
    protected WebElement backToSignInLink;

    @FindBy(xpath = "//app-auth-modal")
    protected WebElement loginRootElement;

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

    public boolean isForgotPasswordLinkDisplayed() {
        return isDisplayed(findWithWaitElement(BACK_TO_SIGN_IN_LINK_XPATH, EXPLICITLY_WAIT_DURATION_FIVE_SECONDS));
    }

    public LoginModalComponent clickBackToSignInLink() {
        click(backToSignInLink);
        waitTillElementIsInvisible(backToSignInLink);
        return new LoginModalComponent(driver, loginRootElement);
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
