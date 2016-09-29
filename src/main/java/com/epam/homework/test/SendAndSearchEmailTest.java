package com.epam.homework.test;

import com.epam.homework.beans.Message;
import com.epam.homework.beans.User;
import com.epam.homework.service.ifaces.LoginService;
import com.epam.homework.service.ifaces.MailService;
import com.epam.homework.service.impl.LoginServiceImpl;
import com.epam.homework.service.impl.MailServiceImpl;
import com.epam.homework.utility.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.epam.homework.constants.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class SendAndSearchEmailTest {

    private static final String EMPTY = "";
    WebDriver driver;
    MailService service;

    @BeforeMethod
    public void setUp() {
        service = new MailServiceImpl();
    }

    @Test
    public void loginToMail() {
        driver = WebDriverFactory.getInstance();
        LoginService loginService = new LoginServiceImpl(driver);
        driver.get(Constants.MAILRU_URL);
        User user = new User(Constants.EMAIL_LOGIN, Constants.CORRECT_PASS);
        loginService.login(user);
        assertTrue(loginService.isLoginSuccess());
    }

    @Test(dependsOnMethods = "loginToMail")
    public void sendMailAllFieldsCorrect() {
        String body = generateText();
        Message message = new Message(Constants.EMAIL_LOGIN, body, body);
        service.sendMessage(message);
    }

    private String generateText() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("Hh:mm:ss");
        return sdf.format(date);
    }

    @Test(dependsOnMethods = "loginToMail")
    public void sendMailNotFilledAddress() {

    }

    @Test(dependsOnMethods = "loginToMail")
    public void sendMailWithAddressNoBodyNoSubject() {
        Message message = new Message(Constants.EMAIL_LOGIN, EMPTY, EMPTY);
        service.sendMessage(message);
    }
}
