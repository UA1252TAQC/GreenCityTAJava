package com.academy.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import com.academy.ui.components.HeaderComponent;
import com.academy.ui.components.RegistrationComponent;

public class HomePage extends BasePage {
    @Getter(lazy = true)
    private final HeaderComponent headerComponent = new HeaderComponent(driver, findElement("//header[@role='banner']"));

    @Getter(lazy = true)
    private final RegistrationComponent registrationComponent = new RegistrationComponent(driver, findElement("//app-auth-modal"));

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openRegistrationFormInHeader() {
        getHeaderComponent()
        .clickRegister();
        return this;
    }
}
