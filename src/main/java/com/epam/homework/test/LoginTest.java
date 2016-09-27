package com.epam.homework.test;

import com.epam.homework.ui.pages.ErrorLoginPage;
import com.epam.homework.ui.pages.LoginPage;
import com.epam.homework.ui.pages.MainPage;
import com.epam.homework.utility.FireFoxWebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void setUp() {
        driver = FireFoxWebDriverFactory.getInstance();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getUnit());
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void correctLogin() {
        driver.get(MAILRU_URL);
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.correctLogin(EMAIL_LOGIN, CORRECT_PASS);
        assertEquals(mainPage.getUsernameTitle(), EMAIL_LOGIN);
    }

    @Test
    public void incorrectLogin() {
        driver.get(MAILRU_URL);
        LoginPage loginPage = new LoginPage();
        ErrorLoginPage errorPage = loginPage.incorrectLogin(EMAIL_LOGIN, INCORRECT_PASS);
        assertEquals(errorPage.getErrorMessage(), INVALID_PASSWORD_MESSAGE);
    }
}
