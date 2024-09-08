package com.academy.ui;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Base {
    protected final WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            //
        }
    }

    public WebElement findElement(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }

    public void click(WebElement element) {
        if (isDisplayed(element)) {
            new Actions(driver).moveToElement(element).click().perform();
        } else {
            throw new NoSuchElementException("Element is not visible.");
        }
    }

    public boolean isDisplayed(WebElement element) {
        if (element == null) {
            return false;
        }
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled(WebElement element) {
        if (isDisplayed(element)) {
            return false;
        }

        return element.isEnabled();
    }
}
