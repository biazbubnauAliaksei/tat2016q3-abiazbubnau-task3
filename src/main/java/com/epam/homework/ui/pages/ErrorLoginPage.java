package com.epam.homework.ui.pages;

import org.openqa.selenium.By;


/**
 * Created by Al on 26.09.2016.
 */
public class ErrorLoginPage extends BasePage {
    private static final By ERROR_MSG_LOCATOR = By.xpath("//*[@id='frame']/div[contains(@class, 'b-login__errors')]");

    public ErrorLoginPage() {
        super();
    }

    public String getErrorMessage() {
        String message = driver.findElement(ERROR_MSG_LOCATOR).getText().trim();
        return message;
    }
}
