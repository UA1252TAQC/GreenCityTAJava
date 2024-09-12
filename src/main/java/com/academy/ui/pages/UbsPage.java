package com.academy.ui.pages;

import com.academy.ui.components.RegistrationComponent;
import com.academy.ui.components.UbsHeaderComponent;
import org.openqa.selenium.WebDriver;

public class UbsPage extends BasePage {
    private final UbsHeaderComponent headerComponent;

    public UbsPage(WebDriver driver) {
        super(driver);
        this.headerComponent = new UbsHeaderComponent(driver, findElement(".//header[@role='banner']"));
    }

    public RegistrationComponent openRegistrationFormInHeader() {
        return headerComponent.openRegistrationForm();
    }

    public UbsPage setLanguage(String language) {
        this.headerComponent.setLanguage(language);
        return this;
    }

    public String getAccountSubmitPopUpMessage() {
        return findWithWaitElement(".//div[@class='mat-mdc-snack-bar-label mdc-snackbar__label']").getText();
    }
}
