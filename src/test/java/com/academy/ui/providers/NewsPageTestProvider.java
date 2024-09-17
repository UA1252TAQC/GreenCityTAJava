package com.academy.ui.providers;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.academy.utils.TestUtils;

public class NewsPageTestProvider {
    private final TestUtils testUtils;

    public NewsPageTestProvider() {
        this.testUtils = new TestUtils();
    }

    @DataProvider(name = "allTagsDisplayedCorrectly")
    public Iterator<Object[]> dpAllTagsDisplayedCorrectly(Method method) {
        return testUtils.getTestCases(method);
    }
}
