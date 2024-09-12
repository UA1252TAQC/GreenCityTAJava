package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import com.academy.ui.components.sub.form.PasswordField;
import com.academy.ui.pages.HomePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginComponent extends BaseComponent {
    private EmailField emailField;

    private PasswordField passwordField;

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

    public HomePage fillForm(String email, String password) {
        this.enterEmail(email)
                .enterPassword(password)
                .clickSubmit();

        return new HomePage(driver);
    }

    public LoginComponent clickSubmit() {
        click(submitButton);
        return this;
    }
}
