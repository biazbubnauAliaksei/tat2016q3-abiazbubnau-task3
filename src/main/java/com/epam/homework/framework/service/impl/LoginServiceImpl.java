package com.epam.homework.framework.service.impl;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.product.bean.User;
import com.epam.homework.product.page.ErrorLoginPage;
import com.epam.homework.product.utility.constant.Constants;
import com.epam.homework.framework.service.exception.AuthorizationException;
import com.epam.homework.framework.service.iface.LoginService;
import com.epam.homework.product.page.LoginPage;
import com.epam.homework.product.page.MainPage;
import com.epam.homework.framework.logging.Log;

public class LoginServiceImpl implements LoginService {
    private static final String MAILRU_URL = "https://mail.ru";

    @Override
    public void login(User user) {
        Log.info("Login to product initial page");
        Browser.getBrowser().open(MAILRU_URL);
        new LoginPage().typeLogin(user.getEmail())
                .typePassword(user.getPassword())
                .submitLogin();
    }

    @Override
    public boolean isLoginSuccess() throws AuthorizationException {
        Log.info("Checks is login successful");
        String title = new MainPage().getUsernameTitle();
        if (!title.equals(Constants.EMAIL_LOGIN)) {
            String message = getErrorMessage();
            throw new AuthorizationException(message);
        } else {
            return true;
        }
    }

    private String getErrorMessage() {
        Log.info("Getting error message.");
        return new ErrorLoginPage().getErrorMessage();
    }

}
