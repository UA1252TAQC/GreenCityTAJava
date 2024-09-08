package com.academy.ui.pages;

import com.academy.ui.Base;
import com.academy.ui.components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Base {

    @FindBy(how = How.CSS, using = ".header-container")
    protected WebElement headerSection;

    private HeaderComponent headerComponent;

    public BasePage(WebDriver driver) {
        super(driver);
        this.headerComponent = new HeaderComponent(driver, headerSection);
    }

    public void openURL(String url){
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    public SignInModal openSignInModal() {
        headerComponent.clickSignInTab();
        return new SignInModal(driver);
    }

    public String getHeaderProfileLinkText() {
        return headerComponent.getProfileLinkText();
    }
}
