package com.academy.ui.components;

import com.academy.ui.pages.NewsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(how = How.XPATH, using = "//ul[@aria-label='language switcher']//li[@aria-label='english']")
    protected WebElement listLanguage;

    @FindBy(how = How.XPATH, using = "//span[text()='En']")
    protected WebElement english;

    @FindBy(how = How.XPATH, using = ".//li[@class='header_sign-up-link']//span")
    protected WebElement register;

    @FindBy(xpath = "//a[@role='link']")
    public WebElement signInButton;

//    @FindBy(how = How.XPATH, using = "//nav[contains(@class, 'header_navigation-menu-left')]//li[1]/a")
//    protected WebElement newsLogin;

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

    public LoginComponent openLoginForm() {
        click(signInButton);
        return new LoginComponent(driver, findElement(".//app-auth-modal"));
    }

    public NewsPage switchToNews() {
        click(news);
        return new NewsPage(driver);
    }
}
