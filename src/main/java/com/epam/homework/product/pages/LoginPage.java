package com.epam.homework.product.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractBasePage {

    private static final By LOGIN_INPUT_LOCATOR = By.xpath(".//input[@id='mailbox__login']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath(".//input[@id='mailbox__password']");
    private static final By LOGIN_FORM_SUBMIT_BUTTON_LOCATOR = By.xpath(".//input[@id='mailbox__auth__button']");

    public LoginPage() {
    }

    public LoginPage typeLogin(String login) {
        browser.findElement(LOGIN_INPUT_LOCATOR).sendKeys(login);
        return this;

    }

    public LoginPage typePassword(String pass) {
        browser.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(pass);
        return this;
    }

    public MainPage submitLogin() {
        browser.clickElement(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR);
        return PageFactory.initElements(browser.getWrappedDriver(), MainPage.class);
    }
}
