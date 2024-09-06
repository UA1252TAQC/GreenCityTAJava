package com.academy.ui.pages;

import org.openqa.selenium.WebDriver;
import com.academy.ui.components.HeaderComponent;
import com.academy.ui.components.RegistrationComponent;

public class HomePage extends BasePage {
    private HeaderComponent headerComponent;
    private RegistrationComponent registrationComponent;

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

    public RegistrationComponent getRegistrationComponent() {
        if (registrationComponent == null) {
            var root = findElement("//app-auth-modal");
            registrationComponent = new RegistrationComponent(driver, root);
        }

        return registrationComponent;
    }
}
