package com.academy.ui.pages;

import com.academy.ui.Base;
import com.academy.ui.components.HeaderComponent;
import com.academy.ui.components.SignInComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Base {

    @FindBy(how = How.CSS, using = ".header_container")
    protected WebElement headerComponentRoot;

    private HeaderComponent headerComponent;

    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.headerComponent = new HeaderComponent(driver, headerComponentRoot);
    }

    public void openURL(String url){
        driver.get(url);
    }

    public String getUserProfileButtonText() {
        return headerComponent.getProfileButtonText();
    }

    public SignInComponent openSignInComponent() {
        return headerComponent.clickSignInLink();
    }



}
