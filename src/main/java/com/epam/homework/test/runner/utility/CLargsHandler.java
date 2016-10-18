package com.epam.homework.test.runner.utility;

import static org.apache.commons.lang3.ObjectUtils.Null;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CLargsHandler {
    private static final String PATH_TO_CHROME_DRIVER = "./src/main/resources/drivers/chromedriver.exe";
    private static final int DEFAULT_REMOTE_WEB_DRIVER_PORT = 4444;
    private static final int INDEX_0 = 0;
    private static final String URL_PATH_TAIL = "/wd/hub";
    private static final String DEFAULT_HOST_ADDRESS_PATTERN = "http://127.0.0.1:%d";

    private static CLargsSetting setting = CLargsSetting.getInstance();

    public static String getChromeDriverPath() {
        String path = setting.chromePath;
        if (path == null) {
            path = PATH_TO_CHROME_DRIVER;
        }
        return path;
    }

    public static int getRemoteWebDriverPort() {
        int port = setting.port;
        if (port == INDEX_0) {
            port = DEFAULT_REMOTE_WEB_DRIVER_PORT;
        }
        return port;
    }

    public static List<String> getTestSuitesList() {
        List<String> result = new ArrayList<>();
        if (setting.testsSuite == null) {
            return result;
        }
        if (setting.testsSuite.size() > 0) {
            result = setting.testsSuite;
        }
        return result;
    }

    public static TypeOfBrowser getBrowserType() {
        if (setting.typeOfBrowser == TypeOfBrowser.CHROME) {
            return TypeOfBrowser.CHROME;
        }  return TypeOfBrowser.FIREFOX;
    }

    public static URL getWebDriverURL() {
        String target = setting.host;
        URL result = null;
        if (isEmpty(target)) {
            target = String.format(DEFAULT_HOST_ADDRESS_PATTERN, getRemoteWebDriverPort());
        }
        try {
            result = new URL(target + URL_PATH_TAIL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
