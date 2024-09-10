package com.academy.ui.providers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import com.academy.ui.forms.Messages;

public class RegistrationFormFieldTestProvider {

    @DataProvider(name = "testEmailValidation")
    public Iterator<Object[]> dpTestEmailValidation() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] {false, Messages.EMAIL_REQUIRED, false, "'Електронна пошта' with empty field", ""});
        data.add(new Object[] {true, null, false, "'Електронна пошта' with spaces around", " test@gmail.com "});
        data.add(new Object[] {false, Messages.EMAIL_WITH_SPACES, false, "'Електронна пошта' with spaces", "tes t@gmail.com"});

        data.add(new Object[] {true, null, false, "'Електронна пошта' with “@” is valid", "test@gmail.com"});
        data.add(new Object[] {false, Messages.EMAIL_INVALID, false, "'Електронна пошта' with “@” is valid", "test@gmailcom"});
        data.add(new Object[] {false, Messages.EMAIL_INVALID, false, "'Електронна пошта' field require “@”", "testgmail.com"});

        data.add(new Object[] {false, Messages.EMAIL_INVALID, true, "'Електронна пошта' field can't contain more than 72 characters", "emailemailemailemailemailemailemailemailemailemailemai2emailema1@gmail.com"});
        data.add(new Object[] {true, null, true, "'Електронна пошта' field can contain max 72 characters", "emailemailemailemailemailemailemailemailemailemailemailemailem@gmail.com"}); // TODO check already registered?
        data.add(new Object[] {true, null, true, "'Електронна пошта' field can contain max 72 characters", "emailemailemailemailemailemailemailemailemailemailemailemaile@gmail.com"}); // TODO check already registered?
    
        return data.iterator();
    }

    @DataProvider(name = "testUsernameValidation")
    public Iterator<Object[]> dpTestUsernameValidation() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] {false, Messages.USERNAME_REQUIRED, "User name empty", ""});
        data.add(new Object[] {true, null, "Valid username", "Ira"});
        data.add(new Object[] {true, null, "Valid username with numbers and dots", "Ira99.Prut"});
        data.add(new Object[] {false, Messages.USERNAME_WITH_DOT_AT_THE_START, "Username with dot at the start", ".Ira"});
        data.add(new Object[] {false, Messages.USERNAME_WITH_CONSECUTIVE_DOT, "Username with consecutive dots", "Ira..99"});
        data.add(new Object[] {false, Messages.USERNAME_TOO_LONG, "Username too long", "IraThisUsernameIsWayTooLongForTheValidationRule"});

        return data.iterator();
    }

    @DataProvider(name = "testPasswordValidation")
    public Iterator<Object[]> dpTestPasswordValidation() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] {false, Messages.PASSWORD_REQUIRED, "Password empty", ""});
        data.add(new Object[] {false, Messages.PASSWORD_TOO_SHORT, "Password too short", "Short1!"});
        data.add(new Object[] {false, Messages.PASSWORD_WITHOUT_UPPERCASE, "Password without uppercase", "password1!"});
        data.add(new Object[] {false, Messages.PASSWORD_WITHOUT_DIGIT, "Password without digit", "Password!@"});
        data.add(new Object[] {false, Messages.PASSWORD_WITHOUT_SPECIAL_CHAR, "Password without special char", "Password1a"});
        data.add(new Object[] {true, null, "Valid password", "Password1!"});
        data.add(new Object[] {false, Messages.PASSWORD_TOO_LONG, "VeryLongPasswordThatExceeds20Chars1!", "Password1!Password1!Password1!"});

        return data.iterator();
    }

    @DataProvider(name = "testRepeatPasswordValidation")
    public Iterator<Object[]> dpTestRepeatPasswordValidation() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] {true, null, "Confirm password and password are equals!", "Password1!", "Password1!"});
        data.add(new Object[] {false, Messages.CONFIRM_PASSWORD_REQUIRED, "Confirm password empty", "Password1!", ""});
        data.add(new Object[] {false, Messages.PASSWORDS_DO_NOT_MATCH, "Passwords do not match", "Password1!", "DifferentPassword1!"});
        data.add(new Object[] {false, Messages.ENTER_PASSWORD_FIRST, "Confirm password with password empty", "", "Password1!"});

        return data.iterator();
    }

}
