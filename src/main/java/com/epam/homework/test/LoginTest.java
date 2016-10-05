package com.epam.homework.test;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.product.utility.exception.AuthorizationException;
import com.epam.homework.product.utility.factories.UserFactory;
import com.epam.homework.service.impl.LoginServiceImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest {
    private static final String INVALID_PASSWORD_MESSAGE =
            "Неверное имя пользователя или пароль. Проверьте правильность введенных данных.";

    private LoginServiceImpl service;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        service = new LoginServiceImpl();
    }

    @AfterMethod
    public void tearDown() {
        Browser.getBrowser().close();
    }

    @Test(expectedExceptions = AuthorizationException.class, expectedExceptionsMessageRegExp = INVALID_PASSWORD_MESSAGE,
            description = "Should not be login, password is incorrect. Exception when login is incorrect")
    public void incorrectLogin() {
        service.login(UserFactory.createRandomUser());
        service.isLoginSuccess();
    }

    @Test(description = "Correct login process.")
    public void correctLogin() {
        service.login(UserFactory.createCorrectUser());
        assertTrue(service.isLoginSuccess(), "Should be logged in. Username and password is correct.");
    }

}
