package com.academy.ui.pages.ubs;

import org.openqa.selenium.WebDriver;

public class HomePageUbs extends BasePageUbs {
    public HomePageUbs(WebDriver driver) {
        super(driver);
        findWithWaitElement(".//header[@role='banner']", 20);
    }

    public HomePageUbs setLanguage(String language) {
        super.getHeaderComponent().setLanguage(language);
        return this;
    }
}
