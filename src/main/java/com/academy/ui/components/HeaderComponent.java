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

    @FindBy(how = How.XPATH, using = ".//li[@tabindex='0'][@class='lang-option'][@role='menuitem']")
    protected WebElement english;

    @FindBy(how = How.XPATH, using = ".//li[@class='header_sign-up-link']//span")
    protected WebElement register;

    @FindBy(how = How.CSS, using = "a.header_sign-in-link")
    protected WebElement signInLink;

    @FindBy(how = How.CSS, using = "li.user-name")
    protected WebElement profileLink;

    @FindBy(how = How.XPATH, using = ".//app-auth-modal")
    protected WebElement signInComponentRoot;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public RegistrationComponent openRegistrationForm() {
        click(register);
        return new RegistrationComponent(driver, findElement(".//app-auth-modal"));
    }

    public HeaderComponent changeLanguage(String language) {
        if (language.equals("en")) {
            click(this.listLanguage);
            click(this.english);
        }
        return this;
    }


    public SignInComponent clickSignInLink() {
        signInLink.click();
        return new SignInComponent(driver, signInComponentRoot);
    }

    public void clickNewsTab() {
        news.click();
    }

    public String getProfileButtonText() {
        return profileLink.getText();
    }
}
