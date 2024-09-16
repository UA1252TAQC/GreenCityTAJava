package com.academy.ui.runners.base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import com.academy.utils.LocalizationUtils;
import com.academy.utils.props.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverRunner {
    private static final long IMPLICITLY_WAIT_DURATION = 1;
    protected ConfigProperties configProperties;
    protected WebDriver driver;

    protected LocalizationUtils localizationUtils;
    protected SoftAssert softAssert;

    @BeforeSuite
    public void setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_DURATION));

        
        configProperties = new ConfigProperties();
        localizationUtils = new LocalizationUtils();
    }

    @BeforeMethod
    public void configureParamsMethod() {
        softAssert = new SoftAssert();
    }
    
    public void closeBrowser(){
        if(driver != null) {
            driver.quit();
        }
    }
}
