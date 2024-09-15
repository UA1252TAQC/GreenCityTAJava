package com.academy.ui.runners;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.time.Duration;
import java.util.HashMap;

public class LoginFormTestRunner extends BaseTestRunner{


    @BeforeClass
    @Override
    public void baseSetUp() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_DURATION));
        //driver.get(configProperties.getBaseUrl() + configProperties.getHomePageUrlFragment());
    }

    @DataProvider(name = "validUserDataProvider")
    public Object[][] validUserDataProvider() {
        HashMap<String, String> validUserData = new <String, String>HashMap();
        validUserData.put("email", configProperties.getRegisteredUserEmail());
        validUserData.put("password", configProperties.getRegisteredUserPassword());
        validUserData.put("id", configProperties.getRegisteredUserId());
        validUserData.put("name", configProperties.getRegisteredUserName());
        return new Object[][]{
                {validUserData}
        };
    }

    protected void openPage(String pageUrl) {
        driver.get(pageUrl);
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected static String baseUrl() {
        return configProperties.getBaseUrl();
    }

    protected static String getHomePageUrl() {
        return baseUrl() + configProperties.getHomePageUrlFragment();
    }

    protected static String getNewsPageUrl() {
        return baseUrl() + configProperties.getNewsPageUrlFragment();
    }

    protected static String getProfilePageUrl() {
        return baseUrl() + configProperties.getProfilePageUrlFragment();
    }
}
