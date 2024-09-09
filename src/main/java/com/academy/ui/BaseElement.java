package com.academy.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.academy.ui.components.BaseComponent;

public class BaseElement extends BaseComponent {

    public BaseElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
