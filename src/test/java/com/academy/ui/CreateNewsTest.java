package com.academy.ui;

import com.academy.ui.constants.NewsTags;
import com.academy.ui.pages.CreateNewsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.pages.PreviewPage;
import com.academy.ui.providers.CreateNewsProvider;
import com.academy.ui.runners.BaseTestRunner;
import com.academy.utils.props.ConfigProperties;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CreateNewsTest extends BaseTestRunner {
    private HomePage homePage;
    private CreateNewsPage createNewsPage;
    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);
        ConfigProperties configProperties = new ConfigProperties();
        String email = configProperties.getBaseEmail();
        String password = configProperties.getBasePassword();
        homePage.switchLanguage("en")
                .openLoginFormInHeader()
                .fillForm(email, password)
                .openNewsInHeader()
                .goToCreateNews();
        createNewsPage = new CreateNewsPage(driver);
    }
    @Test(dataProvider = "validData", dataProviderClass = CreateNewsProvider.class)
    public void firstTest(String title, NewsTags[] tagNames, String description) {
        createNewsPage.enterTitle(title)
                .enterDescription(description)
                .selectTags(tagNames, "en");

        String titleBefore = createNewsPage.getTitleText();
        String descriptionBefore = createNewsPage.getDescriptionText();
        List<WebElement> selectedTagsBefore = createNewsPage.getSelectedTags();

        createNewsPage.clickPreviewButton().clickBackToEditing();

        String titleAfter = createNewsPage.getTitleText();
        String descriptionAfter = createNewsPage.getDescriptionText();
        List<WebElement> selectedTagsAfter = createNewsPage.getSelectedTags();

        Assert.assertEquals(titleBefore, titleAfter);
        Assert.assertEquals(descriptionBefore, descriptionAfter);
        Assert.assertEquals(selectedTagsBefore, selectedTagsAfter);
    }
}
