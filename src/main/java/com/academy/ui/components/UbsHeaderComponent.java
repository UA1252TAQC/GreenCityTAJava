package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UbsHeaderComponent extends BaseComponent {
    @FindBy(xpath = ".//ul[@aria-label='language switcher']")
    protected WebElement currentLanguage;

    @FindBy(xpath = ".//ul[@class='header_lang-switcher-wrp header_navigation-menu-right-lang ubs-lang-switcher']")
    protected WebElement listLanguage;

    @FindBy(xpath = "//li[@aria-label='EN']")
    protected WebElement english;

    @FindBy(xpath = "//li[@aria-label='UA']")
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
        String cLanguage = currentLanguage.getText();
        if (cLanguage.equalsIgnoreCase(language)) {
            return;
        }
        if (language.equalsIgnoreCase("En")) {
            click(listLanguage);
            sleep(1);
            click(english);
        } else if (language.equalsIgnoreCase("Ua")) {
            click(listLanguage);
            sleep(1);
            click(ukrainian);
        }
    }
}
