package com.epam.homework.service.impl;

import com.epam.homework.product.beans.User;
import com.epam.homework.product.utility.constants.Constants;
import com.epam.homework.product.utility.exception.AuthorizationException;
import com.epam.homework.service.ifaces.LoginService;
import com.epam.homework.product.pages.LoginPage;
import com.epam.homework.product.pages.MainPage;
import com.epam.homework.product.utility.WebDriverFactory;
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
