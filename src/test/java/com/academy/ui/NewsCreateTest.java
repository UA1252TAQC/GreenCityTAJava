package com.academy.ui;

import com.academy.ui.constants.NewsTags;
import com.academy.ui.providers.CreateNewsProvider;
import com.academy.ui.runners.TestRunnerMethodInitDriverLoginCreateNews;
import com.academy.ui.styleConstants.Colors;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.Test;

public class NewsCreateTest extends TestRunnerMethodInitDriverLoginCreateNews {

    private static final String NEWS_TITLE = "Workshop to educate your customers about eco-friendly living";
    private static final String NEWS_CONTENT = "Workshop to educate your customers about eco-friendly living";
    private static final String NEWS_TITLE_LENGTH = "Celebrating World Water Day: The Coral Triangle. Water benefits not only humans but all animals and natural life that exists above and below the surface. Water benefits!!";
    private static final String NEWS_CONTENT_LENGTH = "Celebrating World Water Day: The Coral Triangle";
    private static final String NEWS_TITLE_AUTHOR = "Coffee takeaway with 40% discount";
    private static final String NEWS_CONTENT_AUTHOR = "It's so healthy, fun and cool to bring eco habits into everyday life";


    @Test(dataProvider = "tagsListSelect", dataProviderClass = CreateNewsProvider.class)
    public void selectUnSelectTags(NewsTags[] tagsList1, NewsTags[] tagsList2) {

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

    @Test(dataProvider = "validData", dataProviderClass = CreateNewsProvider.class)
    public void testBackToEditing(String title, NewsTags[] tagNames, String description) {
        createNewsPage.fillTheNewsForm(title, tagNames, description, "en");

        String titleBefore = createNewsPage.getTitleText();
        String descriptionBefore = createNewsPage.getContentText();
        List<WebElement> selectedTagsBefore = createNewsPage.getSelectedTags();

        createNewsPage.clickPreviewButton().clickBackToEditing();

        String titleAfter = createNewsPage.getTitleText();
        String descriptionAfter = createNewsPage.getContentText();
        List<WebElement> selectedTagsAfter = createNewsPage.getSelectedTags();

        Assert.assertEquals(titleBefore, titleAfter);
        Assert.assertEquals(descriptionBefore, descriptionAfter);
        Assert.assertEquals(selectedTagsBefore, selectedTagsAfter);
    }

    @Test
    public void publishNews() {
        createNewsPage
            .fillTheNewsForm(NEWS_TITLE, new NewsTags[]{NewsTags.EVENTS}, NEWS_CONTENT, "en");

        boolean isPublishButtonEnabled = createNewsPage.newsPublishButtonIsEnabled();
        softAssert.assertTrue(isPublishButtonEnabled);
        createNewsPage.clickPublishButton();

        boolean isNewsDisplayed = newsPage.isNewsDisplayedWithTitle(NEWS_TITLE);
        softAssert.assertTrue(isNewsDisplayed);

        softAssert.assertAll();

    }

    @Test
    public void checkLengthTitle() {
        createNewsPage
            .fillTheNewsForm(NEWS_TITLE_LENGTH, new NewsTags[]{NewsTags.EVENTS},
                NEWS_CONTENT_LENGTH, "en")
            .clickPublishButton();

        boolean isNewsDisplayed = newsPage.isNewsDisplayedWithTitle(NEWS_TITLE_LENGTH);
        softAssert.assertTrue(isNewsDisplayed);

        softAssert.assertAll();
    }

    @Test
    public void AuthorFieldIsAutofilled() {
        createNewsPage
            .fillTheNewsForm(NEWS_TITLE_AUTHOR, new NewsTags[]{NewsTags.EVENTS},
                NEWS_CONTENT_AUTHOR, "en")
            .clickPublishButton()
            .clickOnNewsWithTitle(NEWS_TITLE_AUTHOR);

        String actualAuthorName = newsPage.getAuthorName();
        softAssert.assertEquals(actualAuthorName, "by " + configProperties.getRegisteredUserName());
        softAssert.assertAll();
    }

    @Test(dataProvider = "validDataSourceLink", dataProviderClass = CreateNewsProvider.class)
    public void createNewsWithSourceLink(String title, NewsTags[] tags,String content,String sourceLink){
        createNewsPage.fillTheNewsForm(title,tags,content,sourceLink)
                .enterSourceLink(sourceLink)
                .clickPublishButton();
        boolean isNewsAdded = newsPage.isNewsDisplayed(title,content, Arrays.asList(tags));
        softAssert.assertTrue(isNewsAdded);
        softAssert.assertAll();
    }

}