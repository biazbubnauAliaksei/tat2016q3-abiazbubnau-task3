package com.epam.homework.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Al on 27.09.2016.
 */
public final class FireFoxWebDriverFactory {
    private static volatile WebDriver driver;

    private FireFoxWebDriverFactory(){
    }

    public static WebDriver getInstance(){
        if (driver == null){
            try {
                driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.firefox());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        } return driver;
    }
}
