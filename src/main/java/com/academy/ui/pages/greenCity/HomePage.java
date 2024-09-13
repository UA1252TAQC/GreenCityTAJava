package com.academy.ui.pages.greenCity;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePageGreenCity {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage setLanguage(String language) {
        super.getHeaderComponent().setLanguage(language);
        return this;
    }
}
