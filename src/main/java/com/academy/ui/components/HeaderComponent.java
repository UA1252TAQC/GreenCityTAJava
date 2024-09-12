package com.academy.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderComponent extends BaseComponent{

    @FindBy(how = How.XPATH, using = ".//[@class='header_logo']")
    protected WebElement logo;

    @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[1]/a")
    protected WebElement news;

    @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[2]/a")
    protected WebElement places;

    @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[3]/a")
    protected WebElement profile;

    @FindBy(how = How.CSS, using = "a.header_sign-in-link")
    protected WebElement signInLink;

    @FindBy(how = How.CSS, using = "li.user-name")
    protected WebElement profileLink;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public SignInComponent clickSignInLink() {
        signInLink.click();
        return new SignInComponent(driver, driver.findElement(By.cssSelector(".wrapper")));
    }

    public void clickNewsTab() {
        news.click();
    }

    public String getProfileButtonText() {
        return profileLink.getText();
    }
}
