package com.academy.ui.runners;

import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.pages.ubs.HomePageUbs;
import com.academy.utils.MailUtils;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Step;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.List;

public class TestRunnerRegistrationForm extends BaseTestRunner {
    protected ImmutableMap<String, String> localizedMessages;
    protected MailUtils mailUtils;
    protected String language;

    @BeforeClass
    @Parameters({"language"})
    public void setUp(@Optional("En") String language) {
        this.localizedMessages = localizationUtils.getFormMessages(language);
        this.mailUtils = new MailUtils();
        this.language = language;
    }

    @BeforeMethod
    public void setUpMethod(ITestContext context) {
        initChromeDriver(List.of("--disable-blink-features=AutomationControlled"));
        context.setAttribute("webDriver", driver);
        driver.get(configProperties.getHomePageGreenCityUrl());
    }

    @AfterMethod
    public void tearDownMethod() {
        closeBrowser();
    }


    @Step("Open Ubs page in a new tab")
    protected HomePageUbs openUbsPageInNewTab(HomePage homePage) {
        homePage.openUrlInNewTab(configProperties.getBaseUrl() + "/#/ubs");
        return new HomePageUbs(driver);
    }

    @Step("Open Home page in a new tab")
    protected HomePage openHomePageInNewTab(HomePageUbs ubsPage) {
        ubsPage.openUrlInNewTab(configProperties.getBaseUrl() + "/#/greenCity");
        return new HomePage(driver);
    }

    @Step("Open Ubs page")
    protected HomePageUbs openUbsPage() {
        driver.get(configProperties.getBaseUrl() + "/#/ubs");
        return new HomePageUbs(driver).setLanguage(language);
    }

    @Step("Open Home page")
    protected HomePage openHomePage() {
        driver.get(configProperties.getBaseUrl() + "/#/greenCity");
        return new HomePage(driver).setLanguage(language);
    }
}
