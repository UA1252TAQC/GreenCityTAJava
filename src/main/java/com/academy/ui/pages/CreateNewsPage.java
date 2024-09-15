package com.academy.ui.pages;

import com.academy.ui.constants.NewsTags;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

@Getter
public class CreateNewsPage extends BasePageGreenCity {

    private static final String CLOSE_TAG_BUTTON_XPATH = "//div[contains(@class, 'global-tag-close-icon')]";

    @FindBy(how = How.XPATH, using = "//textarea[@formcontrolname='title']")
    protected WebElement newsTitle;

    @FindBy(how = How.XPATH, using = "//div[@class='ql-editor ql-blank']")
    protected WebElement newsContent;

    @FindBy(how = How.XPATH, using = "//app-tags-select//button/a")
    protected List<WebElement> tagsButton;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewsPage selectSingleTag(NewsTags tag, String languageCode){
        String tagText = tag.getText(languageCode);
        for (WebElement tagButton : tagsButton) {
            if (tagButton.getText().equalsIgnoreCase(tagText)) {
                tagButton.click();
                break;
            }
        }
        return this;
    }

    public CreateNewsPage fillTheNewsForm(String title, NewsTags[] tags, String content, String language) {
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

    public CreateNewsPage unSelectSingleTag(NewsTags tag, String languageCode){
        String tagText = tag.getText(languageCode);
        for (WebElement tagButton : tagsButton) {
            if (tagButton.getText().equalsIgnoreCase(tagText)) {
                tagButton.findElement(By.xpath(CLOSE_TAG_BUTTON_XPATH)).click();
                break;
            }
        }
        return this;
    }

    public CreateNewsPage unSelectTags(NewsTags[] tags, String languageCode){
        for (NewsTags tag : tags) {
            unSelectSingleTag(tag, languageCode);
        }
        return this;
    }

    public String getTagButtonBackgroundColor(NewsTags tag){
        for (WebElement tagButton : tagsButton) {
            if (tagButton.getText().equalsIgnoreCase(tag.getText("en"))
                    || tagButton.getText().equalsIgnoreCase(tag.getText("ua"))) {
                return tagButton.getCssValue("background-color");
            }
        }
        return null;
    }
}
