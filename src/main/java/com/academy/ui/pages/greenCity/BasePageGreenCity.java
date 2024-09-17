package com.academy.ui.pages.greenCity;

import com.academy.ui.components.HeaderComponent;
import com.academy.ui.components.LoginModalComponent;
import com.academy.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class BasePageGreenCity extends BasePage {

    public BasePageGreenCity(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent getHeaderComponent() {
        return new HeaderComponent(driver, headerRootElement);
    }
}
