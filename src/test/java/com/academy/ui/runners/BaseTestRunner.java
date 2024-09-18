package com.academy.ui.runners;

import com.academy.utils.LocalizationUtils;
import com.academy.utils.props.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class BaseTestRunner {
    protected static final long IMPLICITLY_WAIT_DURATION = 1;
    protected static ConfigProperties configProperties;
    protected static WebDriver driver;


    protected LocalizationUtils localizationUtils;
    protected SoftAssert softAssert;

    @BeforeSuite
    public void setUpWebDriver() {
        configProperties = new ConfigProperties();
        localizationUtils = new LocalizationUtils();
    }

    @BeforeMethod
    public void createSoftAssert() {
        softAssert = new SoftAssert();
    }

    @AfterSuite(alwaysRun = true)
    public void baseTearDown() {
        closeBrowser();
    }

    public void initChromeDriver(List<String> chromeOptions) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        for (String option : chromeOptions) {
            options.addArguments(option);
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_DURATION));
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
