package com.academy.ui.pages.greenCity;

import com.academy.ui.components.NewsFilterComponent;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsPage extends BasePageGreenCity {

    String CREATE_NEWS_BUTTON_XPATH = ".//a[contains(@class,'create')]//div[@id='create-button']";
    @FindBy(xpath = "//div[@class='news-info-author']")
    private WebElement authorElement;

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
        findWithWaitElement(CREATE_NEWS_BUTTON_XPATH,10).click();
        findWithWaitElement(CREATE_NEWS_BUTTON_XPATH, 10).click();
        return new CreateNewsPage(driver);
    }

    public NewsFilterComponent getFilterComponent() {
        return new NewsFilterComponent(driver, rootFilterComponent);
    }

    public boolean isNewsDisplayedWithTitle(String title) {
        findWithWaitElement("//img[@class='list-image-content']", 10);
        List<WebElement> newsItems = driver.findElements(By.xpath("//div[@class='list-gallery']"));
        for (WebElement item : newsItems) {
            WebElement titleElement = item.findElement(By.xpath(".//h3"));
            if (titleElement.getText().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public void clickOnNewsWithTitle(String title) {
        findWithWaitElement("//img[@class='list-image-content']", 10);
        List<WebElement> newsItems = driver.findElements(By.xpath("//div[@class='list-gallery']"));
        for (WebElement item : newsItems) {
            WebElement titleElement = item.findElement(By.xpath(".//h3"));
            if (titleElement.getText().equals(title)) {
                item.click();
                return;
            }
        }
        throw new NoSuchElementException("News with title '" + title + "' was not found.");
    }

    public String getAuthorName() {
        return authorElement.getText();
    }

}

