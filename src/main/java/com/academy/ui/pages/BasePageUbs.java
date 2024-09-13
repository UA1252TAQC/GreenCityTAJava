package com.academy.ui.pages;

import com.academy.ui.Base;
import com.academy.ui.components.RegistrationComponent;
import com.academy.ui.components.UbsHeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePageUbs extends Base {
    private final UbsHeaderComponent headerComponent;

    public BasePageUbs(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.headerComponent = new UbsHeaderComponent(driver, findElement(".//header[@role='banner']"));
    }

    public RegistrationComponent openRegistrationFormInHeader() {
        return headerComponent.openRegistrationForm();
    }

    public BasePageUbs setLanguage(String language) {
        this.headerComponent.setLanguage(language);
        return this;
    }

    public String getAccountSubmitPopUpMessage() {
        return findWithWaitElement(".//div[@class='mat-mdc-snack-bar-label mdc-snackbar__label']").getText();
    }
}
