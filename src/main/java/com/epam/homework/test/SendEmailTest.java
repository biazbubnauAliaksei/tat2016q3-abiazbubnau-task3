package com.epam.homework.test;

import static org.junit.Assert.assertTrue;

import com.epam.homework.beans.Message;
import com.epam.homework.beans.User;
import com.epam.homework.service.ifaces.LoginService;
import com.epam.homework.service.ifaces.MailService;
import com.epam.homework.service.impl.LoginServiceImpl;
import com.epam.homework.service.impl.MailServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.epam.homework.constants.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SendEmailTest {

    private static final String EMPTY = "";

    MailService service;

    @BeforeMethod
    public void setUp() {
        service = new MailServiceImpl();
    }

    @Test
    public void loginToMail() {
        LoginService loginService = new LoginServiceImpl();
        User user = new User(Constants.EMAIL_LOGIN, Constants.CORRECT_PASS);
        loginService.login(user);
        assertTrue(loginService.isLoginSuccess());
    }

    @Test(dependsOnMethods = "loginToMail")
    public void sendMailAllFieldsCorrect() throws InterruptedException {
        String body = generateText();
        Message message = new Message(Constants.EMAIL_LOGIN, body, body);
        service.sendMessage(message);
        assertTrue(service.isMessageSent(message));
    }

    @Test(dependsOnMethods = "loginToMail")
    public void sendMailWithAddressNoSubjectNoBody() {
        Message message = new Message(Constants.EMAIL_LOGIN, EMPTY, EMPTY);
        service.sendMessage(message);
        assertTrue(service.isMessageSent(message));
    }

    private String generateText() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    @Test(dependsOnMethods = "loginToMail")
    public void sendMailNotFilledAddress() {
        Message message = new Message(EMPTY, EMPTY, EMPTY);
        service.sendMessage(message);
    }
}
