package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderComponent extends BaseComponent {

    @FindBy(how = How.XPATH, using = ".//[@class='header_logo']")
    protected WebElement logo;

    @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[1]/a")
    protected WebElement news;

    @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[2]/a")
    protected WebElement places;

    @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[3]/a")
    protected WebElement profile;

    @FindBy(how = How.XPATH, using = ".//ul[@class='header_lang-switcher-wrp header_navigation-menu-right-lang']")
    protected WebElement listLanguage;

    @FindBy(how = How.XPATH, using = "//li[@role='menuitem' and @aria-label='En']/span[text()='En']")
    protected WebElement english;

    @FindBy(how = How.XPATH, using = ".//li[@class='header_sign-up-link']//span")
    protected WebElement register;
    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'header_sign-in-link')]")
    protected WebElement signInHomeButton;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public RegistrationComponent openRegistrationForm() {
        click(register);
        return new RegistrationComponent(driver, findElement(".//app-auth-modal"));
    }

    public SignInComponent openSignInForm() {
        click(signInHomeButton);
        return new SignInComponent(driver, findElement(".//app-auth-modal[@class='ng-star-inserted']"));
    }

    public HeaderComponent changeLanguage(String language) {
        if (language.equals("en")) {
            click(this.listLanguage);
            click(this.english);
        }
        return this;
    }

}
