package com.academy.ui;

import com.academy.ui.components.HeaderComponent;
import com.academy.ui.components.SignInComponent;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignInTest extends BaseTestRunner {

    private HeaderComponent headerComponent;
    private SignInComponent signInComponent;

    @BeforeMethod
    public void setUpSignInTest() {
        WebElement headerRootElement = getHeaderRootElement();
        headerComponent = new HeaderComponent(driver, headerRootElement);
    }

    @Test
    public void verifyErrorMessageForInvalidPasswordInUa(){
        headerComponent.selectLanguage("ua");
        openProfileAndWaitForWrapper();
        boolean isErrorDisplayed =signInComponent
                .sendEmail("test@gmail.com")
                .sendPassword("Test123!")
                .sendForm()
                .verifyErrorMessageUA("Введено невірний email або пароль");

        Assert.assertTrue(isErrorDisplayed, "Error message is not displayed as expected");
    }

    public WebElement getHeaderRootElement() {
        return driver.findElement(By.xpath("//div[@class='header_container']"));
    }

    public WebElement getSignInRootElement() {
        return driver.findElement(By.xpath("//div[@class='wrapper']"));
    }

    private void openProfileAndWaitForWrapper() {
        headerComponent.openProfile();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signInRootElement = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='wrapper']")));
        signInComponent = new SignInComponent(driver, signInRootElement);
    }
}
