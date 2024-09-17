package com.academy.ui;

import com.academy.ui.constants.NewsTags;
import com.academy.ui.pages.greenCity.CreateNewsPage;
import com.academy.ui.providers.CreateNewsProvider;
import com.academy.ui.runners.TestRunnerMethodInitDriverLoginCreateNews;
import com.academy.ui.styleConstants.Colors;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class NewsCreateTest extends TestRunnerMethodInitDriverLoginCreateNews {

    @Test(dataProvider = "tagsListSelect", dataProviderClass = CreateNewsProvider.class)
    public void selectUnSelectTags(NewsTags[] tagsList1, NewsTags[] tagsList2) {
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

}