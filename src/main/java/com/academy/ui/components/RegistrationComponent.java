package com.academy.ui.components;

import com.academy.ui.utils.WebElementUtils;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationComponent extends BaseComponent {
    @FindBy(how = How.XPATH, using = ".//h1[@class='title-text']")
    private WebElement title;
    @FindBy(how = How.XPATH, using = ".//h2[@class='subtitle-text']")
    private WebElement subtitle;

    @FindBy(how = How.XPATH, using = ".//label[@for='email']")
    private WebElement emailLabel;
    @FindBy(how = How.XPATH, using = ".//input[@id='email']")
    private WebElement emailInput;
    @FindBy(how = How.XPATH, using = ".//div[@id='email-err-msg']//div")
    private WebElement emailError;
    @FindBy(how = How.XPATH, using = ".//div[@class='error-message error-message-show']")
    private WebElement emailDynamicError;

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

    @Getter(lazy = true)
    private final FormField email = new FormField(emailInput, emailError, emailDynamicError);
    @Getter(lazy = true)
    private final FormField username = new FormField(usernameInput, usernameError, null);
    @Getter(lazy = true)
    private final FormField password = new FormField(passwordInput, passwordError, null);
    @Getter(lazy = true)
    private final FormField repeatPassword = new FormField(repeatPasswordInput, repeatPasswordError, null);


    public RegistrationComponent fillForm(String email, String username, String password, String repeatPassword) {
        this.email.enter(email);
        this.username.enter(username);
        this.password.enter(password);
        this.repeatPassword.enter(repeatPassword);
        title.click();
        return this;
    }

    public boolean submitIfEnable() {
        if (!WebElementUtils.isEnabled(submitButton)) {
                return false;
        }
        WebElementUtils.click(submitButton);
        return !WebElementUtils.isDisplayed(submitButton);
    }

    public boolean isDisplayed() {
        return WebElementUtils.isDisplayed(rootElement);
    }
}
