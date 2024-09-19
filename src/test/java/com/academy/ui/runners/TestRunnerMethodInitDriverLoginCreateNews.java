package com.academy.ui.runners;

import com.academy.ui.pages.greenCity.CreateNewsPage;
import com.academy.ui.pages.greenCity.NewsPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;

public class TestRunnerMethodInitDriverLoginCreateNews  extends  BaseTestRunner{
    protected NewsPage newsPage;
    protected CreateNewsPage createNewsPage;

    @BeforeMethod
    public void initDriver(){
        initChromeDriver(List.of());
        driver.get(configProperties.getNewsPageGreenCityUrl());
        newsPage = new NewsPage(driver);
    }

    @BeforeMethod
    public void setUpPage() {
        String email = configProperties.getRegisteredUserEmail();
        String password = configProperties.getRegisteredUserPassword();
        newsPage.getHeaderComponent()
                .setLanguage("en");
        newsPage.getHeaderComponent()
                .openLoginForm()
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
