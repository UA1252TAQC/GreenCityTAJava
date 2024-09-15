package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import com.academy.ui.components.sub.form.PasswordField;
import com.academy.ui.pages.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginModalComponent extends BaseComponent{

    @FindBy(how = How.XPATH, using = ".//button[@class='greenStyle']")
    protected WebElement signInButton;

    private EmailField emailField;
    private PasswordField passwordField;

    public LoginModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.emailField = new EmailField(driver, rootElement);
        this.passwordField = new PasswordField(driver, rootElement);
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
        clickSignInButton();
        return new ProfilePage(driver);
    }

    public LoginModalComponent clickSignInButtonUnsuccessfulLogin() {
        clickSignInButton();
        return this;
    }
    public Boolean containsMessage(String message){
        String xpath = "//*[contains(text(),'" + message + "')]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath));
        return Boolean.TRUE;
    }

}
