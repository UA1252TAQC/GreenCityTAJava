package com.academy.ui.components;

import com.academy.ui.components.sub.FormField;
import com.academy.ui.utils.WebElementUtils;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationComponent extends BaseComponent {
    @FindBy(how = How.XPATH, using = ".//h1[@class='title-text']")
    private WebElement title;

    @FindBy(how = How.XPATH, using = ".//input[@id='email']")
    private WebElement emailInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='email-err-msg']//div")
    private WebElement emailError;
    @FindBy(how = How.XPATH, using = ".//div[@class='error-message error-message-show']")
    private WebElement emailDynamicError;

    @FindBy(how = How.XPATH, using = ".//input[@id='firstName']")
    private WebElement usernameInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='firstname-err-msg']//div")
    private WebElement usernameError;

    @FindBy(how = How.XPATH, using = ".//input[@id='password']")
    private WebElement passwordInput;
    @FindBy(how = How.XPATH, using = ".//p[@class='password-not-valid']")
    private WebElement passwordError;

    @FindBy(how = How.XPATH, using = ".//input[@id='repeatPassword']")
    private WebElement repeatPasswordInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='confirm-err-msg']//div")
    private WebElement repeatPasswordError;

    @FindBy(how = How.XPATH, using = ".//button[@type='submit']")
    private WebElement submitButton;
    @FindBy(how = How.XPATH, using = ".//button[@class='google-sign-in']")
    private WebElement googleButton;
    @FindBy(how = How.XPATH, using = ".//a[@aria-label='sign in modal window'][@class='green-link']")
    private WebElement signInLink;

    @FindBy(how = How.XPATH, using = ".//img[@class='cross-btn'][@alt='close button']")
    private WebElement closeButton;

    public RegistrationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Getter
    private final FormField email = new FormField(emailInput, emailError, emailDynamicError);
    @Getter
    private final FormField username = new FormField(usernameInput, usernameError, null);
    @Getter
    private final FormField password = new FormField(passwordInput, passwordError, null);
    @Getter
    private final FormField repeatPassword = new FormField(repeatPasswordInput, repeatPasswordError, null);

    public RegistrationComponent enterEmail(String text) {
        this.email.enter(text);
        return this;
    }

    public RegistrationComponent enterUsername(String text) {
        this.username.enter(text);
        return this;
    }

    public RegistrationComponent enterPassword(String text) {
        this.password.enter(text);
        return this;
    }

    public RegistrationComponent enterRepeatPassword(String text) {
        this.repeatPassword.enter(text);
        return this;
    }

    public RegistrationComponent fillForm(String email, String username, String password, String repeatPassword) {
        return this.enterEmail(email)
                .enterUsername(username)
                .enterPassword(password)
                .enterRepeatPassword(repeatPassword)
                .click();
    }

    public RegistrationComponent click() {
        WebElementUtils.click(title);
        return this;
    }

    public boolean isSubmitButtonDisplayed() {
        return WebElementUtils.isDisplayed(this.submitButton);
    }

    public boolean isGoogleButtonDisplayed() {
        return WebElementUtils.isDisplayed(this.googleButton);
    }

    public boolean submit() {
        WebElementUtils.click(this.submitButton);
        return !WebElementUtils.isDisplayed(this.submitButton);
    }

    public boolean isDisplayed() {
        return WebElementUtils.isDisplayed(this.rootElement);
    }

    public boolean isValid() {
        return WebElementUtils.isEnabled(this.submitButton);
    }

    private static final String[] VALID_DATA = new String[] {"mail@gmail.com", "Denys1", "Password1!", "Password1!"};

    public void fillFormWithTestDataAndSubmitIf(boolean isShouldSubmitForm, String email, String username, String password, String repeatPassword) {
        if (isShouldSubmitForm) { //TODO move to dataprovider & refactor
            this.fillForm(email != null ? email : VALID_DATA[0],
                    username != null ? username : VALID_DATA[1],
                    password != null ? password : VALID_DATA[2],
                    repeatPassword != null ? repeatPassword : VALID_DATA[3]).submit();
            return;
        }

        if (email != null) {
            this.enterEmail(email).click();
        }
        if (username != null) {
            this.enterUsername(username).click();
        }
        if (password != null) {
            this.enterPassword(password).click();
        }
        if (repeatPassword != null) {
            this.enterRepeatPassword(repeatPassword).click();
        }
    }

    public boolean isSignInLinkDisplayed() {
        return WebElementUtils.isDisplayed(this.signInLink);
    }

    public void close() {
        WebElementUtils.click(this.closeButton);
        sleep(1);
    }
}
