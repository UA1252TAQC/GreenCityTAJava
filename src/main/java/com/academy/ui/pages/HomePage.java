package com.academy.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import com.academy.ui.components.HeaderComponent;
import com.academy.ui.components.RegistrationComponent;

public class HomePage extends BasePage {
    @Getter(lazy = true)
    private final HeaderComponent headerComponent = new HeaderComponent(driver, findElement("//header[@role='banner']"));

    private RegistrationComponent registrationComponent;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openRegistrationFormInHeader() {
        this.getHeaderComponent().clickRegister();
        return this;
    }

    public RegistrationComponent getRegistrationComponent() {
        if (registrationComponent == null) {
            registrationComponent = new RegistrationComponent(driver, findElement("//app-auth-modal"));
        }
        
        return registrationComponent;
    }

    public RegistrationComponent getNewRegistrationComponent() {
        registrationComponent = new RegistrationComponent(driver, findElement("//app-auth-modal"));
        return registrationComponent;
    }

    public HomePage setLanguage(String language) {
        if (language.equals("en")) {
            this.getHeaderComponent().clickListLanguage().clickEnglish();
        }
        
        return this;
    }
}
