package com.academy.ui.pages;

import com.academy.ui.components.SignInComponent;
import org.openqa.selenium.WebDriver;
import com.academy.ui.components.HeaderComponent;
import com.academy.ui.components.RegistrationComponent;

public class HomePage extends BasePage {
    private final HeaderComponent headerComponent;

    public HomePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver, findElement(".//header[@role='banner']"));
    }

    public RegistrationComponent openRegistrationFormInHeader() {
        return headerComponent.openRegistrationForm();
    }

    public SignInComponent openSignInFormInHeader() {
        return headerComponent.openSignInForm();
    }

    public HomePage setLanguage(String language) {
        this.headerComponent.changeLanguage(language);
        return this;
    }
}
