package com.academy.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {
    protected final WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    public Actions getActions() {
        return new Actions(driver);
    }

    public WebDriverWait getWait(long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
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
        return getWait(5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }

    public WebElement findWithWaitElement(String xPath, long seconds) {
        return getWait(seconds).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }

    public void click(WebElement element) {
        getActions().moveToElement(element).click().perform();
    }

    public void clear(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled(WebElement element) {
        if (!isDisplayed(element)) {
            return false;
        }

        return element.isEnabled();
    }

}
