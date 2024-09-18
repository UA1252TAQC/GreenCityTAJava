package com.academy.ui.components;

import com.academy.ui.pages.greenCity.NewsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends BaseComponent {

    final String NAME_PROFILE = "//p[@class='name']";

    @FindBy(xpath = ".//*[@class='header_logo']")
    protected WebElement logo;

    @FindBy(xpath = ".//div[@class='header_navigation-menu']//li[1]/a")
    protected WebElement news;

    @FindBy(xpath = ".//div[@class='header_navigation-menu']//li[2]/a")
    protected WebElement places;

    @FindBy(xpath = ".//div[@class='header_navigation-menu']//li[3]/a")
    protected WebElement profile;

    @FindBy(xpath = ".//ul[@aria-label='language switcher']//li[@aria-label='english']")
    protected WebElement listLanguage;

    @FindBy(xpath = ".//li[@aria-label='En']")
    protected WebElement english;

    @FindBy(xpath = ".//a[contains(@class, 'header_sign-in-link')]")
    protected WebElement login;

    @FindBy(xpath = ".//li[@class='header_sign-up-link']//span")
    protected WebElement register;

    @FindBy(xpath = ".//ul[@id='header_user-wrp']/li[contains(@class, 'user-name')]")
    protected WebElement userName;

    @FindBy(xpath = "//app-auth-modal")
    protected WebElement loginRootElement;

    @FindBy(xpath = "//app-auth-modal")
    protected WebElement registrationRootElement;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public LoginModalComponent openLoginForm() {
        click(login);
        return new LoginModalComponent(driver, loginRootElement);
    }

    public RegistrationModalComponent openRegistrationForm() {
        click(register);
        return new RegistrationModalComponent(driver, registrationRootElement);
    }

    public NewsPage clickNewsLInk() {
        findWithWaitElement(NAME_PROFILE, 10);
        click(news);
        return new NewsPage(driver);
    }

    public void setLanguage(String language) {
        if (language.equalsIgnoreCase("En")) {
            click(listLanguage);
            click(english);
        }
    }

    public String getUserNameText() {
        sleep(1);
        return getText(userName);
    }
}
