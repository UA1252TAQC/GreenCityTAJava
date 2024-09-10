package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(how = How.CSS, using = ".header_sign-up-link")
    protected WebElement signInComponentRoot;

    private SignInComponent signInComponent;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver,this);
    }

    public SignInComponent getSignInComponent() {
        return new SignInComponent(driver, signInComponentRoot);
    }

    public void clickSignInLink() {
        signInLink.click();
    }

    public void clickNewsTab() {
        news.click();
    }

    public String getProfileButtonText() {
        return profileLink.getText();
    }
}
