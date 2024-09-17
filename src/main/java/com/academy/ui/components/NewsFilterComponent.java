package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.academy.ui.components.sub.news.FilterTag;
import lombok.Getter;

public class NewsFilterComponent extends BaseComponent {
    public NewsFilterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    
    @Getter
    @FindBy(xpath = ".//span[@class='filter']")
    private WebElement title;

    @FindBy(xpath = ".//div[@aria-label='filter by items']/button[1]")
    private WebElement newsRootElement;

    @FindBy(xpath = ".//div[@aria-label='filter by items']/button[2]")
    private WebElement eventsRootElement;

    @FindBy(xpath = ".//div[@aria-label='filter by items']/button[3]")
    private WebElement educationRootElement;

    @FindBy(xpath = ".//div[@aria-label='filter by items']/button[4]")
    private WebElement initiativesRootElement;

    @FindBy(xpath = ".//div[@aria-label='filter by items']/button[5]")
    private WebElement adsRootElement;

    public FilterTag getNewsTag() {
        return new FilterTag(driver, newsRootElement);
    }
    
    public FilterTag getEventsTag() {
        return new FilterTag(driver, eventsRootElement);
    }

    public FilterTag getEducationTag() {
        return new FilterTag(driver, educationRootElement);
    }

    public FilterTag getInitiativesTag() {
        return new FilterTag(driver, initiativesRootElement);
    }

    public FilterTag getAdsTag() {
        return new FilterTag(driver, adsRootElement);
    }
}
