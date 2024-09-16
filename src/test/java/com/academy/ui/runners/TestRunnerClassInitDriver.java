package com.academy.ui.runners;

import org.testng.annotations.AfterClass;
import com.academy.ui.runners.base.DriverRunner;
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
