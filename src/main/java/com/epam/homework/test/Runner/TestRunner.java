package com.epam.homework.test.runner;

import org.testng.TestNG;
import java.util.Arrays;
import java.util.List;

public class TestRunner {
    static final String SUIT_PATH = "./test-suits/";
    static final String COMMON_TESTS = "suit.xml";

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
//        testNG.addListener(TestListner.class);
        List<String> files = Arrays.asList(SUIT_PATH + COMMON_TESTS, SUIT_PATH);
        testNG.setTestSuites(files);
        testNG.run();
    }
}
