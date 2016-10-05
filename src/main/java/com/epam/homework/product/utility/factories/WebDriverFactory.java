package com.epam.homework.product.utility.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static com.epam.homework.product.enums.DriverTimeouts.IMPLICIT_WAIT;
import static com.epam.homework.product.enums.DriverTimeouts.PAGE_LOAD;

public final class WebDriverFactory {
    private static volatile WebDriver driver;
    private static final String DOWNLOADS_PATH = "D:\\downloads";
    private static final String PATH_TO_CHROME_DRIVER = "./src/main/resources/drivers/chromedriver.exe";

    private WebDriverFactory() {
    }

    public static WebDriver getInstance() {
        driver = getChromeDriver();
        return driver;
    }

    private static WebDriver getMozillaDriver() {
        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.firefox());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        configureDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getChromeDriver() {
        String chromeProperty = "webdriver.chrome.driver";
        String defaultContentSettingsPopups = "profile.default_content_settings.popups";
        String downloadDefauldDirectory = "download.default_directory";
        String prefs = "prefs";

        System.setProperty(chromeProperty, PATH_TO_CHROME_DRIVER);

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put(defaultContentSettingsPopups, 0);
        chromePrefs.put(downloadDefauldDirectory, DOWNLOADS_PATH);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(prefs, chromePrefs);

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        driver = new ChromeDriver(cap);
        configureDriver();
        return driver;
    }

    private static void configureDriver() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getUnit());
        driver.manage().window().maximize();
    }
}
