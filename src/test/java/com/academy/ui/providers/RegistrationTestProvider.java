package com.academy.ui.providers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import com.academy.ui.components.sub.RegistrationMessages.UA;

public class RegistrationTestProvider {

    @DataProvider(name = "testEmailValidation")
    public Iterator<Object[]> dpTestEmailValidation() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] {false, UA.FIELD_REQUIRED, false,  "'Електронна пошта' with empty field", ""});
        data.add(new Object[] {true, null, false,  "'Електронна пошта' with spaces around", " test@gmail.com "});
        data.add(new Object[] {false, UA.EMAIL_INVALID, false, "'Електронна пошта' with spaces", "tes t@gmail.com"});

        data.add(new Object[] {true, null, false,  "'Електронна пошта' with “@” is valid", "test@gmail.com"});
        data.add(new Object[] {false, UA.EMAIL_INVALID, false,  "'Електронна пошта' with “@” is valid", "test@gmailcom"});
        //data.add(new Object[] {false, UA.EMAIL_INVALID, false,  "'Електронна пошта' with “@” is valid", "test@"});
        //data.add(new Object[] {false, UA.EMAIL_INVALID, false,  "'Електронна пошта' with “@” is valid", "@gmail.com"});
        data.add(new Object[] {false, UA.EMAIL_INVALID, false, "'Електронна пошта' field require “@”", "testgmail.com"});

        data.add(new Object[] {false, UA.EMAIL_INVALID, true, "'Електронна пошта' field can't contain more than 72 characters", "emailemailemailemailemailemailemailemailemailemailemai2emailema1@gmail.com"});
        data.add(new Object[] {true, null, true, "'Електронна пошта' field can contain max 72 characters", "emailemailemailemailemailemailemailemailemailemailemailemailem@gmail.com"}); //TODO check already registered?
        data.add(new Object[] {true, null, true, "'Електронна пошта' field can contain max 72 characters", "emailemailemailemailemailemailemailemailemailemailemailemaile@gmail.com"}); //TODO check already registered?

        return data.iterator();
    }

    @DataProvider(name = "testUsernameValidation")
    public Iterator<Object[]> dpTestUsernameValidation() {
        List<Object[]> data = new ArrayList<>();

        return data.iterator();
    }

    @DataProvider(name = "testPasswordValidation")
    public Iterator<Object[]> dpTestPasswordValidation() {
        List<Object[]> data = new ArrayList<>();

        return data.iterator();
    }

    @DataProvider(name = "testRepeatPasswordValidation")
    public Iterator<Object[]> dpTestRepeatPasswordValidation() {
        List<Object[]> data = new ArrayList<>();

        return data.iterator();
    }
}
