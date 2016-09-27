package com.epam.homework.test;

import com.epam.homework.service.ifaces.LoginService;
import com.epam.homework.service.impl.LoginServiceImpl;
import com.epam.homework.ui.pages.LoginPage;
import com.epam.homework.utility.WebDriverFactory;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import com.epam.homework.constants.Constants;

public class LoginTest {

    private static final String INCORRECT_PASS = "incorrect";
    private static final String INVALID_PASSWORD_MESSAGE =
            "Неверное имя пользователя или пароль. Проверьте правильность введенных данных.";

    private WebDriver driver;
    private LoginPage loginPage;
    private LoginServiceImpl service;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = WebDriverFactory.getInstance();
        loginPage = new LoginPage();
        service = new LoginServiceImpl(loginPage);
    }

    @AfterMethod
    public void killDriver(){
        driver.quit();
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

    private void doLogin(String login, String pass){
        driver.get(Constants.MAILRU_URL);
        service.login(login, pass);
    }
}
