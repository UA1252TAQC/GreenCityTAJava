package com.academy.ui.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewsPreviewPage extends BasePage {
  @FindBy(how = How.XPATH, using = "//img[contains(@class, 'news-image-img')]")
  protected WebElement previewPage;
  public NewsPreviewPage(WebDriver driver) {
    super(driver);
  }
  public boolean isPreviewPageDisplayed() {
    return isDisplayed(previewPage);
  }

}
