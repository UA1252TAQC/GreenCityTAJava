package com.academy.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewsPage extends BasePage {

  @FindBy(how = How.XPATH, using = "//div[@id='create-button' and contains(@class, 'secondary-global-button')]/span[text()='Create news']")
  protected WebElement createNewsButton;


  public NewsPage(WebDriver driver) {
    super(driver);
  }

  public CreateNewsPage goToCreateNews() {
    click(createNewsButton);
    return new CreateNewsPage(driver);
  }


}

