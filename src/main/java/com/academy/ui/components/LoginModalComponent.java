package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import com.academy.ui.components.sub.form.PasswordField;
import com.academy.ui.pages.greenCity.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModalComponent extends BaseComponent {

    @FindBy(xpath = ".//button[@class='greenStyle']")
    protected WebElement signInButton;

    private EmailField emailField;
    private PasswordField passwordField;

    public LoginModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.emailField = new EmailField(driver, rootElement);
        this.passwordField = new PasswordField(driver, rootElement);
    }

    public String getLoginErrorText() {
        return "";
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


}
