package com.academy.ui.components;

import com.academy.ui.pages.greenCity.BasePageGreenCity;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleAuthComponent extends BasePageGreenCity {

    @FindBy(xpath = ".//*[@id='identifierId']")
    private WebElement emailInput;

    @FindBy(xpath = ".//*[@id='identifierNext']/div/button")
    private WebElement emailNextButton;

    @FindBy(xpath = ".//*[@id='password']/div[1]/div/div[1]/input")
    private WebElement passwordInput;

    @FindBy(xpath = ".//*[@id='passwordNext']/div/button")
    private WebElement passwordNextButton;

    public GoogleAuthComponent(WebDriver driver) {
        super(driver);
    }

    public GoogleAuthComponent enterEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public GoogleAuthComponent clickEmailSubmitButton() {
        click(emailNextButton);
        sleep(6);
        return this;
    }

    public GoogleAuthComponent enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public void clickPasswordSubmitButton() {
        click(passwordNextButton);
        sleep(1);
    }

    @Step("Email field is displayed on GoogleAuthComponent")
    public boolean isEmailInputDisplayed(){
        return isDisplayed(emailInput);
    }
}
