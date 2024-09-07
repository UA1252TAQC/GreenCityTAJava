package com.academy.ui.runners;

import com.academy.utils.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTestRunner {
    protected WebDriver driver;
    protected final long implicitlyWaitDuration = 10;
    protected static ConfigProperties configProperties;

    @BeforeSuite
    public void setUpWebDriver(){
        WebDriverManager.chromedriver().setup();
        configProperties = new ConfigProperties();
    }
    @BeforeMethod
    public void baseSetUp() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitDuration));
        driver.get(configProperties.getBaseUrl());
    }
    
    @AfterMethod
    public void baseTearDown(){
        if(driver != null) {
            driver.quit();
        }
    }
}
