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
        WebElement langOptions = driver.findElement(By.xpath(" //li[@class='lang-option']"));
        new Actions(driver).moveToElement(langOptions).click().perform();
        driver.findElement(By.xpath("//span[@class='ubs-lang-switcher-span']")).click();
        WebElement signUpButton = driver.findElement(By.xpath("//div[@class='ubs-header_sign-up-btn']"));
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

        Assert.assertEquals(userNameError.getText(), "User name is required");
        Assert.assertFalse(signUpButton.isEnabled());
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
                { "." },
                { ".." },
                { ".John" },
                { "John." },
                { "John..Doe" },
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

        //String errorMessage = form.getUserNameValidationError();

        try {
            String errorMessage = form.getUserNameValidationError();
            System.out.println("Error message displayed: " + errorMessage);

            if (userName.isEmpty()) {
                Assert.assertEquals(errorMessage, "User name cannot be empty", "Validation error message should be for empty user name.");
            } else if (userName.length() > 30) {
                Assert.assertEquals(errorMessage, "User name cannot exceed 30 characters", "Validation error message should be for exceeding length.");
            } else if (userName.startsWith(".") || userName.endsWith(".") || userName.contains("..")) {
                Assert.assertEquals(errorMessage, "User name cannot start or end with a dot and cannot contain consecutive dots", "Validation error message should be for invalid dot placement.");
            } else {
                Assert.fail("Unexpected test case");
            }
        } catch (NoSuchElementException e) {
            System.out.println("No validation error message found: " + e.getMessage());
            Assert.fail("Expected validation error message but none was found.");
        }
    }
}
