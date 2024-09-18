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
        this.email.enter(text);
        return this;
    }

    public RegistrationModalComponent enterUsername(String text) {
        this.username.enter(text);
        return this;
    }

    public RegistrationModalComponent enterPassword(String text) {
        this.password.enter(text);
        return this;
    }

    public RegistrationModalComponent enterRepeatPassword(String text) {
        this.repeatPassword.enter(text);
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
        return this.enterEmail(email).enterUsername(username).enterPassword(password)
                .enterRepeatPassword(repeatPassword).clickTitle();
    }

    public RegistrationModalComponent clickTitle() {
        click(title);
        return this;
    }

    public boolean isRegistrationButtonDisplayed() {
        return isDisplayed(this.registerButton);
    }

    public boolean isGoogleButtonDisplayed() {
        return isDisplayed(this.googleButton);
    }

    public void submit() {
        click(this.registerButton);
    }

    public boolean isRegistrationButtonEnabled() {
        return isEnabled(this.registerButton);
    }

    private static final String[] VALID_DATA =
            new String[]{"mail@gmail.com", "Denys1", "Password1!", "Password1!"};

    public void fillFormWithTestDataAndSubmitIf(boolean isShouldSubmitForm, String email, String username, String password, String repeatPassword) {
        if (isShouldSubmitForm) { // TODO move to data-provider & refactor
            this.fillForm(email != null ? email : VALID_DATA[0],
                    username != null ? username : VALID_DATA[1],
                    password != null ? password : VALID_DATA[2],
                    repeatPassword != null ? repeatPassword : VALID_DATA[3]).submit();
            return;
        }

        if (email != null) {
            this.enterEmail(email).clickTitle();
        }
        if (username != null) {
            this.enterUsername(username).clickTitle();
        }
        if (password != null) {
            this.enterPassword(password).clickTitle();
        }
        if (repeatPassword != null) {
            this.enterRepeatPassword(repeatPassword).clickTitle();
        }
    }

    public boolean isSignInLinkDisplayed() {
        return isDisplayed(this.signInLink);
    }

    public void close() {
        click(this.closeButton);
        sleep(1);
    }

    public RegistrationModalComponent clearPasswordFieldIf(boolean isShouldClearPassword) {
        if (isShouldClearPassword) {
            password.clear();
        }
        return this;
    }
}
