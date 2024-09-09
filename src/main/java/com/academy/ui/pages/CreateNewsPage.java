package com.academy.ui.pages;

import com.academy.ui.constants.NewsTags;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateNewsPage extends BasePage {

  @FindBy(how = How.XPATH, using = "./button[class='tag-button']/a")
  protected List<WebElement> tagsButton;
  @FindBy(how = How.XPATH, using = "//textarea[@formcontrolname='title']")
  protected WebElement newsTitle;
  @FindBy(how = How.XPATH, using = "//button[contains(@class, 'tag-button')]//span[text()='News']")
  protected WebElement newsTag;
  @FindBy(how = How.XPATH, using = "//div[@class='ql-editor ql-blank']")
  protected WebElement newsContent;
  @FindBy(how = How.XPATH, using = "//button[contains(@class, 'secondary-global-button')]")
  protected WebElement newsPreviewButton;

  public CreateNewsPage(WebDriver driver) {
    super(driver);
  }

  public void chooseTag(NewsTags tag) {

  }

  public List<WebElement> getTagsButton() {
    return tagsButton;
  }


  public CreateNewsPage fillTheNewsForm(String title, String content) {
    newsTitle.sendKeys(title);
    newsTag.click();
    newsContent.sendKeys(content);

    return this;
  }

  public boolean waitForPreviewButton() {
    findWithWaitElement("//button[contains(@class, 'secondary-global-button')]");
    return isEnabled(newsPreviewButton);
  }

  public NewsPreviewPage goToNewsPreviewPage() {
    click(newsPreviewButton);
    return new NewsPreviewPage(driver);
  }

}
