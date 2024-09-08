package com.academy.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyHabitsPage extends BasePage{

    private WebElement element;

    public MyHabitsPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        element = driver.findElement(By.id("guy-image"));
    }

}
