package com.academy.ui.runners;

import org.testng.annotations.AfterClass;
import com.academy.ui.runners.base.DriverRunner;

public class ClassDriverRunner extends DriverRunner {
    @AfterClass
    public void baseTearDown(){
        super.closeBrowser();
    }
}
