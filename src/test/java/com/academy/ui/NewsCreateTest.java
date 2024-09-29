package com.academy.ui;

import com.academy.ui.constants.NewsTags;
import com.academy.ui.providers.CreateNewsProvider;
import com.academy.ui.runners.TestRunnerMethodInitDriverLoginCreateNews;
import com.academy.ui.styleConstants.Colors;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
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
    private static final String EXPECTED_NEWS_LOADING_MESSAGE = "Please wait while loading...\nYour news is loading to website. Please wait until page refreshes.";


    @Test(dataProvider = "tagsListSelect", dataProviderClass = CreateNewsProvider.class)
    @Description("Check if the user can select or unselect tags while creating news and whether the tag's style is updated accordingly.")
    @Feature("CreateNews")
    @Issue("97")
    public void selectUnSelectTags(NewsTags[] tagsList1, NewsTags[] tagsList2) {

        createNewsPage.selectTags(tagsList1, "en");
        for (NewsTags tag : tagsList1) {
            softAssert.assertEquals(createNewsPage.getTagButtonBackgroundColor(tag),
                Colors.PRIMARY_GREEN,
                "Tag color mismatch when tag is selected for: " + tag);
        }

        createNewsPage.unSelectTags(tagsList1, "en");
        for (NewsTags tag : tagsList1) {
            softAssert.assertEquals(createNewsPage.getTagButtonBackgroundColor(tag),
                Colors.PRIMARY_WHITE,
                "Tag color mismatch when tag is unselected for: " + tag);
        }

        createNewsPage.selectTags(tagsList2, "en");
        for (NewsTags tag : tagsList2) {
            softAssert.assertEquals(createNewsPage.getTagButtonBackgroundColor(tag),
                Colors.PRIMARY_GREEN,
                "Tag color mismatch when tag is selected for: " + tag);
        }

        createNewsPage.unSelectTags(tagsList2, "en");
        for (NewsTags tag : tagsList2) {
            softAssert.assertEquals(createNewsPage.getTagButtonBackgroundColor(tag),
                Colors.PRIMARY_WHITE,
                "Tag color mismatch when tag is unselected for: " + tag);
        }

        softAssert.assertAll();
    }

    @Test(dataProvider = "validData", dataProviderClass = CreateNewsProvider.class)
    @Description("Verify that the user can go back to editing news by following the ‘Back to editing’ link")
    @Feature("CreateNews")
    @Issue("122")
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
    @Description("Verify that the user can publish news after clicking the 'Publish' button")
    @Feature("CreateNews")
    @Issue("78")
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
    @Description("Verify that news will be created when the user puts 170 characters in the ‘Title’ field")
    @Feature("CreateNews")
    @Issue("115")
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
    @Description("Verify that Author (registered User nickname) is Auto-filled")
    @Feature("CreateNews")
    @Issue("77")
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
    @Description("Verify that the user can create news with a source link.")
    @Feature("CreateNews")
    @Issue("93")
    public void createNewsWithSourceLink(String title, NewsTags[] tags, String content,
        String sourceLink) {
        createNewsPage.fillTheNewsForm(title, tags, content, "en")
            .enterSourceLink(sourceLink)
            .clickPublishButton();
        boolean isNewsAdded = newsPage.isNewsDisplayed(title, content, Arrays.asList(tags));
        softAssert.assertTrue(isNewsAdded);
        softAssert.assertAll();
    }

    @Test(dataProvider = "validDataSourceLink", dataProviderClass = CreateNewsProvider.class)
    @Description("Ensure that the user is able to create news without image.")
    @Feature("CreateNews")
    @Issue("75")
    public void createNewsWithoutImg(String title, NewsTags[] tags, String content,
        String sourceLink) {
        createNewsPage.fillTheNewsForm(title, tags, content, "en")
            .clickPublishButton();
        boolean isNewsAdded = newsPage.isNewsDisplayed(title, content, Arrays.asList(tags));
        softAssert.assertTrue(isNewsAdded);
        softAssert.assertAll();
    }

    @Test
    @Description("Verify that the ‘Create News’ form appears after clicking on the ‘Create news’ button")
    @Feature("CreateNews")
    @Issue("74")
    public void isCreateNewsFormAppear() {
        softAssert.assertEquals(createNewsPage.isTitleFieldAppeared(), true);
        softAssert.assertEquals(createNewsPage.isContentFieldAppeared(), true);
        softAssert.assertEquals(createNewsPage.isSourceFieldAppeared(), true);
        softAssert.assertEquals(createNewsPage.isPhotoFieldAppeared(), true);
    }

    @Test
    @Description("Verify that after clicking the 'Publish' button, a message that the News is loading is displayed on the web-site")
    @Feature("CreateNews")
    @Issue("118")
    public void isNewsLoadingMessageAppear() {
        createNewsPage
            .fillTheNewsForm(NEWS_TITLE, new NewsTags[]{NewsTags.EVENTS},
                NEWS_CONTENT, "en")
            .clickPublishButton();

        String loadingMessage = createNewsPage.getNewsLoadingMessage();
        softAssert.assertEquals(loadingMessage, EXPECTED_NEWS_LOADING_MESSAGE);
        softAssert.assertAll();
    }

    @Test(dataProvider = "validData", dataProviderClass = CreateNewsProvider.class)
    @Description("Ensure that the user is able to create news with content exactly 63,206 characters in length.")
    @Feature("CreateNews")
    @Issue("95")
    public void createNewsWithContentLenght(String title, NewsTags[] tags,String content){
        String str = "a".repeat(63206);
        createNewsPage.fillTheNewsForm(title, tags, str, "en")
                .clickPublishButton();
        boolean isNewsAdded = newsPage.isNewsDisplayed(title,content, Arrays.asList(tags));
        softAssert.assertTrue(isNewsAdded);
        softAssert.assertAll();
    }

    @Test(dataProvider = "validData", dataProviderClass = CreateNewsProvider.class)
    @Description("Verify that news will be created when the user adds the PNG image less than 10 MB")
    @Feature("CreateNews")
    @Issue("91")
    public void createNewsWithPngImage(String title, NewsTags[] tags,String content){
        createNewsPage.fillTheNewsForm(title, tags, content, "en")
                .addImage("/src/test/resources/img/fruit-1218166_1280.png")
                .clickPublishButton();
        boolean isNewsAdded = newsPage.isNewsDisplayed(title,content, Arrays.asList(tags));
        softAssert.assertTrue(isNewsAdded);
        softAssert.assertAll();
    }

    @Test(dataProvider = "validData", dataProviderClass = CreateNewsProvider.class)
    @Description("Verify that news will be created when the user adds the JPG image less than 10 MB")
    @Feature("CreateNews")
    @Issue("80")
    public void createNewsWithJpegImage(String title, NewsTags[] tags,String content){
        createNewsPage.fillTheNewsForm(title, tags, content, "en")
                .addImage("/src/test/resources/img/1700488940348.jpeg")
                .clickPublishButton();
        boolean isNewsAdded = newsPage.isNewsDisplayed(title,content, Arrays.asList(tags));
        softAssert.assertTrue(isNewsAdded);
        softAssert.assertAll();
    }

}