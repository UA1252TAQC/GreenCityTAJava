package com.academy.ui.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UbsHeaderComponent extends BaseComponent {
    @FindBy(how = How.XPATH, using = ".//ul[@class='header_lang-switcher-wrp header_navigation-menu-right-lang ubs-lang-switcher']")
    protected WebElement listLanguage;

    @FindBy(how = How.XPATH, using = ".//li[@tabindex='0'][@class='lang-option ng-star-inserted'][@role='menuitem']")
    protected WebElement english;

    @FindBy(how = How.XPATH, using = ".//li[@class='header_sign-up-link ng-star-inserted']//span")
    protected WebElement register;

    public UbsHeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public RegistrationModalComponent openRegistrationForm() {
        click(register);
        return new RegistrationModalComponent(driver, findElement(".//app-auth-modal"));
    }

    public void setLanguage(String language) {
        if (language.equals("en")) {
            click(this.listLanguage);
            click(this.english);
        }
    }

    public String getPopUpMessage() {
        WebElement messageElement = findWithWaitElement("//simple-snack-bar[@class='mat-mdc-simple-snack-bar ng-star-inserted']");
        return messageElement.getText();
    }
}
