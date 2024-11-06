package com.academy.cucumber.step_definitions;

import com.academy.ui.pages.AllPages;
import org.openqa.selenium.WebDriver;


public class PageContext {

    private final WebDriver driver;
    private AllPages allPages;
//        private AllProfileComponents allProfileComponents;
//        private AllHeaderComponents allHeaderComponents;


    public PageContext(WebDriver driver) {
        this.driver = driver;
    }

    public AllPages getAllPages() {
        allPages = new AllPages(driver);
        return allPages;
    }
//        public AllProfileComponents getAllProfilePageComponents(){
//            allProfileComponents = new AllProfileComponents(driver);
//            return allProfileComponents;
//        }
//
//        public AllHeaderComponents getAllHeaderComponents(){
//            allHeaderComponents = new AllHeaderComponents(driver);
//            return allHeaderComponents;
//        }

}

