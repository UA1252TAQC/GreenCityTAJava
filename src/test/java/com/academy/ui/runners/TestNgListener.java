package com.academy.ui.runners;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNgListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        WebDriver driver = ((BaseTestRunner) testInstance).driver;
        if(driver != null){
            saveScreenshot(driver);
        }
        ITestListener.super.onTestFailure(result);
    }

    @Attachment(value = "Page screenshot", type = "image/png", fileExtension = ".png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
