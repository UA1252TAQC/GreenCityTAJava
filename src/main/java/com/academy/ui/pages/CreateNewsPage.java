package com.academy.ui.pages;

import com.academy.ui.constants.NewsTags;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CreateNewsPage extends BasePage {

    @FindBy(how = How.XPATH, using = "./button[class='tag-button']/a")
    protected List<WebElement> tagsButton;

    public CreateNewsPage (WebDriver driver) {
        super(driver);
    }

    public void chooseTag(NewsTags tag){

    }

    public List<WebElement> getTagsButton(){
        return tagsButton;
    }

}
