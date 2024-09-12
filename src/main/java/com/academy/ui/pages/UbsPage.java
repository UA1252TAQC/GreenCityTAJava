package com.academy.ui.pages;

import com.academy.ui.components.UbsHeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UbsPage extends BasePage {
    @FindBy(how = How.XPATH, using = ".//header[@role='banner']")
    private WebElement headerRoot;

    public UbsPage(WebDriver driver) {
        super(driver);
    }

    public String getAccountSubmitPopUpMessage() {
        return findWithWaitElement(".//div[@class='mat-mdc-snack-bar-label mdc-snackbar__label']").getText();
    }

    public UbsHeaderComponent getHeaderComponent() {
        return new UbsHeaderComponent(driver, headerRoot);
    }
}
