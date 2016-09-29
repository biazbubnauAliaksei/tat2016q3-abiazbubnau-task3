package com.epam.homework.test;

import com.epam.homework.beans.User;
import com.epam.homework.service.impl.LoginServiceImpl;
import com.epam.homework.utility.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import com.epam.homework.constants.Constants;

public class LoginTest {

    private static final String INCORRECT_PASS = "incorrect";
    private static final String INVALID_PASSWORD_MESSAGE =
            "Неверное имя пользователя или пароль. Проверьте правильность введенных данных.";

    private LoginServiceImpl service;

    @BeforeMethod
    public void setUp() {
        service = new LoginServiceImpl();
    }

    @Test
    public void correctLogin() {
        doLogin(Constants.EMAIL_LOGIN, Constants.CORRECT_PASS);
        assertTrue(service.isLoginSuccess());
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = INVALID_PASSWORD_MESSAGE)
    public void incorrectLogin() throws Exception {
        doLogin(Constants.EMAIL_LOGIN, INCORRECT_PASS);
        service.isLoginSuccess();
    }

    private void doLogin(String login, String pass) {
        User user = new User(login, pass);
        service.login(user);
    }
}
