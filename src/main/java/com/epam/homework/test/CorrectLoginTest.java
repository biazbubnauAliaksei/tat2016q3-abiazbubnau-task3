package com.epam.homework.test;

import com.epam.homework.product.utility.factory.UserFactory;
import com.epam.homework.framework.service.iface.LoginService;
import com.epam.homework.framework.service.impl.LoginServiceImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CorrectLoginTest extends BaseSetFixtureTest {

    @Test(description = "Correct login process.")
    public void correctLogin() {
        LoginService service = new LoginServiceImpl();
        service.login(UserFactory.createCorrectUser());
        assertTrue(service.isLoginSuccess(), "Should be logged in. Username and password is correct.");
    }
}
