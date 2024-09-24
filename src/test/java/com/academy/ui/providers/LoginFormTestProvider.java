package com.academy.ui.providers;

import com.academy.ui.styleConstants.Colors;
import com.academy.utils.LocalizationUtils;
import com.academy.utils.TestUtils;
import com.academy.utils.props.ConfigProperties;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginFormTestProvider {
    private final LocalizationUtils localization = new LocalizationUtils();
    private final String EMPTY_FIELDS_ERROR_UA = "Потрібно заповнити всі обов'язкові поля.";
    private final String EMPTY_FIELDS_ERROR_EN = "Please fill all required fields.";
    private final String EMPTY_FIELD_EMAIL_ERROR_UA = "Введіть пошту.";
    private final String EMPTY_FIELD_EMAIL_ERROR_EN = "Email is required.";
    private final String EMPTY_FIELD_PASSWORD_ERROR_UA = "Будь ласка введіть пароль.";
    private final String EMPTY_FIELD_PASSWORD_ERROR_EN = "Password is required.";
    private final String EXCEEDING_PASSWORD_ERROR_UA = "Пароль повинен містити менше 20 символів без пробілів.";
    private final String EXCEEDING_PASSWORD_ERROR_EN = "Password must be less than 20 characters long without spaces.";
    private final String INALID_PASSWORD_ERROR_UA = "Введено невірний email або пароль.";
    private final String INALID_PASSWORD_ERROR_EN = "Bad email or password.";
    private final String UNREGISTERED_EMAIL_ERROR_UA = "Введено невірний email або пароль.";
    private final String UNREGISTERED_EMAIL_ERROR_EN = "Bad email or password.";
    private final String INVALID_EMAIL_RECOVERY_ERROR = "Please check that your e-mail address is indicated correctly";
    private final String SHORT_PASSWORD_ERROR_UA = "Пароль повинен містити принаймні 8 символів без пробілів.";
    private final String SHORT_PASSWORD_ERROR_EN = "Password must be at least 8 characters long without spaces.";

    private final TestUtils testUtils;
    ConfigProperties configProperties = new ConfigProperties();

    public LoginFormTestProvider() {
        this.testUtils = new TestUtils();
    }

    @DataProvider(name = "verifyCssAndErrorIsDisplayedInForgotPasswordWithInvalidEmail")
    public Object[][] verifyCssAndErrorIsDisplayedInForgotPasswordWithInvalidEmail() {
        return new Object[][]{
                {"En", configProperties.getInvalidUserPassword(), INVALID_EMAIL_RECOVERY_ERROR},
        };
    }

    @DataProvider(name = "verifyErrorMessageForInvalidPassword")
    public Object[][] verifyErrorMessageForInvalidPassword() {
        return new Object[][] {
                {"Ua", configProperties.getRegisteredUserEmail(), configProperties.getRegisteredUserPassword() + "@123"
                        , INALID_PASSWORD_ERROR_UA},
                {"En", configProperties.getRegisteredUserEmail(), configProperties.getRegisteredUserPassword() + "@123"
                        , INALID_PASSWORD_ERROR_EN}
        };
    }

    @DataProvider(name = "verifyErrorMessageForEmptyEmailAndOrPassword")
    public Object[][] verifyErrorMessageForEmptyEmailAndOrPassword() {
        return new Object[][]{
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
        return new Object[][]{
                {"Ua", "", EMPTY_FIELD_EMAIL_ERROR_UA},
                {"En", "", EMPTY_FIELD_EMAIL_ERROR_EN},
        };
    }

    @DataProvider(name = "verifyErrorMessageForExceedingPasswordLength")
    public Object[][] verifyErrorMessageForExceedingPasswordLength() {
        return new Object[][]{
                {"Ua", configProperties.getRegisteredUserEmail(),
                        configProperties.getRegisteredUserPassword() + "12345678901234567890",
                        EXCEEDING_PASSWORD_ERROR_UA},
                {"En", configProperties.getRegisteredUserEmail(),
                        configProperties.getRegisteredUserPassword() + "12345678901234567890",
                        EXCEEDING_PASSWORD_ERROR_EN},
        };
    }

    @DataProvider(name = "verifyErrorMessageForEmptyPassword")
    public Object[][] verifyErrorMessageForEmptyPassword() {
        return new Object[][]{
                {"Ua", "", EMPTY_FIELD_PASSWORD_ERROR_UA},
                {"En", "", EMPTY_FIELD_PASSWORD_ERROR_EN},
        };
    }

    @DataProvider(name = "registeredUserCredentials")
    public Object[][] registeredUserCredentialsDataProvider() {
        return new Object[][]{
                {configProperties.getRegisteredUserEmail(), configProperties.getRegisteredUserPassword(),
                        configProperties.getRegisteredUserName(), configProperties.getRegisteredUserId()}
        };
    }

    @DataProvider(name = "verifySignInButtonRemainedInactivePassword")
    public Object[][] verifySignInButtonRemainedInactivePasswordDataProvider() {
        return new Object[][]{
                {configProperties.getRegisteredUserPassword()},
        };
    }

    @DataProvider(name = "verifyInSignInButtonRemainedInactiveEmail")
    public Object[][] verifyInSignInButtonRemainedInactiveEmailDataProvider() {
        return new Object[][]{
                {configProperties.getRegisteredUserEmail()},
        };
    }

    @DataProvider(name = "verifyPasswordLessThan8Characters")
    public Object[][] verifyPasswordLessThan8Characters() {
        return new Object[][]{
                {"Ua", "test@mail.com", "Test12", SHORT_PASSWORD_ERROR_UA},
                {"En", "test@mail.com", "Test12", SHORT_PASSWORD_ERROR_EN}
        };
    }

    @DataProvider(name = "widthResolutionPxAndZoomLevelsPercentage")
    public Object[][] widthResolutionPxAndZoomLevelsPercentageDataProvider() {
        return new Object[][]{
                {"En", 320, new ArrayList<>(List.of(100, 125, 150, 200))},
                {"En", 576, new ArrayList<>(List.of(100, 125, 150, 200))},
                {"En", 768, new ArrayList<>(List.of(100, 125, 150, 200))},
                {"En", 1024, new ArrayList<>(List.of(100, 125, 150, 200))},
                {"En", 1440, new ArrayList<>(List.of(100, 125, 150, 200))}
        };
    }

    @DataProvider(name = "verifyInvalidEmailPassword")
    public Object[][] verifyInvalidEmailPassword() {
        return new Object[][]{
                {"emailgmailcom", "pass"}
        };
    }

    @DataProvider(name = "verifyLoginUnregisteredEmailData")
    public Object[][] verifyLoginUnregisteredEmailData() {
        return new Object[][]{
                {"Ua", "test111@mail.com", "randomPassword", UNREGISTERED_EMAIL_ERROR_UA},
                {"En", "test111@mail.com", "randomPassword", UNREGISTERED_EMAIL_ERROR_EN}

        };
    }

    @DataProvider(name = "verifyMessageAfterRecoverPassDataProvider")
    public Object[][] verifyMessageAfterRecoverPassDataProvider() {
        return new Object[][]{
                {configProperties.getRegisteredUserEmail(), "Password restore link already sent, please check your email:"}
        };
    }

    @DataProvider(name = "verifyInSignInButtonRemainedInactiveValidEmailInvalidPassword")
    public Object[][] verifyInSignInButtonValidEmailInvalidPasswordProvider() {
        return new Object[][]{
                {configProperties.getRegisteredUserEmail(), "aaa"},
        };
    }

    @DataProvider(name = "verifyInSignInButtonRemainedInactiveValidPasswordInvalidEmail")
    public Object[][] verifyInSignInButtonValidPasswordInvalidEmailProvider() {
        return new Object[][]{
                {"gkefjefefgmailcom", configProperties.getRegisteredUserPassword()},
        };
    }

    @DataProvider(name = "verifyInvalidEmailWarningProvider")
    public Object[][] verifyInvalidEmailWarningProvider() {
        String english = "En";
        String ukrainian = "Ua";
        String color = Colors.PRIMARY_RED;
        String invalidEmail = "test.mail.comaaaa";
        String invalidEmailMessage = "EMAIL_INVALID";

        String emailNotRegisteredMessage = "EMAIL_NOT_REGISTERED";
        String notRegisteredEmail = "test.peach.green.city@gmail.com";
        return new Object[][] {
                {invalidEmail, english, localization.getFormMessages(english).get(invalidEmailMessage), color},
                {invalidEmail, ukrainian, localization.getFormMessages(ukrainian).get(invalidEmailMessage), color},

                {notRegisteredEmail, english, localization.getFormMessages(english).get(emailNotRegisteredMessage)+ " " + notRegisteredEmail, color},
                {notRegisteredEmail, ukrainian, localization.getFormMessages(ukrainian).get(emailNotRegisteredMessage) + " " + notRegisteredEmail, color},
        };
    }

    @DataProvider(name = "checkForgotPasswordUnregisteredEmailData")
    public Object[][] checkForgotPasswordUnregisteredEmailData() {
        return new Object[][]{
                {"test1@gmail.com", "The user does not exist by this email: test1@gmail.com"}
        };
    }
}
