package com.academy.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HeaderComponent extends BaseComponent {
    private static final String SIGN_IN_ROOT_XPATH = "//div[@class='wrapper']";
    private static final String LANGUAGE_SWITCHER_XPATH = "//ul[@aria-label='language switcher']";
    private static final String LANGUAGE_OPTION_XPATH = LANGUAGE_SWITCHER_XPATH + "//li/span[text()='%s']";

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

    public SignInComponent getSignInFormModal() {
        clickElementWithAction(profile);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signInRootElement = driver.findElement(By.xpath(SIGN_IN_ROOT_XPATH));
        return new SignInComponent(driver, signInRootElement);
    }

    public void selectLanguage(String language) {
        WebElement languageSwitcher = findElementByXpath(LANGUAGE_SWITCHER_XPATH);
        clickElementWithAction(languageSwitcher);
        WebElement languageOption = findElementByXpath(String.format(LANGUAGE_OPTION_XPATH, language));
        clickElementWithAction(languageOption);
    }

    private WebElement findElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }
}
