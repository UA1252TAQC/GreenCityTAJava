package com.academy.ui.runners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;

public class TestRunnerClassInitDriver extends BaseTestRunner {
    @BeforeClass
    public void initDriver(){
        initChromeDriver(List.of());
    }

    @AfterClass
    public void baseTearDown(){
        closeBrowser();
    }
}
