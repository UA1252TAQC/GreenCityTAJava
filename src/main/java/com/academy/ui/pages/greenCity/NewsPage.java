package com.academy.ui.pages.greenCity;

import com.academy.ui.components.NewsFilterComponent;
import java.util.List;
import java.util.NoSuchElementException;

import com.academy.ui.constants.NewsTags;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewsPage extends BasePageGreenCity {

    private static final String CREATE_NEWS_BUTTON_XPATH = ".//a[contains(@class,'create')]//div[@id='create-button']";
    private static final String NEWS_ITEM_BOX = "//app-news-list-gallery-view";

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
        findWithWaitElement(CREATE_NEWS_BUTTON_XPATH, 10).click();
        return new CreateNewsPage(driver);
    }

    public NewsFilterComponent getFilterComponent() {
        return new NewsFilterComponent(driver, rootFilterComponent);
    }

    public boolean isNewsDisplayed(String title, String content, List<NewsTags> tags) {
        for (WebElement item : driver.findElements(By.xpath(NEWS_ITEM_BOX))) {
            boolean titleMatch = !item.findElements(By.xpath(".//div[contains(@class, 'title-list')]//h3" + "[contains(text(), '" + title + "')]"))
                    .isEmpty();
            boolean contentMatch = !item.findElements(By.xpath(".//div[contains(@class, 'list-text')]//p[contains(text(), '" + content  + "')]"))
                    .isEmpty();
            boolean isTagsPresent = true;
            for (NewsTags tag : tags) {
                 List <WebElement> addedTagUa = item.findElements(By.xpath(".//div[contains(@class, 'ul-eco-buttons') and contains(text(),'" + tag.getText("ua") + "')]"));
                List <WebElement> addedTagEn = item.findElements(By.xpath(".//div[contains(@class, 'ul-eco-buttons') and contains(text(),'" + tag.getText("en") + "')]"));
                isTagsPresent = isTagsPresent && (!addedTagUa.isEmpty() || !addedTagEn.isEmpty());
            }
            boolean result = titleMatch && contentMatch && isTagsPresent;
            if(result) {
                return result;
            };
        }
        return false;
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

