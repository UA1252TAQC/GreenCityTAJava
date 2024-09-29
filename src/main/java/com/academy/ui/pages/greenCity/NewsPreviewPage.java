package com.academy.ui.pages.greenCity;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewsPreviewPage extends BasePageGreenCity{

    @FindBy(how = How.XPATH, using = ".//div[@class='news-title word-wrap']")
    protected WebElement previewPageTitle;
    @FindBy(xpath = "//div[@class = 'back-button']/a")
    private WebElement backToEditingButton;

    public NewsPreviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click 'Back to Editing' button")
    public NewsPreviewPage clickBackToEditing() {
        backToEditingButton.click();
        return this;
    }

    @Step("Verify if preview page is displayed")
    public boolean isPreviewPageDisplayed() {
        return isDisplayed(previewPageTitle);
    }
}