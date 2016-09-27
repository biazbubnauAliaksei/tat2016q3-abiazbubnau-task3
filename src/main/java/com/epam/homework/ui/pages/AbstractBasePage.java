package com.epam.homework.ui.pages;

import com.epam.homework.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.homework.enums.DriverTimeouts.EXPLICIT_WAIT;

public abstract class AbstractBasePage {
    protected WebDriver driver;

    protected AbstractBasePage() {
        this.driver = WebDriverFactory.getInstance();
    }

    protected WebElement waitForElement(By locator){
        return new WebDriverWait(driver, EXPLICIT_WAIT.getValue()).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
