package com.academy.ui.runners;

import com.academy.ui.pages.greenCity.CreateNewsPage;
import com.academy.ui.pages.greenCity.NewsPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;

public class TestRunnerMethodInitDriverLoginCreateNews  extends  BaseTestRunner{
    protected NewsPage page;
    protected CreateNewsPage createNewsPage;

    @BeforeMethod
    public void initDriver(){
        initChromeDriver(List.of());
        driver.get(configProperties.getNewsPageGreenCityUrl());
        page = new NewsPage(driver);
    }

    @BeforeMethod
    public void setUpPage() {
        String email = configProperties.getUserEmail();
        String password = configProperties.getUserPassword();
        page.getHeaderComponent()
                .setLanguage("en");
        page.getHeaderComponent()
                .clickSignInButtonAndGetLoginForm()
                .fillForm(email, password)
                .clickSignInButtonSuccessfulLogin()
                .getHeaderComponent()
                .clickNewsLInk()
                .clickCreateNews();
        createNewsPage = new CreateNewsPage(driver);
    }

    @AfterMethod
    public void baseTearDown(){
        closeBrowser();
    }
}
