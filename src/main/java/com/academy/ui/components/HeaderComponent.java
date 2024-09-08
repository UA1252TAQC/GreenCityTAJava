package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderComponent extends BaseComponent{
    @FindBy(how = How.XPATH, using = "./div[@class='header_logo']")
    protected WebElement logo;

    @FindBy(how = How.XPATH, using = "./div[@class='header_navigation-menu']//li[1]/a")
    protected WebElement news;

    @FindBy(how = How.XPATH, using = "./div[@class='header_navigation-menu']//li[2]/a")
    protected WebElement places;

    @FindBy(how = How.XPATH, using = "./div[@class='header_navigation-menu-ubs']//a[@class='ubs-header-sign-in']")
    protected WebElement profile;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickElement(WebElement element){
        new Actions(driver)
                .moveToElement(element)
                .click()
                .perform();
    }

    public void clickProfile() {
        clickElement(profile);
    }
}
