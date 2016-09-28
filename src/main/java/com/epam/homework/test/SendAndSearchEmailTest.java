package com.epam.homework.test;

import com.epam.homework.service.ifaces.LoginService;
import com.epam.homework.service.impl.LoginServiceImpl;
import com.epam.homework.ui.pages.ComposePage;
import com.epam.homework.ui.pages.LoginPage;
import com.epam.homework.utility.WebDriverFactory;
import org.junit.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.epam.homework.constants.Constants;

import static org.junit.Assert.assertTrue;

public class SendAndSearchEmailTest extends BaseMailruLoginTest {

    @BeforeMethod
    @Test
    public void loginToMail(){
        driver = WebDriverFactory.getInstance();
        driver.get(Constants.MAILRU_URL);
        loginPage = new LoginPage();
        LoginService service = new LoginServiceImpl(loginPage);
        service.login(Constants.EMAIL_LOGIN, Constants.CORRECT_PASS);
        assertTrue(service.isLoginSuccess());
    }

    @Test
    public void sendMail(){


    }
}
