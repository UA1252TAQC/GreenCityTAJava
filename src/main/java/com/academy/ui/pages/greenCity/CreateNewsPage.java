package com.academy.ui.pages.greenCity;

import com.academy.ui.constants.NewsTags;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Getter
public class CreateNewsPage extends BasePageGreenCity {

    private static final String CLOSE_TAG_BUTTON_XPATH = "//div[contains(@class, 'global-tag-close-icon')]";

    @FindBy(how = How.XPATH, using = "//textarea[@formcontrolname='title']")
    protected WebElement newsTitle;
    @FindBy(how = How.XPATH, using = "//div[@class='ql-editor ql-blank']")
    protected WebElement newsContent;
    @FindBy(how = How.XPATH, using = "//app-tags-select//button/a")
    protected List<WebElement> tagsButton;
    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'secondary-global-button')]")
    protected WebElement newsPreviewButton;
    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'primary-global-button')]")
    protected WebElement newsPublishButton;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewsPage selectSingleTag(NewsTags tag, String languageCode) {
        String tagText = tag.getText(languageCode);
        for (WebElement tagButton : tagsButton) {
            if (tagButton.getText().equalsIgnoreCase(tagText)) {
                tagButton.click();
                break;
            }
        }
        return this;
    }

    public CreateNewsPage fillTheNewsForm(String title, NewsTags[] tags, String content,
        String language) {
        newsTitle.sendKeys(title);
        selectTags(tags, language);
        newsContent.sendKeys(content);
        return this;
    }


    public CreateNewsPage selectTags(NewsTags[] tags, String languageCode) {
        for (NewsTags tag : tags) {
            selectSingleTag(tag, languageCode);
        }
        return this;
    }

    public CreateNewsPage unSelectSingleTag(NewsTags tag, String languageCode) {
        String tagText = tag.getText(languageCode);
        for (WebElement tagButton : tagsButton) {
            if (tagButton.getText().equalsIgnoreCase(tagText)) {
                tagButton.findElement(By.xpath(CLOSE_TAG_BUTTON_XPATH)).click();
                break;
            }
        }
        return this;
    }

    public CreateNewsPage unSelectTags(NewsTags[] tags, String languageCode) {
        for (NewsTags tag : tags) {
            unSelectSingleTag(tag, languageCode);
        }
        return this;
    }

    public String getTagButtonBackgroundColor(NewsTags tag) {
        for (WebElement tagButton : tagsButton) {
            if (tagButton.getText().equalsIgnoreCase(tag.getText("en"))
                || tagButton.getText().equalsIgnoreCase(tag.getText("ua"))) {
                return tagButton.getCssValue("background-color");
            }
        }
        return null;
    }

    public boolean newsPreviewButtonIsEnabled() {
        return isEnabled(newsPreviewButton);
    }

    public boolean newsPublishButtonIsEnabled() {
        return isEnabled(newsPublishButton);
    }

    public NewsPage clickPublishButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", newsPublishButton);
        return new NewsPage(driver);
    }

    public NewsPreviewPage clickPreviewButton() {
        click(newsPreviewButton);
        return new NewsPreviewPage(driver);
    }

    private boolean isTagSelected(WebElement tagButton) {
        return tagButton.getAttribute("class").contains("global-tag-clicked");
    }

    public List<WebElement> getSelectedTags() {
        List<WebElement> selectedTags = new ArrayList<>();
        for (WebElement tagButton : tagsButton) {
            if (isTagSelected(tagButton)) {
                selectedTags.add(tagButton);
            }
        }
        return selectedTags;
    }

    public String getTitleText() {
        return newsTitle.getAttribute("value");
    }

    public String getContentText() {
        WebElement editor = driver.findElement(By.cssSelector("quill-editor .ql-editor"));
        return (String) ((JavascriptExecutor) driver).executeScript(
            "return arguments[0].innerText;", editor);
    }

}
