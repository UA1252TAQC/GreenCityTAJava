package com.academy.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Base {
    protected WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    public void sleep(long seconds) {
        try{
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            //
        }
    }

}
