package com.epam.homework.framework;

import com.epam.homework.product.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class Browser implements WrapsDriver {

    public static final int ELEMENT_WAIT_TIMEOUT_SECONDS = 10;

    private static Browser instance;
    private WebDriver driver;

    private Browser() {
        driver = WebDriverFactory.getInstance();
    }

    public static synchronized Browser getBrowser() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }

    public void close() {
        try {
            if (getWrappedDriver() != null) {
                getWrappedDriver().quit();
            }
        } finally {
            instance = null;
        }
    }

    @Override
    public WebDriver getWrappedDriver() {
        return driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

    public boolean isElementVisible(By by) {
        if (!isElementPresent(by)) {
            return false;
        }
        return findElement(by).isDisplayed();
    }

    public void waitForElementIsPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElementIsVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementIsNotVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
