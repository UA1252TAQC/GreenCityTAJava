package com.academy.ui.providers;

import com.academy.utils.TestUtils;
import com.academy.utils.props.ConfigProperties;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginFormTestProvider {
    private final TestUtils testUtils;

    ConfigProperties configProperties = new ConfigProperties();
    String emptyFieldsErrorUA = "Потрібно заповнити всі обов'язкові поля.";
    String emptyFieldsErrorEN = "Please fill all required fields.";

    public LoginFormTestProvider() {
        this.testUtils = new TestUtils();
    }


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

    @DataProvider(name = "verifyErrorMessageForEmptyEmailAndOrPassword")
    public Object[][] emptyFieldsDataProvider() {
        return new Object[][] {
                {"Ua", configProperties.getRegisteredUserEmail(), "", emptyFieldsErrorUA},
                {"Ua", "", configProperties.getRegisteredUserPassword(), emptyFieldsErrorUA},
                {"Ua", "", "", emptyFieldsErrorUA},
                {"En", configProperties.getRegisteredUserEmail(), "", emptyFieldsErrorEN},
                {"En", "", configProperties.getRegisteredUserPassword(), emptyFieldsErrorEN},
                {"En", "", "", emptyFieldsErrorEN}
        };
    }

    @DataProvider(name = "registeredUserCredentials")
    public Object[][] registeredUserCredentialsDataProvider() {
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

    @DataProvider(name = "checkPasswordLessThan8Characters")
    public Object[][] checkPasswordLessThan8CharactersEN() {
        return new Object[][] {
                {"test@mail.com", "Test12", "Password must be at least 8 characters long without spaces."}
        };
    }

    @DataProvider(name = "checkPasswordLessThan8CharactersUA")
    public Object[][] checkPasswordLessThan8CharactersUA() {
        return new Object[][]{
                {"test@mail.com", "Test12", "Пароль повинен містити принаймні 8 символів без пробілів."}
        };
    }


    @DataProvider(name = "screenResolution320pxAndZoomLevelValuesPercentage")
    public Object[][] checkScrollbarIsDisplayedAt320pxResolutionDataProvider() {
        return new Object[][] {
                {320, new ArrayList<>(List.of(100, 125, 150, 200))}
        };
    }

}
