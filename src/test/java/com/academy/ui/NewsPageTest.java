package com.academy.ui;

import com.academy.ui.runners.TestRunnerClassInitDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.academy.ui.components.NewsFilterComponent;
import com.academy.ui.pages.greenCity.NewsPage;

public class NewsPageTest extends TestRunnerClassInitDriver {
    private NewsPage page;
    private NewsFilterComponent filter;

    @BeforeClass
    public void openNewsPage() {
        driver.get(configProperties.getBaseUrl() + "/#/news");

        this.page = new NewsPage(driver);
        this.filter = page.getFilterComponent();
    }

    @BeforeClass
    public void initDriver(){
        super.initDriver();
        driver.get(configProperties.getBaseUrl() + "/#/news");
    }

    @BeforeMethod()
    public void goToNews(){
        driver.get(configProperties.getBaseUrl() + "/#/news");
    }

    @Test()
    public void allTagsDisplayedCorrectly() {
        softAssert.assertTrue(filter.getTitle().isDisplayed());
        softAssert.assertTrue(filter.getNewsTag().isDisplayed());
        softAssert.assertTrue(filter.getEventsTag().isDisplayed());
        softAssert.assertTrue(filter.getEducationTag().isDisplayed());
        softAssert.assertTrue(filter.getInitiativesTag().isDisplayed());
        softAssert.assertTrue(filter.getAdsTag().isDisplayed());

        softAssert.assertEquals(filter.getNewsTag().getState(), null);
        softAssert.assertEquals(filter.getEventsTag().getState(), null);
        softAssert.assertEquals(filter.getEducationTag().getState(), null);
        softAssert.assertEquals(filter.getInitiativesTag().getState(), null);
        softAssert.assertEquals(filter.getAdsTag().getState(), null);
        softAssert.assertEquals(filter.getNewsTag().getAllCss(), null);
        softAssert.assertAll();
    }
}
