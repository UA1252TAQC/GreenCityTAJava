package com.academy.ui;

import com.academy.ui.components.SignInComponent;
import com.academy.ui.pages.CreateNewsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.pages.NewsPage;
import com.academy.ui.pages.NewsPreviewPage;
import com.academy.ui.runners.FormTestRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EcoNewsPreviewTest extends FormTestRunner {

  private static final String NEWS_TITLE = "Workshop to educate your customers about eco-friendly living";
  private static final String NEWS_CONTENT = "Workshop to educate your customers about eco-friendly living";

  private SignInComponent form;
  private HomePage page;
  private NewsPage newsPage;
  private CreateNewsPage createNewsPage;
  private NewsPreviewPage newsPreviewPage;
  private SoftAssert softAssert;

  @BeforeClass
  @Parameters({"language"})
  public void setUp(@Optional("en") String language) {
    page = new HomePage(driver).setLanguage(language);
  }

  @BeforeMethod
  public void setUpMethod() {
    newsPage = new NewsPage(driver);
    form = page.openSignInFormInHeader();
    form.fillTheSignInForm(configProperties.getEmail(), configProperties.getPassword())
        .submitSignInForm();
    softAssert = new SoftAssert();
  }

  @Test
  public void ecoNewsPreview() {
    createNewsPage = new CreateNewsPage(driver);
    newsPreviewPage = new NewsPreviewPage(driver);
    page.waitForNewsButton();
    newsPage.goToCreateNews()
        .fillTheNewsForm(NEWS_TITLE, NEWS_CONTENT);
    boolean isPreviewButtonEnabled = createNewsPage.waitForPreviewButton();
    softAssert.assertTrue(isPreviewButtonEnabled);
    createNewsPage.goToNewsPreviewPage();
    boolean isPreviewPageDisplayed = newsPreviewPage.isPreviewPageDisplayed();
    softAssert.assertTrue(isPreviewPageDisplayed);
    softAssert.assertAll();
  }

}


