package com.academy.ui.pages.ubs;

import com.academy.ui.components.RegistrationModalComponent;
import com.academy.ui.components.UbsHeaderComponent;
import com.academy.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class BasePageUbs extends BasePage {
    public BasePageUbs(WebDriver driver) {
        super(driver);
    }

    protected UbsHeaderComponent getHeaderComponent() {
        return new UbsHeaderComponent(driver, headerRootElement);
    }

    public RegistrationModalComponent openRegistrationFormInHeader() {
        return getHeaderComponent().openRegistrationForm();
    }
}
