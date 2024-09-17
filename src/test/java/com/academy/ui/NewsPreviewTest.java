package com.academy.ui;

import com.academy.ui.constants.NewsTags;
import com.academy.ui.pages.greenCity.CreateNewsPage;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.pages.greenCity.NewsPage;
import com.academy.ui.runners.TestRunnerMethodInitDriverHomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NewsPreviewTest extends TestRunnerMethodInitDriverHomePage {

    private static final String NEWS_TITLE = "Workshop to educate your customers about eco-friendly living";
    private static final String NEWS_CONTENT = "Workshop to educate your customers about eco-friendly living";
    private CreateNewsPage createNewsPage;
    private NewsPage newsPage;

    @BeforeMethod
    public void setUpPage() {
        newsPage = new NewsPage(driver);
        String email = configProperties.getEmail();
        String password = configProperties.getPassword();
        page = new HomePage(driver);
        page.getHeaderComponent()
                .setLanguage("en");
        page.getHeaderComponent()
                .openLoginForm()
                .fillForm(email, password)
                .getHeaderComponent()
                .clickNewsButton();

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
