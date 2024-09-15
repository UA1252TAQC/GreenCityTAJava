package com.academy.ui.pages;

import com.academy.ui.Base;
import com.academy.ui.components.HeaderComponent;
import com.academy.ui.components.LoginModalComponent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class BasePageGreenCity extends Base {

    @FindBy(how = How.XPATH, using = ".//div[@class='header_container']")
    protected WebElement headerComponentRoot;

    private final HeaderComponent headerComponent;

    public BasePageGreenCity(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.headerComponent = new HeaderComponent(driver, headerComponentRoot);

    }

    public LoginModalComponent clickSignInButtonInHeader() {
        return headerComponent.clickSignInLink();
    }

    public void openUrl(String url) {
        this.driver.get(url);
    }

    public String getUserNameInHeader() {
     return headerComponent.getUserName();
    }

    public void openUrlInNewTab(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "', '_blank');");
        switchToActiveTab();
    }
    public void switchToActiveTab() {
        sleep(5);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.getLast());
    }

    public String getLocalStorageItem(String key) {
        return (String) ((JavascriptExecutor) driver).executeScript(String.format(
                "return window.localStorage.getItem('%s');", key));
    }

    public String getAuthToken() {
        return getLocalStorageItem("accessToken");
    }
    public BasePageGreenCity setLanguage(String language) {
        this.headerComponent.setLanguage(language);
        return this;
    }
}
