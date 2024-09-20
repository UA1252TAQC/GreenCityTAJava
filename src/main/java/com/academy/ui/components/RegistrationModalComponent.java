package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import com.academy.ui.components.sub.form.PasswordField;
import com.academy.ui.components.sub.form.RepeatPasswordField;
import com.academy.ui.components.sub.form.UsernameField;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class RegistrationModalComponent extends BaseComponent {
    @FindBy(xpath = ".//h1[@class='title-text']")
    private WebElement title;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement registerButton;

    @FindBy(xpath = ".//button[@class='google-sign-in']")
    private WebElement googleButton;

    @FindBy(xpath = ".//a[@aria-label='sign in modal window'][@class='green-link']")
    private WebElement signInLink;

    @FindBy(xpath = ".//img[@class='cross-btn'][@alt='close button']")
    private WebElement closeButton;

    @Getter
    private final EmailField email;
    @Getter
    private final UsernameField username;
    @Getter
    private final PasswordField password;
    @Getter
    private final RepeatPasswordField repeatPassword;

    public RegistrationModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        email = new EmailField(driver, rootElement);
        username = new UsernameField(driver, rootElement);
        password = new PasswordField(driver, rootElement);
        repeatPassword = new RepeatPasswordField(driver, rootElement);
    }

    public RegistrationModalComponent enterEmail(String text) {
        email.enter(text);
        return this;
    }

    public RegistrationModalComponent enterUsername(String text) {
        username.enter(text);
        return this;
    }

    public RegistrationModalComponent enterPassword(String text) {
        password.enter(text);
        return this;
    }

    public RegistrationModalComponent enterRepeatPassword(String text) {
        repeatPassword.enter(text);
        return this;
    }

    public GoogleAuthComponent openAuthGoogleForm() {
        click(googleButton);
        sleep(1);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.getLast());
        return new GoogleAuthComponent(driver);
    }

    public RegistrationModalComponent fillForm(String email, String username, String password, String repeatPassword) {
        return enterEmail(email).enterUsername(username).enterPassword(password)
                .enterRepeatPassword(repeatPassword).clickTitle();
    }

    public RegistrationModalComponent clickTitle() {
        click(title);
        return this;
    }

    public boolean isRegistrationButtonDisplayed() {
        return isDisplayed(registerButton);
    }

    public boolean isGoogleButtonDisplayed() {
        return isDisplayed(googleButton);
    }

    public void submit() {
        click(registerButton);
    }

    public boolean isRegistrationButtonEnabled() {
        return isEnabled(registerButton);
    }

    public void submitIf(boolean isShouldSubmitForm) {
        if (isShouldSubmitForm) {
            submit();
        }
    }

    public boolean isSignInLinkDisplayed() {
        return isDisplayed(signInLink);
    }

    public void close() {
        click(closeButton);
        sleep(1);
    }

    public RegistrationModalComponent clearPasswordFieldIf(boolean isShouldClearPassword) {
        if (isShouldClearPassword) {
            password.clear();
        }
        return this;
    }
}
