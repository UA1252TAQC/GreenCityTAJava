package com.academy.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PreviewPage extends BasePage {
    @FindBy(xpath = "//div[@class = 'back-button']/a")
    private WebElement backToEditingButton;
    public PreviewPage(WebDriver driver) {
        super(driver);
    }

    public PreviewPage clickBackToEditing() {
        backToEditingButton.click();
        return this;
    }
}