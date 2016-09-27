package com.epam.homework.ui.pages;

import org.openqa.selenium.By;

public class ErrorLoginPage extends AbstractBasePage {
    private static final By ERROR_MSG_LOCATOR = By.xpath("//*[@id='frame']/div[contains(@class, 'b-login__errors')]");

    public ErrorLoginPage() {
        super();
    }

    public String getErrorMessage() {
        String message = driver.findElement(ERROR_MSG_LOCATOR).getText().trim();
        return message;
    }
}
