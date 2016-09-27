package com.epam.homework.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Al on 26.09.2016.
 */
public class MainPage {
    private static final By USERNAME_LOCATOR = By.xpath("//*[@id='PH_user-email']");
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUsernameTitle() {
        String message = driver.findElement(USERNAME_LOCATOR).getText().trim();
        return message;
    }
}
