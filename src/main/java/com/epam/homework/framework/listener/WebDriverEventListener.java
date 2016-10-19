package com.epam.homework.framework.listener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import com.epam.homework.framework.logging.Log;

public class WebDriverEventListener extends AbstractWebDriverEventListener {

    public static WebDriverEventListener create() {
        return new WebDriverEventListener();
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        Log.debug(url + " - Before navigate");
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        Log.debug(url + " - Navigation complete");
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        Log.debug(by + " - Searching for element");
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        Log.debug(by + " - Element has been found");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        Log.debug(element.toString() + " - Before click on element");
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        Log.debug("Done.");
    }
}
