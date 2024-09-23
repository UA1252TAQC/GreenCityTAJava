package com.academy.ui.runners;

import com.academy.utils.MailUtils;
import com.google.common.collect.ImmutableMap;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.List;

public class TestRunnerRegistrationForm extends BaseTestRunner {
    protected ImmutableMap<String, String> localizedMessages;
    protected MailUtils mailUtils;
    protected String language;

    @BeforeClass
    @Parameters({"language"})
    public void setUp(@Optional("Ua") String language) {
        this.localizedMessages = localizationUtils.getFormMessages(language);
        this.mailUtils = new MailUtils();
        this.language = language;
    }

    @BeforeMethod
    public void setUpMethod(ITestContext context) {
        initChromeDriver(List.of());
        context.setAttribute("webDriver", driver);
        driver.get(configProperties.getHomePageGreenCityUrl());
    }

    @AfterMethod
    public void tearDownMethod() {
        closeBrowser();
    }
}
