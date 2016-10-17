package com.epam.homework.test.runner.utility;

import org.apache.commons.cli.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CliUtility {
    private static final String PATH_TO_CHROME_DRIVER = "./src/main/resources/drivers/chromedriver.exe";
    private static final int DEFAULT_REMOTE_WEB_DRIVER_PORT = 4444;
    private static final String DEFAULT_HOST_ADDRESS_PATTERN = "http://127.0.0.1:%d";
    private static final String URL_PATH_TAIL = "/wd/hub";

    private static Options option = new Options();
    private static final String SPLITTER_COLON = ":";
    private static CommandLine commandLine;
    private static String[] args;

    public CliUtility(String[] args) {
        this.args = args;
        setOptions();
        commandLine = parse();
    }

    public static CommandLine parse() {
        CommandLineParser parser = new BasicParser();
        try {
            commandLine = parser.parse(option, args);
            if (commandLine.equals(null) || commandLine.hasOption("-?")) {
                help();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return commandLine;
    }

    public static List<String> getTestSuitesList() {
        List<String> result = new ArrayList<>();
        if (commandLine.hasOption("-s")) {
            result.add(commandLine.getOptionValue("-s"));
        }
        return result;
    }

    public static TypeOfBrowser getTypeOfBrowser() {
        TypeOfBrowser result = TypeOfBrowser.FIREFOX;
        if (commandLine.hasOption("-b")) {
            TypeOfBrowser target = TypeOfBrowser.valueOf(commandLine.getOptionValue("-b").toUpperCase());
            switch (target) {
                case CHROME:
                    result = TypeOfBrowser.CHROME;
                    break;

                case FIREFOX:
                    result = TypeOfBrowser.FIREFOX;
                    break;

                default:
                    throw new RuntimeException("Browser " + target + " not supported!");

            }
        }
        return result;
    }

    public static URL getHostAddress() {
        String pattern;
        URL result = null;
        if (commandLine.hasOption("-h")) {
            String target = commandLine.getOptionValue("-h");
            pattern = target + SPLITTER_COLON + getRemoteWebDriverPort() + URL_PATH_TAIL;
        } else {
            pattern = String.format(DEFAULT_HOST_ADDRESS_PATTERN + URL_PATH_TAIL, getRemoteWebDriverPort());
        }

        try {
            result = new URL(pattern);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getRemoteWebDriverPort() {
        int result = DEFAULT_REMOTE_WEB_DRIVER_PORT;
        if (commandLine.hasOption("-p")) {
            result = Integer.valueOf(commandLine.getOptionValue("-p"));
        }
        return result;
    }

    public static String getChromeDriverPath() {
        String result = PATH_TO_CHROME_DRIVER;
        if (commandLine.hasOption("-cp")) {
            result = commandLine.getOptionValue("-cp");
        }
        return result;
    }

    private static void help() {
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("mail-test", option);
        System.exit(0);
    }

    private void setOptions() {
        option.addOption("-cp", "--chromepath", false, " Path to chrome driver");
        option.addOption("-h", "--host", false, " Remote host address");
        option.addOption("-p", "--port", false, " Remote WebDriver port");
        option.addOption("-s", "--suite", false, " Path to test suite");
        option.addOption("-b", "--browser", false, " Browser type");
        option.addOption("-?", "--help", false, " Commands help");
    }

}
