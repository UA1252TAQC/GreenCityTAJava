package com.academy.ui;

import com.academy.ui.pages.CreateNewsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.FormTestRunner;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class NewsCreateTest extends FormTestRunner {

    private HomePage homePage;
    private CreateNewsPage createNewsPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUpPage() {
        softAssert = new SoftAssert();
        String email = configProperties.getEmail();
        String password = configProperties.getPassword();
        homePage = new HomePage(driver);
        homePage.switchLanguage("en")
                .openLoginFormInHeader()
                .fillForm(email, password)
                .getHeaderComponent()
                .clickNewsButton()
                .clickNews();
        createNewsPage = new CreateNewsPage(driver);
    }

    @Test
    public void firstTest() {

    }
}
