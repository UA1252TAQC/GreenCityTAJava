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

    public Map<String, String> getAllCss() { //TODO move to base?
        String script =
                "var s = '';" +
                        "var o = getComputedStyle(arguments[0]);" +
                        "for(var i = 0; i < o.length; i++) {" +
                        "s+=o[i] + ':' + o.getPropertyValue(o[i])+';'; " +
                        "}" +
                        "return s;";

        String cssString = ((JavascriptExecutor) driver).executeScript(script, button).toString();
        Map<String, String> map = new HashMap<>();

        String[] pairs = cssString.split(";");

        for (String pair : pairs) {
            if (pair.isEmpty()) {
                continue;
            }

            String[] keyValue = pair.split(":");

            if (keyValue.length == 2) {
                map.put(keyValue[0], keyValue[1]);
            }
        }

        return map;
    }

    public ButtonState getState() {
        return ButtonState.builder() //each property should be to compare?
                .buttonColor(button.getCssValue("background-color"))
                .buttonSize(button.getSize())
                .text(getText(text))
                .textColor(text.getCssValue("color"))
                .location(button.getLocation())
                .build();
    }
}
