package com.academy.ui.components.sub.news;

import com.academy.ui.components.BaseComponent;
import com.academy.ui.components.sub.ButtonState;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class FilterTag extends BaseComponent {
    public FilterTag(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @FindBy(xpath = ".//a")
    private WebElement button;

    @FindBy(xpath = ".//span[@class='text']")
    private WebElement text;

    @FindBy(xpath = ".//div[@class='global-tag-close-icon']")
    private WebElement closeIcon;

    public void click() {
        click(this.rootElement);
    }

    public boolean isDisplayed() {
        return isDisplayed(this.text);
    }

    public ButtonState getState() {
        return ButtonState.builder()
                .buttonColor(button.getCssValue("background-color"))
                .buttonSize(button.getSize())
                .text(getText(text))
                .textColor(text.getCssValue("color"))
                .location(button.getLocation())
                .build();
    }
}
