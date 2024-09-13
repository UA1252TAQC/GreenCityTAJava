package com.academy.ui.pages;

import com.academy.ui.constants.NewsTags;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class CreateNewsPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//app-tags-select//button/a")
    protected List<WebElement> tagsButton;

    @FindBy(xpath = "//textarea")
    @Getter
    private WebElement inputTitle;

    @FindBy(xpath = "//input[@formcontrolname='source']")
    @Getter
    private WebElement inputSource;

    @FindBy(xpath = "//div[@style='position: relative;']")
    @Getter
    private WebElement contentField;

    @FindBy(xpath = "//div[@class = 'submit-buttons']/button[2]")
    private WebElement previewButton;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewsPage chooseTag(NewsTags tag, String languageCode) {
        String tagText = tag.getText(languageCode);
        for (WebElement tagButton : tagsButton) {
            if (tagButton.getText().equalsIgnoreCase(tagText)) {
                tagButton.click();
                break;
            }
        }
        return this;
    }

    public List<WebElement> getTagsButton() {
        return tagsButton;
    }

    public CreateNewsPage enterTitle(String title) {
        inputTitle.sendKeys(title);
        return this;
    }

    public CreateNewsPage enterSource(String source) {
        inputSource.sendKeys(source);
        return this;
    }

    public CreateNewsPage enterDescription(String description) {
        WebElement quillEditor = driver.findElement(By.cssSelector(".ql-editor"));
        quillEditor.sendKeys(description);
        return this;
    }

    public CreateNewsPage selectTags(NewsTags[] tags, String languageCode) {
        for (NewsTags tag : tags) {
            chooseTag(tag, languageCode);
        }
        return this;
    }

    public String getTitleText() {
        return inputTitle.getAttribute("value");
    }

    public String getSourceText() {
        return inputSource.getAttribute("value");
    }

    public String getDescriptionText() {
        return contentField.getAttribute("value");
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

    public PreviewPage clickPreviewButton() {
        click(previewButton);
        return new PreviewPage(driver);
    }

}
