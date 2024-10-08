package com.academy.ui.runners;

import com.academy.utils.LocalizationUtils;
import com.academy.utils.props.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.emulation.Emulation;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Optional;


@Listeners(TestNgListener.class)
public class BaseTestRunner {
    protected static final long IMPLICITLY_WAIT_DURATION = 1;
    protected static ConfigProperties configProperties;
    protected static WebDriver driver;
    protected static DevTools devTools;


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
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
    }

    @Step("Close the browser session")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public int getWindowWidth() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return ((Long) jse.executeScript("return window.innerWidth;")).intValue();
    }

    public int getWindowHeight() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return ((Long) jse.executeScript("return window.innerHeight;")).intValue();
    }

    @Step("Set window width")
    public void setWindowWidth(int width) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        devTools.send(Emulation.setDeviceMetricsOverride(
                        width,
                        0,
                        0,
                        false,
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                )
        );
    }

    public String getZoomLevel() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return jse.executeScript("return document.body.style.zoom || '100%';").toString();
    }

    @Step("Set zoom level")
    public void setZoomLevel(int zoomLevel) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.body.style.zoom = '" + zoomLevel + "%';");
    }

    public boolean hasHorizontalScrollbar(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (Boolean) jsExecutor.executeScript(
                "return arguments[0].scrollWidth > arguments[0].clientWidth;", element);
    }

    public boolean hasVerticalScrollbar(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (Boolean) jsExecutor.executeScript(
                "return arguments[0].scrollHeight > arguments[0].clientHeight;", element);
    }
}
