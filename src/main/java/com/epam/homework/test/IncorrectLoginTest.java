package com.epam.homework.test;

import com.epam.homework.product.utility.factory.UserFactory;
import com.epam.homework.framework.service.exception.AuthorizationException;
import com.epam.homework.framework.service.iface.LoginService;
import com.epam.homework.framework.service.impl.LoginServiceImpl;
import org.testng.annotations.Test;

public class IncorrectLoginTest extends BaseSetFixtureTest {
    private static final String INVALID_PASSWORD_MESSAGE =
            "Неверное имя пользователя или пароль. Проверьте правильность введенных данных.";

    @Test(expectedExceptions = AuthorizationException.class, expectedExceptionsMessageRegExp = INVALID_PASSWORD_MESSAGE,
            description = "Should not be login, password is incorrect. Exception when login is incorrect")
    public void incorrectLogin() {
        LoginService service = new LoginServiceImpl();
        service.login(UserFactory.createRandomUser());
        service.isLoginSuccess();
    }
}
