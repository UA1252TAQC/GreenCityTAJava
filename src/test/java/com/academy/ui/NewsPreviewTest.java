
package com.academy.ui;

import com.academy.ui.constants.NewsTags;
import com.academy.ui.pages.greenCity.CreateNewsPage;
import com.academy.ui.pages.greenCity.HomePage;
import com.academy.ui.pages.greenCity.NewsPage;
import com.academy.ui.runners.TestRunnerMethodInitDriverLoginCreateNews;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NewsPreviewTest extends TestRunnerMethodInitDriverLoginCreateNews {

    private static final String NEWS_TITLE = "Workshop to educate your customers about eco-friendly living";
    private static final String NEWS_CONTENT = "Workshop to educate your customers about eco-friendly living";

    @Test
    public void previewPage() {
        createNewsPage
                .fillTheNewsForm(NEWS_TITLE, new NewsTags[]{NewsTags.NEWS}, NEWS_CONTENT, "en");

        boolean isPreviewButtonEnabled = createNewsPage.newsPreviewButtonIsEnabled();
        softAssert.assertTrue(isPreviewButtonEnabled);

        boolean isPreviewPageDisplayed = createNewsPage.clickPreviewButton()
                .isPreviewPageDisplayed();
        softAssert.assertTrue(isPreviewPageDisplayed);

        softAssert.assertAll();

    }
}
