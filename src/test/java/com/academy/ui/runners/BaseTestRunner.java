package com.academy.ui.runners;

import com.academy.utils.props.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTestRunner {
    private static final long IMPLICITLY_WAIT_DURATION = 1;
    protected static ConfigProperties configProperties;
    protected WebDriver driver;

    @BeforeSuite
    public void setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
        configProperties = new ConfigProperties();
    }
    
    @BeforeMethod
    public void baseSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_DURATION));
        driver.get(configProperties.getBaseUrl() + "/#/greenCity");
    }
    
    @AfterMethod
    public void baseTearDown(){
        if(driver != null) {
            driver.quit();
        }
    }
}
