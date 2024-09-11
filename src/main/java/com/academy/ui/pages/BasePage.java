package com.academy.ui.pages;

import com.academy.ui.Base;
import com.academy.ui.components.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Base {
    @FindBy(how = How.XPATH, using = "//div[@class='header_container']")
    protected WebElement headerRoot;

    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void openURL(String url){
        driver.get(url);
    }

    public HeaderComponent getHeaderComponent() {
         return new HeaderComponent(driver, headerRoot);
    }
}
