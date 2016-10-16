package com.epam.homework.test.runner;

import org.testng.TestNG;
import java.util.Arrays;
import java.util.List;

public class TestRunner {
    static final String SUIT_PATH = "./test-suites/";
    static final String COMMON_TESTS = "login-suite.xml";

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> files = Arrays.asList(SUIT_PATH + COMMON_TESTS);
        testNG.setTestSuites(files);
        testNG.run();
    }
}
