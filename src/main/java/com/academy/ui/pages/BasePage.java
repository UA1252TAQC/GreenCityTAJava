package com.academy.ui.pages;

import com.academy.ui.Base;
import com.academy.ui.components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Base {
    public final HeaderComponent headerComponent;

    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        headerComponent = new HeaderComponent(driver, findElement(".//header[@role='banner']"));
    }

    public void openURL(String url){
        driver.get(url);
    }
}
