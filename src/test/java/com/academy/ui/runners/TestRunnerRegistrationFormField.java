package com.academy.ui.runners;

import com.academy.ui.components.RegistrationModalComponent;
import com.academy.ui.pages.greenCity.HomePage;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.*;

import java.util.List;

public class TestRunnerRegistrationFormField extends BaseTestRunner{
    protected ImmutableMap<String, String> localizedMessages;
    protected HomePage page;
    protected RegistrationModalComponent form;

    @BeforeClass
    @Parameters({"language"})
    public void setUp(@Optional("Ua") String language) {
        initChromeDriver(List.of());
        driver.get(configProperties.getHomePageGreenCityUrl());

        this.page = new HomePage(driver).setLanguage(language);
        this.localizedMessages = localizationUtils.getFormMessages(language);
    }

    @BeforeMethod
    public void setUpMethod() {
        form = page.getHeaderComponent().openRegistrationForm();
    }

    @AfterMethod
    public void tearDownMethod() {
        form.close();
    }

    @AfterClass
    public void tearDownClass() {
        closeBrowser();
    }
}
