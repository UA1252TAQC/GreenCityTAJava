package com.academy.ui.runners;

import com.academy.utils.LocalizationUtils;
import com.academy.utils.props.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;


@Listeners(TestNgListener.class)
public class BaseTestRunner{
    protected static final long IMPLICITLY_WAIT_DURATION = 1;
    protected static ConfigProperties configProperties;
    protected static WebDriver driver;


    protected static LocalizationUtils localizationUtils;
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

    @Step("Initialize Chrome WebDriver with options: {chromeOptions}")
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

    @Step("Close the browser session")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void setWindowWidth(int width) {
        int height = driver.manage().window().getSize().height;
        Dimension dimension = new Dimension(width, height);
        driver.manage().window().setSize(dimension);
    }

    public void setZoomTo(int zoomLevel) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.body.style.zoom = '" + zoomLevel + "%';");
    }

    public boolean hasHorizontalScrollbar(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (Boolean) jsExecutor.executeScript(
                "return arguments[0].scrollWidth > arguments[0].clientWidth;", element);
    }
}
