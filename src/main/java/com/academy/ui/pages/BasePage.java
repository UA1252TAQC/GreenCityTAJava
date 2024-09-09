package com.academy.ui.pages;

import com.academy.ui.Base;
import com.academy.ui.components.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Base {
    private final HeaderComponent headerComponent;

    public BasePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver, getHeaderRootElement());
    }
    public void openURL(String url){
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }

    public WebElement getHeaderRootElement() {
        return driver.findElement(By.xpath("//div[@class='header_container']"));
    }
}
