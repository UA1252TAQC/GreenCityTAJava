package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.academy.ui.pages.greenCity.NewsPage;

public class HeaderComponent extends BaseComponent {
    @FindBy(xpath = ".//*[@class='header_logo']")
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

    @FindBy(xpath = "")
    protected WebElement userName;

    @FindBy(xpath = ".//li[contains(@class, 'body-2 user-name') or contains(@class, 'body-2 ubs-user-name')]")
    protected WebElement username;

    @FindBy(xpath = "")
    protected WebElement loginRootElement;

    @FindBy(xpath = "//app-auth-modal")
    protected WebElement registrationRootElement;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public LoginModalComponent clickSignInButtonAndGetLoginForm() {
        return new LoginModalComponent(driver, loginRootElement);
    }

    public RegistrationModalComponent openRegistrationForm() {
        click(register);
        return new RegistrationModalComponent(driver, registrationRootElement);
    }
    
    public NewsPage openNewsPage() {
        click(news);
        return new NewsPage(driver);
    }

    public void setLanguage(String language) {
        if (language.equalsIgnoreCase("En")) {
            click(this.listLanguage);
            click(this.english);
        }
    }

    public String getUsername(){
        return username.getText();
    }

    public String getUserNameText(){
        return "";
    }
}
