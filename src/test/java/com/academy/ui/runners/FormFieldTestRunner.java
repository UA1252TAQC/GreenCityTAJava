package com.academy.ui.runners;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.academy.utils.props.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FormFieldTestRunner {
    protected static final long IMPLICITLY_WAIT_DURATION = 1;
    protected ConfigProperties configProperties;
    protected WebDriver driver;

    @BeforeSuite
    public void setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
        configProperties = new ConfigProperties();
    }

    @BeforeClass
    public void baseSetUp() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_DURATION));
        driver.get(configProperties.getBaseUrl() + "/#/greenCity");
    }

    @AfterClass
    public void baseTearDown(){
        if(driver != null) {
            driver.quit();
        }
    }
}
