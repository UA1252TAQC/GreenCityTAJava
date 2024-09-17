package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import com.academy.ui.components.sub.form.PasswordField;
import com.academy.ui.pages.greenCity.ProfilePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModalComponent extends BaseComponent {
    @FindBy(xpath = "")
    protected WebElement signInButton;
    @FindBy(xpath = ".//img[@class='main-picture']")
    protected WebElement mainPicture;

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
}
