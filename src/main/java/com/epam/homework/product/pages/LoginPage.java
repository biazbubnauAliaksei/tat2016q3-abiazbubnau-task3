package com.epam.homework.product.pages;

import com.epam.homework.framework.element.Element;
import org.openqa.selenium.By;

public class LoginPage {

    private static final By LOGIN_INPUT_LOCATOR = By.xpath(".//input[@id='mailbox__login']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath(".//input[@id='mailbox__password']");
    private static final By LOGIN_FORM_SUBMIT_BUTTON_LOCATOR = By.xpath(".//input[@id='mailbox__auth__button']");

    private Element loginInput = new Element(LOGIN_INPUT_LOCATOR);
    private Element passwordInput = new Element(PASSWORD_INPUT_LOCATOR);
    private Element submitButton = new Element(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR);

    public LoginPage typeLogin(String login) {
        loginInput.typeValue(login);
        return this;
    }

    public LoginPage typePassword(String pass) {
        passwordInput.typeValue(pass);
        return this;
    }

    public MainPage submitLogin() {
        submitButton.click();
        return new MainPage();
    }
}
