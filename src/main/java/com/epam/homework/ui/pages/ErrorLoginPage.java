package com.epam.homework.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Al on 26.09.2016.
 */
public class ErrorLoginPage {
    private static final By ERROR_MSG_LOCATOR = By.xpath("//*[@id='frame']/div[contains(@class, 'b-login__errors')]");

    private WebDriver driver;

    public ErrorLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getErrorMessage() {
        String message = driver.findElement(ERROR_MSG_LOCATOR).getText().trim();
        return message;
    }
}
