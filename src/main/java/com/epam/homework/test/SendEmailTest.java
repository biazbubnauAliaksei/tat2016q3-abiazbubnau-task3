package com.epam.homework.test;

import com.epam.homework.framework.Browser;
import com.epam.homework.product.beans.Message;
import com.epam.homework.product.beans.User;
import com.epam.homework.product.utility.constants.Constants;
import com.epam.homework.service.iface.LoginService;
import com.epam.homework.service.iface.MailService;
import com.epam.homework.service.impl.LoginServiceImpl;
import com.epam.homework.service.impl.MailServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class SendEmailTest {

    private static final String ADDRESS_ERROR_MESSAGE = "Не указан адрес получателя";
    private MailService service;
    private Browser browser;

    @BeforeMethod
    public void setUp() {
        browser = Browser.getBrowser();
        service = new MailServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        browser.close();
    }

    @Test(description = "Supports testing preconditions")
    public void loginToMail() {
        LoginService loginService = new LoginServiceImpl();
        User user = new User(Constants.EMAIL_LOGIN, Constants.CORRECT_PASS);
        loginService.login(user);
        assertTrue("Correct precondition for supporting tests.", loginService.isLoginSuccess());
    }

    @Test(dependsOnMethods = "loginToMail", description = "Correct mail sending process.")
    public void sendMailAllFieldsCorrect() {
        String body = generateText();
        Message message = new Message(Constants.EMAIL_LOGIN, body, body);
        service.sendMessage(message);
        assertTrue("Message should be sent. All fields in order.", service.isMessageSent(message));
    }

    @Test(dependsOnMethods = "loginToMail", description = "Opportunity to send mail with incomplete data.")
    public void sendMailWithAddressNoSubjectNoBody() {
        Message message = new Message(Constants.EMAIL_LOGIN, Constants.EMPTY, Constants.EMPTY);
        service.sendMessage(message);
        assertTrue("Message should be sent. Not all fields are necessary.", service.isMessageSent(message));
    }

    @Test(dependsOnMethods = "loginToMail", description = "Inability of sending message without email address.",
            expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = ADDRESS_ERROR_MESSAGE)
    public void sendMailNotFilledAddress() {
        Message message = new Message(Constants.EMPTY, Constants.EMPTY, Constants.EMPTY);
        service.sendIncorrectMessage(message);
    }

    public static String generateText() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }
}
