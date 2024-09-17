package com.academy.ui;

import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTestRunner {
    private HomePage page;

    @BeforeMethod
    public void setUpPage() {
        page = new HomePage(driver);
    }

    @Test
    public void openRegistrationFormInHeader() {
        page.openRegistrationFormInHeader();
    }
}
