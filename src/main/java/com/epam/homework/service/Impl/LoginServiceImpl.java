package com.epam.homework.service.impl;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.product.beans.User;
import com.epam.homework.product.pages.ErrorLoginPage;
import com.epam.homework.product.utility.constants.Constants;
import com.epam.homework.product.utility.exception.AuthorizationException;
import com.epam.homework.service.iface.LoginService;
import com.epam.homework.product.pages.LoginPage;
import com.epam.homework.product.pages.MainPage;

public class LoginServiceImpl implements LoginService {

    @Override
    public void login(User user) {
        Browser.getBrowser().open(Constants.MAILRU_URL);
        new LoginPage().typeLogin(user.getEmail())
                .typePassword(user.getPassword())
                .submitLogin();
    }

    @Override
    public boolean isLoginSuccess() throws AuthorizationException {
        String title = new MainPage().getUsernameTitle();
        if (!title.equals(Constants.EMAIL_LOGIN)) {
            String message = getErrorMessage();
            throw new AuthorizationException(message);
        } else {
            return true;
        }
    }

    private String getErrorMessage() {
        return new ErrorLoginPage().getErrorMessage();
    }

}
