package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import com.academy.ui.components.sub.form.PasswordField;
import com.academy.ui.pages.greenCity.ProfilePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModalComponent extends BaseComponent {
    private static final String FORGOT_PASSWORD_ROOT_ELEMENT = "//div[@class='restore-password-container']";

    @FindBy(xpath = ".//button[@type='submit']")
    protected WebElement signInButton;
    @FindBy(xpath = ".//img[@class='main-picture']")
    protected WebElement mainPicture;
    @FindBy(xpath = ".//a[@class='forgot-password']")
    protected WebElement forgotPasswordLink;

    @Getter
    private final EmailField emailField;
    @Getter
    private final PasswordField passwordField;

    public LoginModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        emailField = new EmailField(driver, rootElement);
        passwordField = new PasswordField(driver, rootElement);
    }

    public String getLoginErrorText() {
        String errorMessageXpath = ".//div[contains(@class, 'alert-general-error')]";
        if (isPresent(errorMessageXpath)) {
            WebElement errorMessage = findWithWaitElement(errorMessageXpath, 5);
            return getText(errorMessage);
        } else {
            return "Element not found: " + errorMessageXpath;
        }
    }

    public LoginModalComponent enterEmail(String email) {
        emailField.enter(email);
        return this;
    }

    public LoginModalComponent enterPassword(String password) {
        passwordField.enter(password);
        return this;
    }

    public LoginModalComponent clickSignInButton() {
        click(signInButton);
        return this;
    }

    public ProfilePage clickSignInButtonSuccessfulLogin() {
        clickSignInButton();
        return new ProfilePage(driver);
    }

    public LoginModalComponent clickSignInButtonUnsuccessfulLogin() {
        clickSignInButton();
        return this;
    }

    public LoginModalComponent clickInsideForm() {
        click(mainPicture);
        clickSignInButton();
        return this;
    }

    public ForgotPasswordModalComponent clickForgotPasswordLink() {
        click(forgotPasswordLink);
        WebElement forgetPasswordRootElement = findWithWaitElement(FORGOT_PASSWORD_ROOT_ELEMENT, 5);
        return new ForgotPasswordModalComponent(driver, forgetPasswordRootElement);
    }

    public LoginModalComponent clearEmail() {
        emailField.clear();
        return this;
    }

    public LoginModalComponent clearPassword() {
        passwordField.clear();
        return this;
    }

    public boolean isSignInButtonActive() {
        return isEnabled(signInButton);

    }

    public String getPasswordErrorMessage() {
        return this.passwordField.getErrorMessage();
    }
    public LoginModalComponent fillForm(String email, String password) {
        enterEmail(email).enterPassword(password);
        return this;
    }

    public boolean isHighlightedSignInBtnGreen() {
        String backgroundColor = signInButton.getCssValue("background-color");
        return backgroundColor.equals("rgba(19, 170, 87, 1)");
    }

}
