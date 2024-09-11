package com.academy.ui.pages;

import com.academy.ui.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Base {
    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openURL(String url){
        driver.get(url);
    }
}
