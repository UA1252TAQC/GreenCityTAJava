package com.academy.ui.components;

import com.academy.ui.user.SignInModal;
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

    private SignInModal signInComponent;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver,this);
    }

    private SignInModal getSignInComponent() {
        return new SignInModal(driver);
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
