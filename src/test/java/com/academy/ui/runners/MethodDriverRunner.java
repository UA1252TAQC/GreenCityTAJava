package com.academy.ui.runners;

import org.testng.annotations.AfterMethod;
import com.academy.ui.runners.base.DriverRunner;

public class MethodDriverRunner extends DriverRunner {
    @AfterMethod
    public void baseTearDown(){
        super.closeBrowser();
    }
}
