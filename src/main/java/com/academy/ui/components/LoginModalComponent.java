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
    private EmailField emailField;
    @Getter
    private PasswordField passwordField;

    public LoginModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        emailField = new EmailField(driver, rootElement);
        passwordField = new PasswordField(driver, rootElement);
    }

    public String getLoginErrorText() {
        return "";
    }

    public LoginModalComponent enterEmail(String email) {
        this.emailField.enter(email);
        return this;
    }

    public LoginModalComponent enterPassword(String password) {
        this.passwordField.enter(password);
        return this;
    }

    public LoginModalComponent clickSignInButton() {
        click(signInButton);
        return this;
    }

    public ProfilePage clickSignInButtonSuccessfulLogin() {
        return new ProfilePage(driver);
    }

    public LoginModalComponent clickSignInButtonUnsuccessfulLogin() {
        return this;
    }

    public LoginModalComponent clickInsideForm() {
        click(mainPicture);
        return this;
    }

    public ForgotPasswordModalComponent clickForgotPasswordLink(){
        click(forgotPasswordLink);
        WebElement forgetPasswordRootElement = findWithWaitElement(FORGOT_PASSWORD_ROOT_ELEMENT);
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
}
