package com.epam.homework.test;

import com.epam.homework.ui.pages.ErrorLoginPage;
import com.epam.homework.ui.pages.LoginPage;
import com.epam.homework.ui.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.epam.homework.enums.DriverTimeouts.IMPLICIT_WAIT;
import static com.epam.homework.enums.DriverTimeouts.PAGE_LOAD;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Al on 26.09.2016.
 */
public class LoginTest {

    private static final String EMAIL_LOGIN = "tat2016al@mail.ru";
    private static final String CORRECT_PASS = "pass000";
    private static final String INCORRECT_PASS = "incorrect";
    private static final String MAILRU_URL = "https://mail.ru";

    private static final String INVALID_PASSWORD_MESSAGE =
            "Неверное имя пользователя или пароль. Проверьте правильность введенных данных.";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.firefox());
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getUnit());
        driver.manage().window().maximize();
    }

    @Test
    public void correctLogin() {
        driver.get(MAILRU_URL);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = loginPage.correctLogin(EMAIL_LOGIN, CORRECT_PASS);

        assertEquals(mainPage.getUsernameTitle(), EMAIL_LOGIN);
    }

    @Test
    public void incorrectLogin() {
        driver.get(MAILRU_URL);
        LoginPage loginPage = new LoginPage(driver);
        ErrorLoginPage errorPage = loginPage.incorrectLogin(EMAIL_LOGIN, INCORRECT_PASS);

        assertEquals(errorPage.getErrorMessage(), INVALID_PASSWORD_MESSAGE);
    }
}
