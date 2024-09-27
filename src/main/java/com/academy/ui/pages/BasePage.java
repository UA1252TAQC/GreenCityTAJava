package com.academy.ui.pages;

import com.academy.ui.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class BasePage extends Base {
    private static final int TIME_TO_WAIT = 10;

    @FindBy(xpath = ".//header[@role='banner']")
    protected WebElement headerRootElement;

    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPopUpMessage() {
        return super.findWithWaitElement("//div[@matsnackbarlabel]", TIME_TO_WAIT).getText();
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public void openUrlInNewTab(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "', '_blank');");
        switchToActiveTab();
    }

    public void switchToActiveTab() {
        sleep(5);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.getLast());
    }

    public String getLocalStorageItem(String key) {
        return (String) ((JavascriptExecutor) driver).executeScript(String.format(
                "return window.localStorage.getItem('%s');", key));
    }

    public String getAuthToken() {
        return getLocalStorageItem("accessToken");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void uploadFile(String filePath) throws Exception {
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        Robot robot = new Robot();
        robot.setAutoDelay(500);

        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_META);

        robot.setAutoDelay(500);

        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
