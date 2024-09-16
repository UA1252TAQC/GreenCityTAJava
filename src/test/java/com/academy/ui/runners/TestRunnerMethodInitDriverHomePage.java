package com.academy.ui.runners;

import com.academy.ui.pages.greenCity.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;

public class TestRunnerMethodInitDriverHomePage extends BaseTestRunner {
    protected HomePage page;


    @BeforeMethod
    public void initDriver(){
        initChromeDriver(List.of());
        driver.get(configProperties.getHomePageGreenCityUrl());
        page = new HomePage(driver);
    }

    @AfterMethod
    public void baseTearDown(){
        closeBrowser();
    }
}
