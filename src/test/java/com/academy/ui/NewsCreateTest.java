package com.academy.ui;

import com.academy.ui.constants.NewsTags;
import com.academy.ui.pages.CreateNewsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import com.academy.ui.styleConstants.Colors;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class NewsCreateTest extends BaseTestRunner {

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
    public void selectUnSelectTags() {
        NewsTags[] tagsList1 = {NewsTags.NEWS, NewsTags.EDUCATION, NewsTags.EVENTS};
        NewsTags[] tagsList2 = {NewsTags.INITIATIVES, NewsTags.ADS};
        createNewsPage = new CreateNewsPage(driver);

        createNewsPage.selectTags(tagsList1, "en");
        for (NewsTags tag : tagsList1) {
            softAssert.assertEquals(createNewsPage.getTagButtonBackgroundColor(tag), Colors.PRIMARY_GREEN,
                    "Tag color mismatch when tag is selected for: " + tag);
        }

        createNewsPage.unSelectTags(tagsList1, "en");
        for (NewsTags tag : tagsList1) {
            softAssert.assertEquals(createNewsPage.getTagButtonBackgroundColor(tag), Colors.PRIMARY_WHITE,
                    "Tag color mismatch when tag is unselected for: " + tag);
        }

        createNewsPage.selectTags(tagsList2, "en");
        for (NewsTags tag : tagsList2) {
            softAssert.assertEquals(createNewsPage.getTagButtonBackgroundColor(tag), Colors.PRIMARY_GREEN,
                    "Tag color mismatch when tag is selected for: " + tag);
        }

        createNewsPage.unSelectTags(tagsList2, "en");
        for (NewsTags tag : tagsList2) {
            softAssert.assertEquals(createNewsPage.getTagButtonBackgroundColor(tag), Colors.PRIMARY_WHITE,
                    "Tag color mismatch when tag is unselected for: " + tag);
        }

        softAssert.assertAll();
    }
}
