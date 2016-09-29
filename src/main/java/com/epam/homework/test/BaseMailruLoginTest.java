package com.epam.homework.test;

import com.epam.homework.beans.User;
import com.epam.homework.constants.Constants;
import com.epam.homework.service.ifaces.LoginService;
import com.epam.homework.service.impl.LoginServiceImpl;
import com.epam.homework.utility.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

public class BaseMailruLoginTest {
    protected WebDriver driver;

    @BeforeMethod
    @Test
    public void loginToMail() {
        driver = WebDriverFactory.getInstance();
        LoginService service = new LoginServiceImpl(driver);
        driver.get(Constants.MAILRU_URL);
        User user = new User(Constants.EMAIL_LOGIN, Constants.CORRECT_PASS);
        service.login(user);
        assertTrue(service.isLoginSuccess());
    }

}
