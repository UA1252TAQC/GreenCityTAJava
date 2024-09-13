package com.academy.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsPreviewPage extends BasePageGreenCity {
    @FindBy(xpath = "//div[@class = 'back-button']/a")
    private WebElement backToEditingButton;
    public NewsPreviewPage(WebDriver driver) {
        super(driver);
    }

    public NewsPreviewPage clickBackToEditing() {
        backToEditingButton.click();
        return this;
    }
}