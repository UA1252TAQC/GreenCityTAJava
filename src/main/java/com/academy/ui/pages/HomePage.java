package com.academy.ui.pages;

import org.openqa.selenium.WebDriver;
import com.academy.ui.components.HeaderComponent;
import com.academy.ui.components.RegistrationForm;

public class HomePage extends BasePage {
    private HeaderComponent headerComponent;
    private RegistrationForm registrationComponent;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openRegistrationFormInHeader() {
        getHeaderComponent()
        .clickRegister();
        return this;
    }

    public HeaderComponent getHeaderComponent() {
        if (headerComponent == null) {
            var root = findElement("//header[@role='banner']");
            headerComponent = new HeaderComponent(driver, root);
        }

        return headerComponent;
    }

    public RegistrationForm getRegistrationComponent() {
        if (registrationComponent == null) {
            var root = findElement("//app-auth-modal");
            registrationComponent = new RegistrationForm(driver, root);
        }

        return registrationComponent;
    }
}
