package com.epam.homework.ui.pages;

import com.epam.homework.utility.FireFoxWebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by Al on 26.09.2016.
 */
public class LoginPage extends BasePage {

    private static final By LOGIN_INPUT_LOCATOR = By.xpath(".//input[@id='mailbox__login']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath(".//input[@id='mailbox__password']");
    private static final By LOGIN_FORM_SUBMIT_BUTTON_LOCATOR = By.xpath(".//input[@id='mailbox__auth__button']");


    public LoginPage() {
        super();
    }

    public MainPage correctLogin(String login, String pass) {
        doLogin(login, pass);
        return PageFactory.initElements(driver, MainPage.class);
    }

    public ErrorLoginPage incorrectLogin(String login, String pass) {
        doLogin(login, pass);
        return PageFactory.initElements(driver, ErrorLoginPage.class);

    }

    private void doLogin(String login, String pass) {
        driver.findElement(LOGIN_INPUT_LOCATOR).sendKeys(login);
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(pass);
        driver.findElement(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR).click();
    }
}
