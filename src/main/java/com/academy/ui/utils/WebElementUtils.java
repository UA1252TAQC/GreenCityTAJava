package com.academy.ui.utils;

import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

public class WebElementUtils {

    public static void click(WebElement element) {
        if (isDisplayed(element)) {
            element.click();
        } else {
            throw new NoSuchElementException("Element is not visible.");
        }
    }

    public static boolean isDisplayed(WebElement element) {
        if (element == null) {
            return false;
        }
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isEnabled(WebElement element) {
        if (isDisplayed(element)) {
            return false;
        }

        return element.isEnabled();
    }
}
