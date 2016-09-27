package com.epam.homework.ui.pages;

import com.epam.homework.utility.FireFoxWebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.homework.enums.DriverTimeouts.EXPLICIT_WAIT;

/**
 * Created by Al on 27.09.2016.
 */
public class BasePage {
    protected WebDriver driver;

    protected BasePage() {
        this.driver = FireFoxWebDriverFactory.getInstance();
    }

    protected WebElement waitForElement(By locator){
        return new WebDriverWait(driver, EXPLICIT_WAIT.getValue()).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
