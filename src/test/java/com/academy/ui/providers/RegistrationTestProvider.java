package com.academy.ui.providers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

public class RegistrationTestProvider {

    @DataProvider(name = "testEmailValidation")
    public Iterator<Object[]> dpTestEmailValidation() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] {true, false, "'Електронна пошта' field can't contain more than 72 characters", "emailemai1emailemailemai5emailemai1emailema36emailemai2emailemailemail123@gmaif.com"});
        data.add(new Object[] {true, false, "'Електронна пошта' field can't contain more than 72 characters", "emailemai1emailemailemailemailemai2emailemailem77lemai2email123@gmaif.com"});
        data.add(new Object[] {true, true, "'Електронна пошта' field can contain max 72 characters", "emailemailemai1emailemailemailemailemai3emailemaile47ilemai212@gmaif.com"});

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
