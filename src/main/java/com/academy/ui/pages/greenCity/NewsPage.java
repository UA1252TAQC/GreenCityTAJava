package com.academy.ui.pages.greenCity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.academy.ui.components.NewsFilterComponent;

public class NewsPage extends BasePageGreenCity {

    @FindBy(xpath = ".//*[@id='create-button']")
    private WebElement createNewsButton;

    @FindBy(xpath = ".//h1[@class='main-header']")
    private WebElement title;

    @FindBy(xpath = ".//app-remaining-count")
    private WebElement newsCountMessage;

    // @FindBy(xpath = ".//span[@aria-label='table view']/em")
    // private WebElement tableViewButton;

    // @FindBy(xpath = ".//span[@aria-label='list view']/em")
    // private WebElement listViewButton;

    //itemsComponent

    
    @FindBy(xpath = ".//app-tag-filter")
    private WebElement rootFilterComponent;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewsPage clickCreateNews() {
        click(createNewsButton);
        return new CreateNewsPage(driver);
    }

    public NewsFilterComponent getFilterComponent() {
        return new NewsFilterComponent(driver, rootFilterComponent);
    }
}
