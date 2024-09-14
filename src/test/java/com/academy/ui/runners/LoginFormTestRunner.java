package com.academy.ui.runners;

import com.academy.utils.props.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
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
        driver.get(configProperties.getBaseUrl() + configProperties.getNewsPageUrl());
    }

    @DataProvider(name = "validUserDataProvider")
    public Object[][] validUserDataProvider() {
        HashMap<String, String> validUserData = new <String, String>HashMap();
        validUserData.put("email", configProperties.getRegisteredUserEmail());
        validUserData.put("password", configProperties.getRegisteredUserPassword());
        validUserData.put("id", configProperties.getRegisteredUserId());
        validUserData.put("firstName", configProperties.getRegisteredUserFirstName());
        validUserData.put("lastName", configProperties.getRegisteredUserLastName());
        return new Object[][]{
                {validUserData}
        };
    }
}
