package com.epam.homework.product.pages;

import com.epam.homework.product.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.homework.product.enums.DriverTimeouts.EXPLICIT_WAIT;

public abstract class AbstractBasePage {
    private static final int TIME = 1000;
    protected WebDriver driver;

    protected AbstractBasePage() {
        this.driver = WebDriverFactory.getInstance();
    }

    protected WebElement waitForElement(By locator) {
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new WebDriverWait(driver, EXPLICIT_WAIT.getValue())
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
