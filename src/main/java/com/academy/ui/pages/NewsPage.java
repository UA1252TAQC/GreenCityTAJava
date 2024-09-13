package com.academy.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewsPage extends BasePage {

    @FindBy(how = How.XPATH, using = "./[@id='create-button']")
    protected WebElement createNewsButton;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewsPage goToCreateNews() {
        createNewsButton.click();
        return new CreateNewsPage(driver);
    }
}
