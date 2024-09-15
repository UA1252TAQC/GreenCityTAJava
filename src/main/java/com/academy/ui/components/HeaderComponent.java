package com.academy.ui.components;

import com.academy.ui.pages.NewsPage;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderComponent extends BaseComponent {

    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'header_sign-in-link')]")
    public WebElement signInButton;
    @FindBy(how = How.XPATH, using = ".//[@class='header_logo']")
    protected WebElement logo;
    @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[1]/a")
    protected WebElement news;
    @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[2]/a")
    protected WebElement places;
    @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[3]/a")
    protected WebElement profile;
    @FindBy(how = How.XPATH, using = ".//ul[@aria-label='language switcher']//li[@aria-label='english']")
    protected WebElement listLanguage;
    @FindBy(how = How.XPATH, using = ".//span[text()='En']")
    protected WebElement english;
    @FindBy(how = How.XPATH, using = ".//li[@class='header_sign-up-link']//span")
    protected WebElement register;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public RegistrationModalComponent openRegistrationForm() {
        click(register);
        return new RegistrationModalComponent(driver, findElement("//app-auth-modal"));
    }

    public void setLanguage(String language) {
        if (language.equals("en")) {
            click(this.listLanguage);
            click(this.english);
        }
    }

    public LoginComponent openLoginForm() {
        click(signInButton);
        return new LoginComponent(driver, findElement(".//app-auth-modal"));
    }

    public NewsPage clickNewsButton() {
        news.click();
        return new NewsPage(driver);
    }

}
