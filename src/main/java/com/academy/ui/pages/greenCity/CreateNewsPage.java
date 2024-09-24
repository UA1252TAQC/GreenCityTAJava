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

    public CreateNewsPage enterSourceLink(String content) {
        sourceLinkField.sendKeys(content);
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
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        click(newsPublishButton);
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

    public String getNewsLoadingMessage() {
        return newsIsLoadingMessage.getText();
    }

    public String getContentText() {
        WebElement editor = driver.findElement(By.cssSelector("quill-editor .ql-editor"));
        return (String) ((JavascriptExecutor) driver).executeScript(
            "return arguments[0].innerText;", editor);
    }

    public boolean isTitleFieldAppeared() {
        return isDisplayed(newsTitle);
    }

    public boolean isContentFieldAppeared() {
        return isDisplayed(newsContent);
    }

    public boolean isSourceFieldAppeared() {
        return isDisplayed(sourceLinkField);
    }

    public boolean isPhotoFieldAppeared() {
        return isDisplayed(newsPhoto);
    }

    public CreateNewsPage addImage(String path) {
        String filePath = System.getProperty("user.dir") + path;
        click(findWithWaitElement(ADD_IMG_LINK_XPATH ,10));

        try {
            uploadFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        findWithWaitElement(SUBMIT_IMG_BUTTON_XPATH,10);
        click(findWithWaitElement(SUBMIT_IMG_BUTTON_XPATH,10));
        return this;
    }

}