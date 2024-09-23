package com.academy.ui.runners;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;

public class TestRunnerClassInitDriver extends BaseTestRunner {
    @BeforeClass
    public void initDriver(ITestContext context){
        initChromeDriver(List.of());
        context.setAttribute("webDriver", driver);
    }

    @AfterClass
    public void baseTearDown(){
        closeBrowser();
    }
}
