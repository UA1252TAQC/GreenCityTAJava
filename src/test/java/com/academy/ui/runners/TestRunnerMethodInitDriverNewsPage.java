package com.academy.ui.runners;

import com.academy.ui.pages.greenCity.NewsPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;

public class TestRunnerMethodInitDriverNewsPage extends BaseTestRunner{
    protected NewsPage page;


    @BeforeMethod
    public void initDriver(){
        initChromeDriver(List.of());
        driver.get(configProperties.getNewsPageGreenCityUrl());
        page = new NewsPage(driver);
    }

    @AfterMethod
    public void baseTearDown(){
        closeBrowser();
    }
}
