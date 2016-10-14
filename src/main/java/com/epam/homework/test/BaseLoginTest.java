package com.epam.homework.test;

import com.epam.homework.product.utility.factory.UserFactory;
import com.epam.homework.service.iface.LoginService;
import com.epam.homework.service.impl.LoginServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class BaseLoginTest extends BaseSetFixtureTest {

    @BeforeMethod
    @Test(description = "Supports testing preconditions.")
    public void loginToMail() {
        LoginService loginService = new LoginServiceImpl();
        loginService.login(UserFactory.createCorrectUser());
        assertTrue("Need to be logged in for supporting next tests", loginService.isLoginSuccess());
    }
}
