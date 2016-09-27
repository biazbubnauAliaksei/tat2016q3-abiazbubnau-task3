package com.epam.homework.test;

import com.epam.homework.constants.Constants;
import com.epam.homework.service.ifaces.LoginService;
import com.epam.homework.service.impl.LoginServiceImpl;
import com.epam.homework.ui.pages.LoginPage;
import com.epam.homework.utility.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

public class BaseMailruLoginTest {
    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    @Test
    public void loginToMail(){
        driver = WebDriverFactory.getInstance();
        driver.get(Constants.MAILRU_URL);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        LoginService service = new LoginServiceImpl(loginPage);
        service.login(Constants.EMAIL_LOGIN, Constants.CORRECT_PASS);
        assertTrue(service.isLoginSuccess());
    }

    @AfterClass
    public void killDriver() {
        driver.quit();
    }
}
