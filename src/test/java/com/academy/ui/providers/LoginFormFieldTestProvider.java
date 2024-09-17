package com.academy.ui.providers;

import com.academy.utils.props.ConfigProperties;
import org.testng.annotations.DataProvider;

public class LoginFormFieldTestProvider {

    ConfigProperties configProperties = new ConfigProperties();
    String emptyFieldsErrorUA = "Потрібно заповнити всі обов'язкові поля.";

    @DataProvider(name = "emptyFields")
    public Object[][] emptyFieldsDataProvider() {
        return new Object[][] {
                {"Ua", configProperties.getRegisteredUserEmail(), "", emptyFieldsErrorUA},
                {"Ua", "", configProperties.getRegisteredUserPassword(), emptyFieldsErrorUA},
                {"Ua", "", "", emptyFieldsErrorUA},
        };
    }
}