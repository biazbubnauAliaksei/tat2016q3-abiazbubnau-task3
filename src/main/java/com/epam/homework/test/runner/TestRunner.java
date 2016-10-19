package com.epam.homework.test.runner;

import com.epam.homework.framework.listener.TestListener;
import com.epam.homework.test.runner.utility.CLargsHandler;
import com.epam.homework.test.runner.utility.CLargsSetting;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.TestNG;

import java.util.Arrays;

public class TestRunner {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        CLargsSetting settings = CLargsSetting.getInstance();
        CmdLineParser parser = new CmdLineParser(settings);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
        testNG.addListener(new TestListener());
        testNG.setTestSuites(CLargsHandler.getTestSuitesList());
        testNG.setTestSuites(Arrays.asList("./test-suites/login-suite.xml"));
        testNG.run();
    }
}
