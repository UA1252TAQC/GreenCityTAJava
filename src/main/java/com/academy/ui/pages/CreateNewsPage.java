package com.academy.ui.pages;

import com.academy.ui.constants.NewsTags;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CreateNewsPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'tag-button')]//span[text()='News']")
    protected WebElement tagsButtons;

    public CreateNewsPage (WebDriver driver) {
        super(driver);
    }

    public CreateNewsPage chooseTag(NewsTags tag, String language){
        findWithWaitElement("//button[contains(@class, 'tag-button')]//span[text()='"+tag.getText(language)+"']").click();
        return this;
    }

    public CreateNewsPage unSelectTag(NewsTags tag, String language){
        findWithWaitElement("//a[.//span[text()='"+tag.getText(language)+"']]//div[contains(@class, 'global-tag-close-icon')]").click();
        return this;
    }

    public String getTagButtonBackgroundColor(NewsTags tag,String lang){
        return findElement("//button[contains(@class, 'tag-button')]//span[text()='"+tag.getText(lang)+"']")
                .getCssValue("background-color");

    }

    public  String getTagButtonBackgroundColor(NewsTags tag){
        return getTagButtonBackgroundColor(tag,"en");
    }

}
