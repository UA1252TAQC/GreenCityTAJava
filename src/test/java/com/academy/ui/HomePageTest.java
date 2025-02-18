package com.academy.ui;

import com.academy.ui.runners.TestRunnerMethodInitDriverHomePage;
import org.testng.annotations.Test;

public class HomePageTest extends TestRunnerMethodInitDriverHomePage {

    @Test
    public void openRegistrationFormInHeader() {
        page.getHeaderComponent().openRegistrationForm();
    }

    @Test
    public void openNewsPageInHeader() {
        page.getHeaderComponent().openNewsLink();
    }
}
