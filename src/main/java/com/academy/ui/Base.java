package com.academy.ui;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
    protected final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;

    public Base(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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

    public WebElement findWithWaitElement(String xPath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }

    public void click(WebElement element) {
        if (isDisplayed(element)) {
            actions.moveToElement(element).click().perform();
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
