package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import lombok.Getter;

@Getter
public class RegistrationForm extends BaseComponent {

    public RegistrationForm(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
