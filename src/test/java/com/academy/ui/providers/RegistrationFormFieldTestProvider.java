package com.academy.ui.providers;

import java.lang.reflect.Method;
import java.util.Iterator;

import com.academy.utils.TestUtils;
import org.testng.annotations.DataProvider;

public class RegistrationFormFieldTestProvider {
    private final TestUtils testUtils;

    public RegistrationFormFieldTestProvider() {
        this.testUtils = new TestUtils();
    }

    @DataProvider(name = "testButtonState")
    public Iterator<Object[]> dpTestButtonState(Method method) {
        return testUtils.getTestCases(method);
    }

    @DataProvider(name = "testEmailValidation")
    public Iterator<Object[]> dpTestEmailValidation(Method method) {
        return testUtils.getTestCases(method);
    }

    @DataProvider(name = "testUsernameValidation")
    public Iterator<Object[]> dpTestUsernameValidation(Method method) {
        return testUtils.getTestCases(method);
    }

    @DataProvider(name = "testPasswordValidation")
    public Iterator<Object[]> dpTestPasswordValidation(Method method) {
        return testUtils.getTestCases(method);
    }

    @DataProvider(name = "testRepeatPasswordValidation")
    public Iterator<Object[]> dpTestRepeatPasswordValidation(Method method) {
        return testUtils.getTestCases(method);
    }

}
