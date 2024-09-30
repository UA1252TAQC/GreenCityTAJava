package com.academy.ui.pages.greenCity;

import com.academy.ui.constants.NewsTags;

import java.util.ArrayList;
import java.util.List;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CreateNewsPage extends BasePageGreenCity {

    private static final String CLOSE_TAG_BUTTON_XPATH = "//div[contains(@class, 'global-tag-close-icon')]";
    private static final String ADD_IMG_LINK_XPATH = "//div[contains(@class, 'dropzone')]//span";
    private static final String SUBMIT_IMG_BUTTON_XPATH = "//div[contains(@class, 'cropper-buttons')]//button[2]";

    @FindBy(xpath = "//textarea[@formcontrolname='title']")
    protected WebElement newsTitle;
    @FindBy(xpath = "//div[@class='ql-editor ql-blank']")
    protected WebElement newsContent;
    @FindBy(xpath = "//div[contains(@class, 'left-form-column')]//label/input")
    protected WebElement sourceLinkField;
    @FindBy(xpath = "//app-tags-select//button/a")
    protected List<WebElement> tagsButton;
    @FindBy(xpath = "//button[contains(@class, 'secondary-global-button')]")
    protected WebElement newsPreviewButton;
    @FindBy(xpath = "//div[contains(@class, 'submit-buttons')]//button[@type='submit']")
    protected WebElement newsPublishButton;
    @FindBy(xpath = "//div[@class='text-wrapper']")
    protected WebElement newsPhoto;
    @FindBy(xpath = "(//div[@class='container'])[2]")
    protected WebElement newsIsLoadingMessage;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select the tag {tag}")
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

    @Step("Fill the Create News form with the title: {title}, content: {content}, and the list of tags: {tags}.")
    public CreateNewsPage fillTheNewsForm(String title, NewsTags[] tags, String content,
        String language) {
        newsTitle.sendKeys(title);
        selectTags(tags, language);
        newsContent.sendKeys(content);
        return this;
    }
    @Step("Fill the source link field with {link}")
    public CreateNewsPage enterSourceLink(String link) {
        sourceLinkField.sendKeys(link);
        return this;
    }

    @Step("Select the tags: {tags}")
    public CreateNewsPage selectTags(NewsTags[] tags, String languageCode) {
        for (NewsTags tag : tags) {
            selectSingleTag(tag, languageCode);
        }
        return this;
    }

    @Step("Unselect the tag: {tag}")
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

    @Step("Unselect the tags list: {tags}")
    public CreateNewsPage unSelectTags(NewsTags[] tags, String languageCode) {
        for (NewsTags tag : tags) {
            unSelectSingleTag(tag, languageCode);
        }
        return this;
    }

    @Step("Get the background color of the {tag} button")
    public String getTagButtonBackgroundColor(NewsTags tag) {
        for (WebElement tagButton : tagsButton) {
            if (tagButton.getText().equalsIgnoreCase(tag.getText("en"))
                || tagButton.getText().equalsIgnoreCase(tag.getText("ua"))) {
                return tagButton.getCssValue("background-color");
            }
        }
        return null;
    }

    @Step("Verify if the newsPreviewButton is enabled")
    public boolean newsPreviewButtonIsEnabled() {
        return isEnabled(newsPreviewButton);
    }

    @Step("Verify if the newsPublishButton is enabled")
    public boolean newsPublishButtonIsEnabled() {
        return isEnabled(newsPublishButton);
    }

    @Step("Click the newsPublishButton")
    public NewsPage clickPublishButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        click(newsPublishButton);
        return new NewsPage(driver);
    }

    @Step("Click the newsPreviewButton")
    public NewsPreviewPage clickPreviewButton() {
        click(newsPreviewButton);
        return new NewsPreviewPage(driver);
    }

    @Step("Check if the tag {tag} is selected")
    private boolean isTagSelected(WebElement tagButton) {
        return tagButton.getAttribute("class").contains("global-tag-clicked");
    }

    @Step("Get all selected tag")
    public List<WebElement> getSelectedTags() {
        List<WebElement> selectedTags = new ArrayList<>();
        for (WebElement tagButton : tagsButton) {
            if (isTagSelected(tagButton)) {
                selectedTags.add(tagButton);
            }
        }
        return selectedTags;
    }

    @Step("Get news title text")
    public String getTitleText() {
        return newsTitle.getAttribute("value");
    }

    @Step("Get news loading message text")
    public String getNewsLoadingMessage() {
        return newsIsLoadingMessage.getText();
    }

    @Step("Get news content text")
    public String getContentText() {
        WebElement editor = driver.findElement(By.cssSelector("quill-editor .ql-editor"));
        return (String) ((JavascriptExecutor) driver).executeScript(
            "return arguments[0].innerText;", editor);
    }

    @Step("Check if the title field is displayed")
    public boolean isTitleFieldAppeared() {
        return isDisplayed(newsTitle);
    }

    @Step("Check if the content field is displayed")
    public boolean isContentFieldAppeared() {
        return isDisplayed(newsContent);
    }

    @Step("Check if the source field is displayed")
    public boolean isSourceFieldAppeared() {
        return isDisplayed(sourceLinkField);
    }

    @Step("Check if the photo field is displayed")
    public boolean isPhotoFieldAppeared() {
        return isDisplayed(newsPhoto);
    }

    @Step("Upload an image from the path {path}")
    public CreateNewsPage addImage(String path) {
        String filePath = System.getProperty("user.dir") + path;
        click(findWithWaitElement(ADD_IMG_LINK_XPATH ,10));

        try {
            uploadFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        click(findWithWaitElement(SUBMIT_IMG_BUTTON_XPATH,10));
        return this;
    }

}