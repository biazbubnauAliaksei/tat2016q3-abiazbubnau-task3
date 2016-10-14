package com.epam.homework.test;

import com.epam.homework.framework.browser.Browser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

public class BaseSetFixtureTest {
    @BeforeMethod(alwaysRun = true)
    public void init() {
    }

    @AfterClass
    public void afterAll() {
        Browser.getBrowser().close();
    }
}
