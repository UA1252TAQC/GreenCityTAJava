package com.academy.ui;

import com.academy.ui.constants.NewsTags;
import com.academy.ui.pages.CreateNewsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import com.academy.ui.styles.ColorConstants;
import com.academy.utils.props.ConfigProperties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.swing.text.StyleConstants;

public class CreateNewsPageTest extends BaseTestRunner {
    ConfigProperties properties;
    private HomePage homePage;
    private CreateNewsPage createNewsPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUpPage() {
        softAssert = new SoftAssert();
        properties = new ConfigProperties();
        String email = properties.getUsername();
        String password = properties.getPassword();
        homePage = new HomePage(driver);
        homePage.openSignInFormInHeader()
                .enterPassword(password)
                .enterEmail(email)
                .submitForm()
                .headerComponent
                .navigateToNews()
                .goToCreateNews();
        createNewsPage = new CreateNewsPage(driver);
    }

    @Test
    public void selectUnSelectTags() {
        createNewsPage = new CreateNewsPage(driver);
        createNewsPage.chooseTag(NewsTags.NEWS, "en")
                .chooseTag(NewsTags.EDUCATION, "en")
                .chooseTag(NewsTags.EVENTS, "en");
        softAssert.assertEquals( createNewsPage.getTagButtonBackgroundColor(NewsTags.NEWS), ColorConstants.PRIMARY_GREEN);
        softAssert.assertEquals( createNewsPage.getTagButtonBackgroundColor(NewsTags.EDUCATION), ColorConstants.PRIMARY_GREEN);
        softAssert.assertEquals( createNewsPage.getTagButtonBackgroundColor(NewsTags.EVENTS), ColorConstants.PRIMARY_GREEN);

        createNewsPage.unSelectTag(NewsTags.NEWS, "en")
                .unSelectTag(NewsTags.EDUCATION, "en")
                .unSelectTag(NewsTags.EVENTS, "en");
        softAssert.assertEquals( createNewsPage.getTagButtonBackgroundColor(NewsTags.NEWS), ColorConstants.PRIMARY_WHITE);
        softAssert.assertEquals( createNewsPage.getTagButtonBackgroundColor(NewsTags.EDUCATION), ColorConstants.PRIMARY_WHITE);
        softAssert.assertEquals( createNewsPage.getTagButtonBackgroundColor(NewsTags.EVENTS), ColorConstants.PRIMARY_WHITE);

        createNewsPage.chooseTag(NewsTags.ADS, "en")
                .chooseTag(NewsTags.INITIATIVES, "en");
        softAssert.assertEquals( createNewsPage.getTagButtonBackgroundColor(NewsTags.ADS), ColorConstants.PRIMARY_GREEN);
        softAssert.assertEquals( createNewsPage.getTagButtonBackgroundColor(NewsTags.INITIATIVES), ColorConstants.PRIMARY_GREEN);

        createNewsPage.unSelectTag(NewsTags.ADS, "en")
                .unSelectTag(NewsTags.INITIATIVES, "en");
        softAssert.assertEquals( createNewsPage.getTagButtonBackgroundColor(NewsTags.NEWS), ColorConstants.PRIMARY_WHITE);
        softAssert.assertEquals( createNewsPage.getTagButtonBackgroundColor(NewsTags.EDUCATION), ColorConstants.PRIMARY_WHITE);


    }
}

