package com.academy.ui;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Base(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            //
        }
    }

    protected void click(WebElement element) {
        if (element.isDisplayed()) {
            element.click();
        } else {
            throw new NoSuchElementException("Element is not visible.");
        }
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isEnableToClickButton(WebElement element) {
        if (element.isDisplayed()) {
            if (element.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    protected WebElement findElement(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }
}
