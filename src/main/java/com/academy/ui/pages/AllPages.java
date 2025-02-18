package com.academy.ui.pages;

import com.academy.ui.pages.greenCity.CreateNewsPage;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.pages.greenCity.NewsPage;
import org.openqa.selenium.WebDriver;

public class AllPages {
    private WebDriver driver;

    private CreateNewsPage createNewsPage;
    private HomePage homePage;
    private NewsPage newsPage;
    public AllPages(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public CreateNewsPage getCreateNewsPage() {
        return createNewsPage == null ? new CreateNewsPage(driver): createNewsPage;
    }

    public HomePage getHomePage() {
        return homePage == null ? new HomePage(driver): homePage;
    }

    public BasePage getNewsPage() {
        return newsPage == null ? new NewsPage(driver): newsPage;
    }
}
