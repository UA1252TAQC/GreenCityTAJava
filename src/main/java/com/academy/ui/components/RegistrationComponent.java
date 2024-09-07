package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import lombok.Getter;

@Getter
public class RegistrationComponent extends BaseComponent {
    @Getter
    @FindBy(how = How.XPATH, using = ".//h1[@class='title-text']")
    private WebElement title;
    @Getter
    @FindBy(how = How.XPATH, using = ".//h2[@class='subtitle-text']")
    private WebElement subtitle;

    @FindBy(how = How.XPATH, using = ".//label[@for='email']")
    private WebElement emailLabel;
    @FindBy(how = How.XPATH, using = ".//input[@id='email']")
    private WebElement emailInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='email-err-msg']//div")
    private WebElement emailError;
    @FindBy(how = How.XPATH, using = ".//div[@class='error-message error-message-show']")
    private WebElement emailSubmitError;

    @FindBy(how = How.XPATH, using = ".//label[@for='firstName']")
    private WebElement usernameLabel;
    @FindBy(how = How.XPATH, using = ".//input[@id='firstName']")
    private WebElement usernameInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='firstname-err-msg']//div")
    private WebElement usernameError;

    @FindBy(how = How.XPATH, using = ".//label[@for='password']")
    private WebElement passwordLabel;
    @FindBy(how = How.XPATH, using = ".//input[@id='password']")
    private WebElement passwordInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='password-err-msg']//div")
    private WebElement passwordError;

    @FindBy(how = How.XPATH, using = ".//label[@for='repeatPassword']")
    private WebElement repeatPasswordLabel;
    @FindBy(how = How.XPATH, using = ".//input[@id='repeatPassword']")
    private WebElement repeatPasswordInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='confirm-err-msg']//div")
    private WebElement repeatPasswordError;

    @FindBy(how = How.XPATH, using = ".//button[@type='submit']")
    private WebElement submitButton;

    public RegistrationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getEmailErrorMessage() {
        if (isDisplayed(emailSubmitError)) {
            return this.emailSubmitError.getText();
        }
        if (isDisplayed(emailError)) {
            return this.emailError.getText();
        }
        return null;
    }

    public String getUsernameErrorMessage() {
        if (isDisplayed(this.usernameError)) {
            return this.usernameError.getText();
        }
        return null;
    }

    public String getPasswordErrorMessage() {
        if (isDisplayed(this.passwordError)) {
            return this.passwordError.getText();
        }
        return null;
    }

    public String getRepeatPasswordErrorMessage() {
        if (isDisplayed(this.repeatPasswordError)) {
            return this.repeatPasswordError.getText();
        }
        return null;
    }

    public RegistrationComponent enterEmail(String email) {
        emailInput.sendKeys(email);
        title.click();
        return this;
    }

    public RegistrationComponent enterUsername(String username) {
        usernameInput.sendKeys(username);
        title.click();
        return this;
    }

    public RegistrationComponent enterPassword(String password) {
        passwordInput.sendKeys(password);
        title.click();
        return this;
    }

    public RegistrationComponent enterRepeatPassword(String repeatPassword) {
        repeatPasswordInput.sendKeys(repeatPassword);
        title.click();
        return this;
    }

    public boolean isEmailValid() {
        return !(isDisplayed(emailError) || isDisplayed(emailSubmitError));
    }

    public boolean isUsernameValid() {
        return !isDisplayed(usernameError);
    }

    public boolean isPasswordValid() {
        return !isDisplayed(passwordError);
    }

    public boolean isRepeatPasswordValid() {
        return !isDisplayed(repeatPasswordError);
    }

    public RegistrationComponent fillForm(String email, String username, String password,
            String repeatPassword) {
        this.enterEmail(email).enterUsername(username).enterPassword(password)
                .enterRepeatPassword(repeatPassword);
        title.click();
        return this;
    }

    public boolean submitIfEnable() {
        if (!isEnableToClickButton(submitButton)) {
                return false;
        }
        click(submitButton);
        return !isDisplayed(submitButton);
    }

    public boolean isDisplayed() {
        return isDisplayed(rootElement);
    }
}
