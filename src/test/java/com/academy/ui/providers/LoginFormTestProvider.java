package com.academy.ui.providers;

import com.academy.utils.TestUtils;
import com.academy.utils.props.ConfigProperties;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
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

    @DataProvider(name = "checkSuccessfulSignInDataProvider")
    public Object[][] checkSuccessfulSignInDataProvider() {
        return new Object[][] {
                {configProperties.getRegisteredUserEmail(), configProperties.getRegisteredUserPassword(),
                configProperties.getRegisteredUserName(), configProperties.getRegisteredUserId()}

        };
    }

    @DataProvider(name = "checkSignInButtonRemainedInactivePassword")
    public Object[][] FieldsDataProviderPassword() {
        return new Object[][] {
                {"password", configProperties.getRegisteredUserPassword()},
        };
    }

    @DataProvider(name = "checkInSignInButtonRemainedInactiveEmail")
    public Object[][] FieldsDataProviderEmail() {
        return new Object[][] {
                {"email", configProperties.getRegisteredUserPassword()},
        };
    }
}
