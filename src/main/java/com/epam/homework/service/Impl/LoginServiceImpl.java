package com.epam.homework.service.impl;

import com.epam.homework.beans.User;
import com.epam.homework.constants.Constants;
import com.epam.homework.exception.AuthorizationException;
import com.epam.homework.service.ifaces.LoginService;
import com.epam.homework.ui.pages.LoginPage;
import com.epam.homework.ui.pages.MainPage;
import com.epam.homework.utility.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class LoginServiceImpl implements LoginService {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;

    public LoginServiceImpl() {
        this.loginPage = new LoginPage();
        this.driver = WebDriverFactory.getInstance();
    }

    @Override
    public void login(User user) {
        driver.get(Constants.MAILRU_URL);
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
        String message = driver.findElement(Constants.ERROR_MSG_LOCATOR).getText().trim();
        return message;
    }
}
