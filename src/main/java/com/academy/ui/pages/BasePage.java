package com.academy.ui.pages;

import com.academy.ui.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class BasePage extends Base {
    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openUrl(String url) {
        driver.get(url);
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
}
