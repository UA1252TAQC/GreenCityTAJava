package com.academy.ui.providers;

import com.academy.ui.forms.Messages;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RegistrationFormTestProvider {

    @DataProvider(name = "testPopUpSignUpValidation")
    public Iterator<Object[]> dpTestPopUpSignUpValidation() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[] { Messages.POP_UP_MESSAGE, "tsdddjdseaa@gmail.com", "TestUser", "Password1!", "Password1!"});
        return data.iterator();
    }
}
