package com.academy.ui.components;

import com.academy.ui.Base;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

@Getter
public class BaseComponent extends Base {

  protected final WebElement rootElement;

  public BaseComponent(WebDriver driver, WebElement rootElement) {
    super(driver);
    this.rootElement = rootElement;
    PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
  }
}
