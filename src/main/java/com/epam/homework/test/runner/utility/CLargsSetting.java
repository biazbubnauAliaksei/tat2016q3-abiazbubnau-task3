package com.epam.homework.test.runner.utility;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class CLargsSetting {
    private static CLargsSetting instance;

    @Option(name = "-b", usage = " Browser Type")
    public TypeOfBrowser typeOfBrowser;

    @Option(name = "-p", usage = " Remote WebDriver port")
    public int port;

    @Option(name = "-cp", usage = "Path to chrome driver")
    public String chromePath;

    @Option(name = "-s", usage = " Path to test suite")
    public List<String> testsSuite;

    @Option(name = "-h", usage = " Remote host address")
    public String host;

    @Argument
    public List<String> arguments = new ArrayList<String>();

    public static CLargsSetting getInstance() {
        if (instance == null) {
            instance = new CLargsSetting();
        } return instance;
    }

    public static class BrowserTypeConverter {
        public TypeOfBrowser convert(String name) {
            return TypeOfBrowser.valueOf(name.toUpperCase());
        }
    }
}
