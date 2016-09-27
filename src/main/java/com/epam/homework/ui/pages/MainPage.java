package com.epam.homework.ui.pages;

import org.openqa.selenium.By;

/**
 * Created by Al on 26.09.2016.
 */
public class MainPage extends BasePage {
    private static final By USERNAME_LOCATOR = By.xpath("//*[@id='PH_user-email']");


    public MainPage() {
        super();
    }

    public String getUsernameTitle() {
        String message = driver.findElement(USERNAME_LOCATOR).getText().trim();
        return message;
    }

//    public ComposePage clickCompose(){
//
//
//    }
}
