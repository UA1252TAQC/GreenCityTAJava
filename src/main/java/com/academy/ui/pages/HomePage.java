package com.academy.ui.pages;

import com.academy.ui.components.HeaderComponent;
import com.academy.ui.components.RegistrationComponent;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePageGreenCity {
    private final HeaderComponent headerComponent;

    public HomePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver, findElement(".//header[@role='banner']"));
    }

    public RegistrationComponent openRegistrationFormInHeader() {
        return headerComponent.openRegistrationForm();
    }

    public HomePage setLanguage(String language) {
        this.headerComponent.setLanguage(language);
        return this;
    }

    public String getSuccessRegisteredPopUpMessage() {
        return findWithWaitElement("//snack-bar-container//span").getText();
    }
}
