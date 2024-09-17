package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import com.academy.ui.components.sub.form.PasswordField;
import com.academy.ui.pages.greenCity.ProfilePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModalComponent extends BaseComponent {

    @FindBy(xpath = ".//button[@type='submit']")
    protected WebElement signInButton;

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
        String errorMessageXpath = ".//div[contains(@class, 'alert-general-error')]";
        if (isPresent(errorMessageXpath))
        {
            WebElement errorMessage = findWithWaitElement(errorMessageXpath);
            return getText(errorMessage);
        }
        else{
            return "Element not found: " + errorMessageXpath;
        }
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
        clickSignInButton();
        return this;
    }


}
