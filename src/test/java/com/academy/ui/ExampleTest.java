package com.academy.ui;

import com.academy.ui.components.RegistrationForm;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class ExampleTest extends BaseTestRunner {

    @Test
    public void openFormEng() {
        WebElement greencity = driver.findElement(By.xpath("//li[@class='nav-left-list'][3]/a[@class='url-name']\n"));
        new Actions(driver).moveToElement(greencity).click().perform();
        WebElement langOptions = driver.findElement(By.xpath(" //li[@class='lang-option']"));
        new Actions(driver).moveToElement(langOptions).click().perform();
        //driver.findElement(By.xpath("//span[@class='ubs-lang-switcher-span']")).click();
        WebElement signUpButton = driver.findElement(By.xpath("//div[@class='header_sign-up-btn']"));
        new Actions(driver).moveToElement(signUpButton).click().perform();
    }

    public void fillForm(String email, String username, String password, String confirmPassword) {
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='repeatPassword']")).sendKeys(confirmPassword);
        new Actions(driver).moveByOffset(1, 1).click().perform();
    }

    @Test
    public void registrationFormEmptyUsernameEng() {
        openFormEng();
        fillForm("test@gmail.com", "", "Testpass1111!", "Testpass1111!");

        WebElement userNameError = driver.findElement(By.xpath("//*[@id='firstname-err-msg']/app-error/div"));
        WebElement signUpButton = driver.findElement(By.xpath("//button[@type='submit']"));

        String actualErrorMessage = userNameError.getText();
        System.out.println("Error message displayed: " + actualErrorMessage);

        String expectedErrorMessage = "Введіть ім'я";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Validation error message should match the expected message.");
        Assert.assertFalse(signUpButton.isEnabled(), "Sign-up button should be disabled when username is empty.");
    }


    @DataProvider(name = "validUserNames")
    public Object[][] validUserNames() {
        return new Object[][] {
                { "ИванИванов" },
                { "JohnDoe" },
                { "John.Doe" },
                { "John123" },
                { "John.Doe123" },
                { "hjfjdkМАксhjfjdkМАкhjfj7777473" },
                { "A" + "a".repeat(28) },
        };
    }

    @DataProvider(name = "invalidUserNames")
    public Object[][] invalidUserNames() {
        return new Object[][] {
                { "" },
                //{ " " },
        };
    }

    @Test(dataProvider = "validUserNames")
    public void testUserNameCanContain30Characters(String userName) {
        openFormEng();
        RegistrationForm form = new RegistrationForm(driver);
        form.enterUserName(userName);
        form.enterEmail("test@example.com");
        form.enterPassword("Cuho_46699");
        form.enterConfirmPassword("Cuho_46699");
        form.submitForm();

        String actualUserName = form.getUserName();
        Assert.assertEquals(actualUserName, userName, "User name should be correctly set and retrieved.");
    }

    @Test(dataProvider = "invalidUserNames")
    public void testUserNameCannotBeEmptyOrInvalid(String userName) {
        openFormEng();
        RegistrationForm form = new RegistrationForm(driver);
        form.enterUserName(userName);
        form.enterEmail("test@example.com");
        form.enterPassword("Cuho_46699");
        form.enterConfirmPassword("Cuho_46699");
        //form.submitForm();

        try {
            String errorMessage = form.getUserNameValidationError();
            System.out.println("Error message displayed: " + errorMessage);

            String expectedMessage;
            if (userName.isEmpty()) {
                expectedMessage = "Введіть ім'я";
            } else if (userName.length() > 30) {
                expectedMessage = "Ім'я користувача не може перевищувати 30 символів";
            } else if (userName.startsWith(".") || userName.endsWith(".") || userName.contains("..")) {
                expectedMessage = "Ім'я користувача не може починатися або закінчуватися крапкою і не може містити підрядкові крапки"; // Локализованное сообщение для недопустимого размещения точек
            } else {
                Assert.fail("Unexpected test case");
                return;
            }

            Assert.assertEquals(errorMessage, expectedMessage, "Validation error message should match the expected message.");
        } catch (NoSuchElementException e) {
            System.out.println("No validation error message found: " + e.getMessage());
            Assert.fail("Expected validation error message but none was found.");
        }
    }
}
