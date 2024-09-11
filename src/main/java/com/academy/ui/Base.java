package com.academy.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Base {
    protected WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElementWithAction(WebElement element) {
        new Actions(driver)
                .moveToElement(element)
                .click()
                .perform();
    }

    public void sleep(long seconds) {
        try{
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            //
        }
    }

}
