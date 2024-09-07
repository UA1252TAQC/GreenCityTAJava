package com.academy.ui;

import com.academy.ui.components.HeaderComponent;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest extends BaseTestRunner {

    private HeaderComponent headerComponent;

    @BeforeMethod
    public void openProfile(){
        WebElement headerRootElement = driver
                .findElement(By.xpath("//div[@class='header_container']"));
        headerComponent = new HeaderComponent(driver, headerRootElement);
    }

    @Test
    public void openGoogle() {
        headerComponent.clickProfile();
    }
}
