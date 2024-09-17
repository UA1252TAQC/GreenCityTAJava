package com.academy.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
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

    protected Actions getActions() {
        return new Actions(driver);
    }

    protected WebDriverWait getWait(long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            //
        }
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public String getCssValue(WebElement element, String property) {
        if (isDisplayed(element)) {
            return element.getCssValue(property);
        }

        return null;
    }

    public Dimension getSize(WebElement element) {
        if (isDisplayed(element)) {
            return element.getSize();
        }

        return null;
    }

    public Point getLocation(WebElement element) {
        if (isDisplayed(element)) {
            return element.getLocation();
        }

        return null;
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