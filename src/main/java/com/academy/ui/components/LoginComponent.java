package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import com.academy.ui.components.sub.form.PasswordField;
import com.academy.ui.pages.ProfilePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginComponent extends BaseComponent {
    private final EmailField emailField;

    private final PasswordField passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    @Getter
    private WebElement submitButton;

    public LoginComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);

        emailField = new EmailField(driver, rootElement);
        passwordField = new PasswordField(driver, rootElement);
    }

    public LoginComponent enterEmail(String email) {
        emailField.enter(email);
        return this;
    }

    public LoginComponent enterPassword(String password) {
        passwordField.enter(password);
        return this;
    }

    public LoginComponent clickSignInUnSuccess() {
        submitButton.click();
        return this;
    }

    public ProfilePage clickSignInSuccess() {
        submitButton.click();
        return new ProfilePage(driver);
    }
}
