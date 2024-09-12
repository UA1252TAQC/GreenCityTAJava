package com.academy.ui.providers;

import com.academy.utils.TestUtils;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Iterator;

public class RegistrationFormTestProvider {
    private final TestUtils testUtils;

    public RegistrationFormTestProvider() {
        this.testUtils = new TestUtils();
    }

    @DataProvider(name = "testPopUpSignUpValidation")
    public Iterator<Object[]> dpTestPopUpSignUpValidation(Method method) {
        return testUtils.getTestCases(method);
    }

    @DataProvider(name = "testGoogleSignUp")
    public Iterator<Object[]> dpTestGoogleSignUp(Method method) {
        return testUtils.getTestCases(method);
    }

    @DataProvider(name = "testRegisteredGreenCity")
    public Iterator<Object[]> dptestRegisteredGreenCity(Method method) {
        return testUtils.getTestCases(method);
    }
}
