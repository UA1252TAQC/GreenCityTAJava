package com.academy.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewsPreviewPage extends BasePageGreenCity {

    @FindBy(how = How.XPATH, using = ".//div[@class='news-title word-wrap']")
    protected WebElement previewPageTitle;
    @FindBy(xpath = "//div[@class = 'back-button']/a")
    private WebElement backToEditingButton;

    public NewsPreviewPage(WebDriver driver) {
        super(driver);
    }

    public NewsPreviewPage clickBackToEditing() {
        backToEditingButton.click();
        return this;
    }

    public boolean isPreviewPageDisplayed() {
        return isDisplayed(previewPageTitle);
    }
}