package com.academy.ui;

import com.academy.ui.constants.NewsTags;
import com.academy.ui.pages.CreateNewsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.pages.NewsPage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NewsPreviewTest extends BaseTestRunner {

    private static final String NEWS_TITLE = "Workshop to educate your customers about eco-friendly living";
    private static final String NEWS_CONTENT = "Workshop to educate your customers about eco-friendly living";
    private HomePage homePage;
    private CreateNewsPage createNewsPage;
    private SoftAssert softAssert;
    private NewsPage newsPage;

    @BeforeMethod
    public void setUpPage() {
        newsPage = new NewsPage(driver);
        softAssert = new SoftAssert();
        String email = configProperties.getEmail();
        String password = configProperties.getPassword();
        homePage = new HomePage(driver);
        homePage.switchLanguage("en")
            .openLoginFormInHeader()
            .fillForm(email, password)
            .openNewsInHeader();
        createNewsPage = new CreateNewsPage(driver);
    }

    @Test
    public void previewPageAfterPreviewButton() {
        newsPage.clickCreateNews()
            .fillTheNewsForm(NEWS_TITLE, new NewsTags[]{NewsTags.NEWS}, NEWS_CONTENT, "en");

        boolean isPreviewButtonEnabled = createNewsPage.newsPreviewButtonIsEnabled();
        softAssert.assertTrue(isPreviewButtonEnabled);

        boolean isPreviewPageDisplayed = createNewsPage.clickPreviewButton()
            .isPreviewPageDisplayed();
        softAssert.assertTrue(isPreviewPageDisplayed);

        softAssert.assertAll();

    }
}
