package com.academy.cucumber.step_definitions;

import com.academy.utils.props.ConfigProperties;

import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.io.IOException;
import java.time.Duration;





public class BaseDefinition {
    protected static WebDriver driver;
    protected static ConfigProperties testValueProvider;

    @BeforeAll
    public static void beforeAll() throws IOException {
        testValueProvider = new ConfigProperties();
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before(){
        ChromeOptions options = new ChromeOptions();
//        if (testValueProvider.getHeadlessMode()) {
//            options.addArguments("--headless");
//            options.addArguments("--window-size=1920,1080", "--no-sandbox", "'--disable-dev-shm-usage");
//        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(testValueProvider.getHomePageGreenCityUrl());
    }

    @After
    public void after() throws IOException {
        if (driver != null) {
            driver.quit();
        }
    }

    public static ConfigProperties getTestValueProvider() {
        return testValueProvider;
    }

    public WebDriver getDriver(){
        return driver;
    }

}