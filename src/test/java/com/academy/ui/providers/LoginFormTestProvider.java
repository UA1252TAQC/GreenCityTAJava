package com.academy.ui.providers;

import com.academy.utils.TestUtils;
import com.academy.utils.props.ConfigProperties;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

public class LoginFormTestProvider {
    private final TestUtils testUtils;

    public LoginFormTestProvider() {
        this.testUtils = new TestUtils();
    }
    ConfigProperties configProperties = new ConfigProperties();
    String emptyFieldsErrorUA = "Потрібно заповнити всі обов'язкові поля.";

    @DataProvider(name = "verifyErrorMessageForExceedingPasswordLengthInUA")
    public Iterator<Object[]> verifyErrorMessageForExceedingPasswordLengthInUA(Method method) {
        return testUtils.getTestCases(method);
    }

    @DataProvider(name = "verifyErrorMessageForInvalidPasswordUA")
    public Iterator<Object[]> verifyErrorMessageForInvalidPasswordUA(Method method) {
        return testUtils.getTestCases(method);
    }

    @DataProvider(name = "verifyErrorMessageForEmptyEmailAndPasswordEng")
    public Iterator<Object[]> verifyErrorMessageForEmptyEmailAndPasswordEng(Method method) {
        return testUtils.getTestCases(method);
    }

    @DataProvider(name = "verifyCssAndErrorIsDisplayedInForgotPasswordWithInvalidEmail")
    public Iterator<Object[]> verifyCssAndErrorIsDisplayedInForgotPasswordWithInvalidEmail(Method method) {
        return testUtils.getTestCases(method);
    }

    @DataProvider(name = "emptyFields")
    public Object[][] emptyFieldsDataProvider() {
        return new Object[][] {
                {"Ua", configProperties.getRegisteredUserEmail(), "", emptyFieldsErrorUA},
                {"Ua", "", configProperties.getRegisteredUserPassword(), emptyFieldsErrorUA},
                {"Ua", "", "", emptyFieldsErrorUA},
        };
    }

    @DataProvider(name = "checkSuccessfulSignInWithValidCredentials")
    public Object[][] validUserDataProvider() {
        HashMap<String, String> validUserData = new <String, String>HashMap();
        validUserData.put("email", configProperties.getRegisteredUserEmail());
        validUserData.put("password", configProperties.getRegisteredUserPassword());
        validUserData.put("id", configProperties.getUserId());
        validUserData.put("name", configProperties.getUserName());
        return new Object[][]{
                {validUserData}
        };
    }
}
