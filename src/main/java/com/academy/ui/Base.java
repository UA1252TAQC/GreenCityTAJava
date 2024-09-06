package com.academy.ui;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
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
           throw new NoSuchElementException("Header component is not visible.");
        }
    }

    protected WebElement findElement(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }
}
