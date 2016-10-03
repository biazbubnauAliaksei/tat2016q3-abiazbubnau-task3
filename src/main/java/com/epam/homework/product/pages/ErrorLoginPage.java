package com.epam.homework.product.pages;

import com.epam.homework.framework.element.Element;
import org.openqa.selenium.By;

public class ErrorLoginPage {
    private static final By ERROR_MSG_LOCATOR = By.xpath("//*[@id='frame']/div[contains(@class, 'b-login__errors')]");

    public ErrorLoginPage() {
    }

    public String getErrorMessage() {
        String message = new Element(ERROR_MSG_LOCATOR).getText();
        return message;
    }
}
