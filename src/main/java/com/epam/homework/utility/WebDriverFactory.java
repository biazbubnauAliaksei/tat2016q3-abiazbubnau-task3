package com.epam.homework.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.epam.homework.enums.DriverTimeouts.IMPLICIT_WAIT;
import static com.epam.homework.enums.DriverTimeouts.PAGE_LOAD;

public final class WebDriverFactory {
    private static volatile WebDriver driver;

    private WebDriverFactory() {
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = getChromeDriver();
            return driver;
        }
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
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        configureDriver();
        return driver;
    }

    private static void configureDriver() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getUnit());
        driver.manage().window().maximize();
    }
}
