package com.epam.homework.test.runner;

import com.epam.homework.framework.listener.TestListener;
import com.epam.homework.test.runner.utility.CLargsHandler;
import com.epam.homework.test.runner.utility.CLargsSetting;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.TestNG;

public class TestRunner {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        CLargsSetting settings = CLargsSetting.getInstance();
        CmdLineParser parser = new CmdLineParser(settings);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println("Incorrect argument(s)!");
        }
        testNG.addListener(new TestListener());
        testNG.setTestSuites(CLargsHandler.getTestSuitesList());
        testNG.run();
    }
}
