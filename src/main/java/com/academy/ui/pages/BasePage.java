package com.academy.ui.pages;

import com.academy.ui.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Base {
    public BasePage(WebDriver driver) {
        super(driver);
    }

    public BasePage openURL(String url) {
        driver.get(url);
        PageFactory.initElements(driver, this);
        return this;
    }

    public BasePage openUrlInNewTab(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "', '_blank');");
        return this;
    }
}
