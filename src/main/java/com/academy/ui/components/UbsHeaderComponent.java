package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UbsHeaderComponent extends BaseComponent {
    @FindBy(xpath = ".//ul[@class='header_lang-switcher-wrp header_navigation-menu-right-lang ubs-lang-switcher']")
    protected WebElement listLanguage;

    @FindBy(xpath = ".//li[@tabindex='0'][@class='lang-option ng-star-inserted'][@role='menuitem']")
    protected WebElement english;

    @FindBy(xpath = ".//li[@aria-label='Ua']")
    protected WebElement ukrainian;

    @FindBy(xpath = ".//li[@class='header_sign-up-link ng-star-inserted']//span")
    protected WebElement register;

    @FindBy(xpath = "//app-auth-modal")
    protected WebElement authRootElement;

    public UbsHeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public LoginModalComponent getCurrentLoginForm() {
        return new LoginModalComponent(driver, authRootElement);
    }

    public RegistrationModalComponent openRegistrationForm() {
        click(register);
        return new RegistrationModalComponent(driver, authRootElement);
    }

    public void setLanguage(String language) {
        if (language.equalsIgnoreCase("En")) {
            click(listLanguage);
            click(english);
        } else if (language.equalsIgnoreCase("Ua")) {
            click(listLanguage);
            click(ukrainian);
        }
    }
}
