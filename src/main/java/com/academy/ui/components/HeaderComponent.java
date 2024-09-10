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
    private static final String HEADER_LOGO_XPATH = "./div[@class='header_logo']";
    private static final String NEWS_XPATH = "./div[@class='header_navigation-menu']//li[1]/a";
    private static final String PLACES_XPATH = "./div[@class='header_navigation-menu']//li[2]/a";
    private static final String PROFILE_XPATH = "./div[@class='header_navigation-menu-ubs']//a[@class='ubs-header-sign-in']";
    private static final String SIGN_IN_WRAPPER_XPATH = "//div[@class='wrapper']";
    private static final String LANGUAGE_SWITCHER_XPATH = "//ul[@aria-label='language switcher']";
    private static final String LANGUAGE_OPTION_XPATH = LANGUAGE_SWITCHER_XPATH + "//li/span";
    private static final String PROFILE_PAGE_XPATH = "//div[@class='profile-page']"; // Example XPath for profile page

    @FindBy(how = How.XPATH, using = HEADER_LOGO_XPATH)
    protected WebElement logo;

    @FindBy(how = How.XPATH, using = NEWS_XPATH)
    protected WebElement news;

    @FindBy(how = How.XPATH, using = PLACES_XPATH)
    protected WebElement places;

    @FindBy(how = How.XPATH, using = PROFILE_XPATH)
    protected WebElement profile;

    private SignInComponent signInComponent;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    // Opens the profile or sign-in form depending on the user's login state
    public SignInComponent openProfileOrSignInForm() {
        clickElement(profile);

        // Check if the sign-in form or profile page is displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if (isElementDisplayed(SIGN_IN_WRAPPER_XPATH)) {
            // User is not logged in, initialize the SignInComponent
            WebElement signInRootElement = driver.findElement(By.xpath(SIGN_IN_WRAPPER_XPATH));
            signInComponent = new SignInComponent(driver, signInRootElement);
            return signInComponent;
        } else if (isElementDisplayed(PROFILE_PAGE_XPATH)) {
            // User is logged in, you can add logic here to handle the profile page
            // For example, you can initialize or interact with the profile page component
        }
        return null;
    }

    // Selects a language from the language switcher
    public void selectLanguage(String languageLabel) {
        WebElement languageSwitcher = findElementByXpath(LANGUAGE_SWITCHER_XPATH);
        clickElement(languageSwitcher);

        List<WebElement> languageOptions = getLanguageOptions();
        for (WebElement option : languageOptions) {
            if (option.getText().equalsIgnoreCase(languageLabel)) {
                clickElement(option);
                return;
            }
        }
        throw new IllegalArgumentException("Language option not found: " + languageLabel);
    }

    // Utility method to check if an element is displayed
    private boolean isElementDisplayed(String xpath) {
        try {
            return driver.findElement(By.xpath(xpath)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Utility method to find an element by XPath
    private WebElement findElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    // Retrieves the list of language options
    private List<WebElement> getLanguageOptions() {
        return driver.findElements(By.xpath(LANGUAGE_OPTION_XPATH));
    }
}
