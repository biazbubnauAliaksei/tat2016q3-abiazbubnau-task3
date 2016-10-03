package com.epam.homework.product.pages;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.framework.element.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private static final By LOGIN_INPUT_LOCATOR = By.xpath(".//input[@id='mailbox__login']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath(".//input[@id='mailbox__password']");
    private static final By LOGIN_FORM_SUBMIT_BUTTON_LOCATOR = By.xpath(".//input[@id='mailbox__auth__button']");

    public LoginPage() {
    }

    public LoginPage typeLogin(String login) {
        new Element(LOGIN_INPUT_LOCATOR).typeValue(login);
        return this;

    }

    public LoginPage typePassword(String pass) {
        new Element(PASSWORD_INPUT_LOCATOR).typeValue(pass);
        return this;
    }

    public MainPage submitLogin() {
        new Element(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR).click();
        return PageFactory.initElements(Browser.getBrowser().getWrappedDriver(), MainPage.class);
    }
}
