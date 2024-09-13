package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends BaseComponent {
    @FindBy(xpath = ".//[@class='header_logo']")
    protected WebElement logo;

    @FindBy(xpath = ".//div[@class='header_navigation-menu']//li[1]/a")
    protected WebElement news;

    @FindBy(xpath = ".//div[@class='header_navigation-menu']//li[2]/a")
    protected WebElement places;

    @FindBy(xpath = ".//div[@class='header_navigation-menu']//li[3]/a")
    protected WebElement profile;

    @FindBy(xpath = ".//ul[@aria-label='language switcher']")
    protected WebElement listLanguage;

    @FindBy(xpath = ".//li[@tabindex='0'][@class='lang-option']")
    protected WebElement english;

    @FindBy(xpath = ".//li[@class='header_sign-up-link']//span")
    protected WebElement register;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public RegistrationModalComponent openRegistrationForm() {
        click(register);
        return new RegistrationModalComponent(driver, findElement("//app-auth-modal"));
    }

    public void setLanguage(String language) {
        if (language.equalsIgnoreCase("En")) {
            click(this.listLanguage);
            click(this.english);
        }
    }
}
