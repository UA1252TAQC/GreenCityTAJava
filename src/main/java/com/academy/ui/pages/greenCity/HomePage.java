package com.academy.ui.pages.greenCity;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePageGreenCity {
    public HomePage(WebDriver driver) {
        super(driver);
        findWithWaitElement(".//header[@role='banner']", 20);
    }

    public HomePage setLanguage(String language) {
        super.getHeaderComponent().setLanguage(language);
        return this;
    }
}
