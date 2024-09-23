package com.academy.ui.providers;

import com.academy.utils.TestUtils;
import com.academy.utils.props.ConfigProperties;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginFormTestProvider {
    private final String EMPTY_FIELDS_ERROR_UA = "Потрібно заповнити всі обов'язкові поля.";
    private final String EMPTY_FIELDS_ERROR_EN = "Please fill all required fields.";
    private final String EMPTY_FIELD_EMAIL_ERROR_UA = "Введіть пошту.";
    private final String EMPTY_FIELD_EMAIL_ERROR_EN = "Email is required.";
    private final String EMPTY_FIELD_PASSWORD_ERROR_UA = "Будь ласка введіть пароль.";
    private final String EMPTY_FIELD_PASSWORD_ERROR_EN = "Password is required.";

    private final TestUtils testUtils;
    ConfigProperties configProperties = new ConfigProperties();

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
    public Object[][] verifyErrorMessageForEmptyEmailAndOrPassword() {
        return new Object[][] {
                {"Ua", configProperties.getRegisteredUserEmail(), "", EMPTY_FIELDS_ERROR_UA},
                {"Ua", "", configProperties.getRegisteredUserPassword(), EMPTY_FIELDS_ERROR_UA},
                {"Ua", "", "", EMPTY_FIELDS_ERROR_UA},
                {"En", configProperties.getRegisteredUserEmail(), "", EMPTY_FIELDS_ERROR_EN},
                {"En", "", configProperties.getRegisteredUserPassword(), EMPTY_FIELDS_ERROR_EN},
                {"En", "", "", EMPTY_FIELDS_ERROR_EN}
        };
    }

    @DataProvider(name = "verifyErrorMessageForEmptyEmail")
    public Object[][] verifyErrorMessageForEmptyEmail() {
        return new Object[][] {
                {"Ua", "", EMPTY_FIELD_EMAIL_ERROR_UA},
                {"En", "", EMPTY_FIELD_EMAIL_ERROR_EN},
        };
    }

    @DataProvider(name = "verifyErrorMessageForEmptyPassword")
    public Object[][] verifyErrorMessageForEmptyPassword() {
        return new Object[][] {
                {"Ua", "", EMPTY_FIELD_PASSWORD_ERROR_UA},
                {"En", "", EMPTY_FIELD_PASSWORD_ERROR_EN},
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
    public Object[][] checkSignInButtonRemainedInactivePasswordDataProvider() {
        return new Object[][] {
                {configProperties.getRegisteredUserPassword()},
        };
    }

    @DataProvider(name = "checkInSignInButtonRemainedInactiveEmail")
    public Object[][] checkInSignInButtonRemainedInactiveEmailDataProvider() {
        return new Object[][] {
                {configProperties.getRegisteredUserEmail()},
        };
    }

    @DataProvider(name = "checkPasswordLessThan8CharactersEN")
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
                {320, new ArrayList<>(List.of(100, 125, 150, 200))},
                {576, new ArrayList<>(List.of(100, 125, 150, 200))}
        };
    }

    @DataProvider(name = "InvalidEmailPassword")
    public Object[][] InvalidEmailPassword() {
        return new Object[][] {
                {"emailgmailcom", "pass"}
        };
    }

    @DataProvider(name = "checkLoginUnregisteredEmailDataEN")
    public Object[][] checkLoginUnregisteredEmailDataEN() {
        return new Object[][] {
                {"test111@mail.com", "randomPassword", "Bad email or password."}
        };
    }

    @DataProvider(name = "checkLoginUnregisteredEmailDataUA")
    public Object[][] checkLoginUnregisteredEmailDataUA() {
        return new Object[][] {
                {"test111@mail.com", "randomPassword", "Введено невірний email або пароль."}
        };
    }

    @DataProvider(name = "verifyMessageAfterRecoverPassDataProvider")
    public Object[][] verifyMessageAfterRecoverPassDataProvider() {
        return new Object[][] {
                {configProperties.getRegisteredUserEmail(), "Password restore link already sent, please check your email:"}
        };
    }

    @DataProvider(name = "verifyErrorMessageForExceedingPasswordLengthInEN")
    public Object[][]verifyErrorMessageForExceedingPasswordLengthInEN() {
        return new Object[][] {
                {configProperties.getRegisteredUserEmail(),
                        "AaBbCcDdEe1234567890!",
                        "Password must be less than 20 characters long without spaces."}
        };
    }

    @DataProvider(name = "checkInSignInButtonRemainedInactiveValidEmailInvalidPassword")
    public Object[][] checkInSignInButtonValidEmailInvalidPasswordProvider() {
        return new Object[][] {
                {configProperties.getRegisteredUserEmail(), "aaa"},
        };
    }

    @DataProvider(name = "checkInSignInButtonRemainedInactiveValidPasswordInvalidEmail")
    public Object[][] checkInSignInButtonValidPasswordInvalidEmailProvider() {
        return new Object[][] {
                {"gkefjefefgmailcom", configProperties.getRegisteredUserPassword()},
        };
    }

}
