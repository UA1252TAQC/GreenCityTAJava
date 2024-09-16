package com.academy.ui.pages.greenCity;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class CreateNewsPage extends BasePageGreenCity {
    @FindBy(xpath = ".//button[class='tag-button']")
    protected List<WebElement> tagsButton;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
    }
}
