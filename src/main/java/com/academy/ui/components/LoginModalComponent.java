package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import com.academy.ui.components.sub.form.PasswordField;
import com.academy.ui.pages.BasePage;
import com.academy.ui.pages.greenCity.ProfilePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModalComponent extends BaseComponent {
    private static final String SIGN_IN_BUTTON_XPATH = ".//button[@type='submit']";
    private static final String FORGOT_PASSWORD_LINK_XPATH = ".//a[@class='forgot-password']";
    private static final String FORGOT_PASSWORD_ROOT_ELEMENT = "//div[@class='restore-password-container']";

    @FindBy(xpath = SIGN_IN_BUTTON_XPATH)
    protected WebElement signInButton;
    @FindBy(xpath = ".//img[@class='main-picture']")
    protected WebElement mainPicture;
    @FindBy(xpath = FORGOT_PASSWORD_LINK_XPATH)
    protected WebElement forgotPasswordLink;
    @FindBy(xpath = "//*[@id=\"pass-err-msg\"]/app-error/div")
    private WebElement errorMessageElement;
    @FindBy(xpath = "//*[@id=\"mat-dialog-0\"]/app-auth-modal/div/div/div[2]/div/app-sign-in/form/div[3]\n")
    private WebElement errorMessageElementUnregistered;
    @FindBy(xpath = ".//button[@class='google-sign-in']")
    private WebElement signInWithGoogleBtn;

    @Getter
    @FindBy(xpath = "./div[@class='main']")
    private WebElement mainWindow;

    @Getter
    private final EmailField emailField;
    @Getter
    private final PasswordField passwordField;

    public LoginModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        emailField = new EmailField(driver, rootElement);
        passwordField = new PasswordField(driver, rootElement);
    }

    @Step("Get error")
    public String getLoginErrorText() {
        String errorMessageXpath = ".//div[contains(@class, 'alert-general-error')]";
        if (isPresent(errorMessageXpath)) {
            WebElement errorMessage = findWithWaitElement(errorMessageXpath, 5);
            return getText(errorMessage);
        } else {
            return "Element not found: " + errorMessageXpath;
        }
    }

    @Step("Enter email {email}")
    public LoginModalComponent enterEmail(String email) {
        emailField.enter(email);
        return this;
    }

    @Step("Enter password")
    public LoginModalComponent enterPassword(String password) {
        passwordField.enter(password);
        return this;
    }

    @Step("Click Sign-in button")
    public LoginModalComponent clickSignInButton() {
        click(signInButton);
        return this;
    }

    @Step("Open profile page if login was successful")
    public ProfilePage clickSignInButtonSuccessfulLogin() {
        clickSignInButton();
        waitTillElementIsInvisible(signInButton);
        return new ProfilePage(driver);
    }

    @Step("Click Sign-in button with invalid data")
    public LoginModalComponent clickSignInButtonUnsuccessfulLogin() {
        clickSignInButton();
        return this;
    }

    @Step("Click somewhere inside the form")
    public LoginModalComponent clickInsideForm() {
        click(mainPicture);
        clickSignInButton();
        return this;
    }

    @Step("Check if \"Forgot Password\" displayed")
    public boolean isForgotPasswordLinkDisplayed() {
        return isDisplayed(findWithWaitElement(FORGOT_PASSWORD_LINK_XPATH, EXPLICITLY_WAIT_DURATION_FIVE_SECONDS));
    }

    @Step("Click \"Forgot Password\" link")
    public ForgotPasswordModalComponent clickForgotPasswordLink() {
        click(forgotPasswordLink);
        WebElement forgetPasswordRootElement = findWithWaitElement(FORGOT_PASSWORD_ROOT_ELEMENT, 5);
        return new ForgotPasswordModalComponent(driver, forgetPasswordRootElement);
    }

    @Step("Click \"Sign-in\" with Google")
    public GoogleAuthComponent clickSignInWithGoogleBtn() {
        click(signInWithGoogleBtn);
        new BasePage(driver).switchToActiveTab();
        return new GoogleAuthComponent(driver);
    }

    @Step("Clear email field")
    public LoginModalComponent clearEmail() {
        emailField.clear();
        return this;
    }

    @Step("Clear password field")
    public LoginModalComponent clearPassword() {
        passwordField.clear();
        return this;
    }

    @Step("Check if Sign-in button is active")
    public boolean isSignInButtonActive() {
        return isEnabled(signInButton);

    }

    @Step("Get error message from password field")
    public String getPasswordErrorMessage() {
        return this.passwordField.getErrorMessage();
    }

    @Step("Fill the Sign-in form")
    public LoginModalComponent fillForm(String email, String password) {
        enterEmail(email).enterPassword(password);
        return this;
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessageElement.isDisplayed();
    }

    public boolean isErrorMessageDisplayedUnregistered() {
        return errorMessageElementUnregistered.isDisplayed();
    }

    public String getErrorMessageText() {
        return errorMessageElement.getText();
    }

    public String getErrorMessageTextUnregistered() {
        return errorMessageElementUnregistered.getText();
    }

    @Step("Check if Sign-In Button is highlighted in {color}")
    public boolean isHighlightedSignInBtnInColor(String color) {
        String backgroundColor = signInButton.getCssValue("background-color");
        return backgroundColor.equals(color);
    }

    public int getWidth() {
        return mainWindow.getSize().width;
    }
}
