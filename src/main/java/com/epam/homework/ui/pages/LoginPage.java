package com.epam.homework.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractBasePage {

    private static final By LOGIN_INPUT_LOCATOR = By.xpath(".//input[@id='mailbox__login']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath(".//input[@id='mailbox__password']");
    private static final By LOGIN_FORM_SUBMIT_BUTTON_LOCATOR = By.xpath(".//input[@id='mailbox__auth__button']");

    public LoginPage() {
        super();
        waitForElement(LOGIN_INPUT_LOCATOR);
    }

    public LoginPage typeLogin(String login) {
        driver.findElement(LOGIN_INPUT_LOCATOR).sendKeys(login);
        return this;
    }

    public LoginPage typePassword(String pass) {
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(pass);
        return this;
    }

    public MainPage submitLogin() {
        driver.findElement(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR).click();
        return PageFactory.initElements(driver, MainPage.class);
    }
}
