package com.epam.homework.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractBasePage {
    private static final By USERNAME_LOCATOR = By.xpath("//*[@id='PH_user-email']");
    private static final By NEW_MAIL_BUTTON_LOCATOR = By.xpath("//div[@id='b-toolbar__left']//a[@data-name='compose']");
    private static final By SENT_LINK_LOCATOR = By.xpath(".//div[@data-id='500000']/a");

    public MainPage() {
        super();
    }

    public String getUsernameTitle() {
        String message = driver.findElement(USERNAME_LOCATOR).getText().trim();
        return message;
    }

    public ComposePage clickCompose() {
        driver.findElement(NEW_MAIL_BUTTON_LOCATOR).click();
        return PageFactory.initElements(driver, ComposePage.class);
    }

    public MainPage clickSent() {
        driver.findElement(SENT_LINK_LOCATOR).click();
        return PageFactory.initElements(driver, MainPage.class);
    }

}
