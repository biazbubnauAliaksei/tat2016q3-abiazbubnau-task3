package com.epam.homework.service.impl;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.product.beans.User;
import com.epam.homework.product.utility.constants.Constants;
import com.epam.homework.product.utility.exception.AuthorizationException;
import com.epam.homework.service.iface.LoginService;
import com.epam.homework.product.pages.LoginPage;
import com.epam.homework.product.pages.MainPage;

public class LoginServiceImpl implements LoginService {
    private Browser browser;
    private LoginPage loginPage;
    private MainPage mainPage;

    public LoginServiceImpl() {
        this.loginPage = new LoginPage();
        this.browser = Browser.getBrowser();
    }

    @Override
    public void login(User user) {
        browser.open(Constants.MAILRU_URL);
        mainPage = loginPage.typeLogin(user.getEmail())
                .typePassword(user.getPassword())
                .submitLogin();
    }

    @Override
    public boolean isLoginSuccess() throws AuthorizationException {
        String title = mainPage.getUsernameTitle();
        if (!title.equals(Constants.EMAIL_LOGIN)) {
            String message = getErrorMessage();
            throw new RuntimeException(message);
        } else {
            return true;
        }
    }

    private String getErrorMessage() {
        String message = browser.findElement(Constants.ERROR_MSG_LOCATOR).getText().trim();
        return message;
    }

}
