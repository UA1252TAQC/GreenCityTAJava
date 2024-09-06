package com.academy.ui.providers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

public class RegistrationTestProvider {

    @DataProvider(name = "testEmailValidation")
    public Iterator<Object[]> dpTestEmailValidation() {
        List<Object[]> data = new ArrayList<>();

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
