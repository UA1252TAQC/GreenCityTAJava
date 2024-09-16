package com.academy.ui.pages.greenCity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsPage extends BasePageGreenCity {

    @FindBy(xpath = ".//*[@id='create-button']")
    protected WebElement createNewsButton;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewsPage goToCreateNews() {
        createNewsButton.click();
        return new CreateNewsPage(driver);
    }
}
