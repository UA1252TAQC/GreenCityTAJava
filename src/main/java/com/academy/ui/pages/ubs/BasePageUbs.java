package com.academy.ui.pages.ubs;

import com.academy.ui.components.UbsHeaderComponent;
import com.academy.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class BasePageUbs extends BasePage {
    public BasePageUbs(WebDriver driver) {
        super(driver);
    }

    public UbsHeaderComponent getHeaderComponent() {
        return new UbsHeaderComponent(driver, headerRootElement);
    }
}
