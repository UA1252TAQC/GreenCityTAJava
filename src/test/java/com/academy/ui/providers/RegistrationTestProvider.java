package com.academy.ui.providers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import com.academy.ui.pages.RegistrationMessages.UA;

public class RegistrationTestProvider {

    @DataProvider(name = "testEmailValidation")
    public Iterator<Object[]> dpTestEmailValidation() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] {false, UA.FIELD_REQUIRED, false,  "'Електронна пошта' with empty field", ""});
        data.add(new Object[] {true, null, false,  "'Електронна пошта' with spaces around", " test@gmail.com "});
        data.add(new Object[] {false, UA.EMAIL_INVALID, false, "'Електронна пошта' with spaces", "tes t@gmail.com"});

        data.add(new Object[] {true, null, false,  "'Електронна пошта' with “@” is valid", "test@gmail.com"});
        data.add(new Object[] {false, UA.EMAIL_INVALID, false,  "'Електронна пошта' with “@” is valid", "test@gmailcom"});
        data.add(new Object[] {false, UA.EMAIL_INVALID, false, "'Електронна пошта' field require “@”", "testgmail.com"});

        data.add(new Object[] {false, UA.EMAIL_INVALID, true, "'Електронна пошта' field can't contain more than 72 characters", "emailemailemailemailemailemailemailemailemailemailemai2emailema1@gmail.com"});
        data.add(new Object[] {true, null, true, "'Електронна пошта' field can contain max 72 characters", "emailemailemailemailemailemailemailemailemailemailemailemailem@gmail.com"}); //TODO check already registered?
        data.add(new Object[] {true, null, true, "'Електронна пошта' field can contain max 72 characters", "emailemailemailemailemailemailemailemailemailemailemailemaile@gmail.com"}); //TODO check already registered?

        return data.iterator();
    }

    
    @DataProvider(name = "testUsernameValidation")
    public Iterator<Object[]> dpTestUsernameValidation() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] {false, "Це поле є обов'язковим", "User name empty", ""});
        data.add(new Object[] {true, null, "Valid username", "Ira"});
        data.add(new Object[] {true, null, "Valid username with numbers and dots", "Ira99.Prut"});
        data.add(new Object[] {false, "Поле обов’язкове для заповнення. Ім'я повинно містити 1-30 символів і може складатись з літер(a-z; а-я), цифр(0-9) та крапки(.), крапка на початку імені, в кінці та послідовність крапок заборонена", "Username with dot at the start", ".Ira"});
        data.add(new Object[] {false, "Поле обов’язкове для заповнення. Ім'я повинно містити 1-30 символів і може складатись з літер(a-z; а-я), цифр(0-9) та крапки(.), крапка на початку імені, в кінці та послідовність крапок заборонена", "Username with consecutive dots", "Ira..99"});
        data.add(new Object[] {false, "Поле обов’язкове для заповнення. Ім'я повинно містити 1-30 символів і може складатись з літер(a-z; а-я), цифр(0-9) та крапки(.), крапка на початку імені, в кінці та послідовність крапок заборонена", "Username too long", "IraThisUsernameIsWayTooLongForTheValidationRule"});

        return data.iterator();
    }

    @DataProvider(name = "testPasswordValidation")
    public Iterator<Object[]> dpTestPasswordValidation() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] {false, "Пароль повинен мати від 8 до 20 символів без пробілів, містити хоча б один символ латинського алфавіту верхнього (A-Z) та нижнього регістру (a-z), число (0-9) та спеціальний символ (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)", "Password empty", ""});
        data.add(new Object[] {false, "Пароль повинен мати від 8 до 20 символів без пробілів, містити хоча б один символ латинського алфавіту верхнього (A-Z) та нижнього регістру (a-z), число (0-9) та спеціальний символ (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)", "Password too short", "Short1!"});
        data.add(new Object[] {false, "Пароль повинен мати від 8 до 20 символів без пробілів, містити хоча б один символ латинського алфавіту верхнього (A-Z) та нижнього регістру (a-z), число (0-9) та спеціальний символ (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)", "Password without uppercase", "password1!"});
        data.add(new Object[] {false, "Пароль повинен мати від 8 до 20 символів без пробілів, містити хоча б один символ латинського алфавіту верхнього (A-Z) та нижнього регістру (a-z), число (0-9) та спеціальний символ (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)", "Password without digit", "Password!@"});
        data.add(new Object[] {false, "Пароль повинен мати від 8 до 20 символів без пробілів, містити хоча б один символ латинського алфавіту верхнього (A-Z) та нижнього регістру (a-z), число (0-9) та спеціальний символ (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)", "Password without special char", "Password1a"});
        data.add(new Object[] {true, null, "Valid password", "Password1!"});
        data.add(new Object[] {false, "Пароль повинен мати від 8 до 20 символів без пробілів, містити хоча б один символ латинського алфавіту верхнього (A-Z) та нижнього регістру (a-z), число (0-9) та спеціальний символ (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)", "Password too long", "VeryLongPasswordThatExceeds20Chars1!"});

        return data.iterator();
    }

    @DataProvider(name = "testRepeatPasswordValidation")
    public Iterator<Object[]> dpTestRepeatPasswordValidation() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] {true, null, "Confirm password and password are equals!", "Password1!", "Password1!"});
        data.add(new Object[] {false, "Це поле є обов'язковим", "Confirm password empty", "Password1!", ""});
        data.add(new Object[] {false, "Пароль не співпадає", "Passwords do not match", "Password1!", "DifferentPassword1!"});
        data.add(new Object[] {false, "Будь ласка введіть спершу пароль!", "Confirm password with password empty", "", "Password1!"});
        
        return data.iterator();
    }
}
