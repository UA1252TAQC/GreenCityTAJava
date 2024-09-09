package com.academy.ui.components;

import com.academy.ui.pages.CreateNewsPage;
import com.academy.ui.pages.NewsPage;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderComponent extends BaseComponent {
  private final By newsTagLocator = By.xpath(
      "//button[contains(@class, 'tag-button')]//span[text()='News']");
  @FindBy(how = How.XPATH, using = "//button[contains(@class, 'tag-button')]//span[text()='News']")
  protected WebElement newsTagLocator2 ;
  @FindBy(how = How.XPATH, using = ".//[@class='header_logo']")
  protected WebElement logo;

  @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[1]/a")
  protected WebElement news;

  @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[2]/a")
  protected WebElement places;

  @FindBy(how = How.XPATH, using = ".//div[@class='header_navigation-menu']//li[3]/a")
  protected WebElement profile;

  @FindBy(how = How.XPATH, using = ".//ul[@class='header_lang-switcher-wrp header_navigation-menu-right-lang']")
  protected WebElement listLanguage;

  @FindBy(how = How.XPATH, using = "//li[@role='menuitem' and @aria-label='En']/span[text()='En']")
  protected WebElement english;

  @FindBy(how = How.XPATH, using = ".//li[@class='header_sign-up-link']//span")
  protected WebElement register;
  @FindBy(how = How.XPATH, using = "//a[contains(@class, 'header_sign-in-link')]")
  protected WebElement signInHomeButton;

  public HeaderComponent(WebDriver driver, WebElement rootElement) {
    super(driver, rootElement);
  }

  public RegistrationComponent openRegistrationForm() {
    click(register);
    return new RegistrationComponent(driver, findElement(".//app-auth-modal"));
  }

  public SignInComponent openSignInForm() {
    click(signInHomeButton);
    return new SignInComponent(driver, findElement(".//app-auth-modal[@class='ng-star-inserted']"));
  }

  public HeaderComponent openEcoNewsTab() {
    click(news);
    return new HeaderComponent(driver, findElement(".//main[@id='main-content' and @aria-label='news list']//div[@class='container']"));
  }

  public HeaderComponent changeLanguage(String language) {
    if (language.equals("en")) {
      click(this.listLanguage);
      click(this.english);
    }
    return this;
  }



//  private void retryClick(By locator, Duration timeout) {
//    WebDriverWait wait = new WebDriverWait(driver, timeout);
//    int retries = 0;
//
//    while (retries < 3) {
//      try {
//        // Wait for the element to be present and clickable
//        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//        wait.until(ExpectedConditions.elementToBeClickable(element));
//
//        // Click the element
//        element.click();
//        return;
//      } catch (StaleElementReferenceException e) {
//        retries++;
//        System.out.println(
//            "StaleElementReferenceException encountered. Retrying... attempt " + retries);
//      }
//    }
//    throw new RuntimeException(
//        "Failed to click element after multiple retries due to stale element.");
//  }

}
