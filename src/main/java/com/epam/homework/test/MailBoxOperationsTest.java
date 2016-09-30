package com.epam.homework.test;

import com.epam.homework.framework.Browser;
import com.epam.homework.product.beans.Message;
import com.epam.homework.product.beans.User;
import com.epam.homework.product.utility.constants.Constants;
import com.epam.homework.service.ifaces.LoginService;
import com.epam.homework.service.impl.LoginServiceImpl;
import com.epam.homework.service.impl.MailServiceImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class MailBoxOperationsTest {
    private static final String EMPTY = "";
    private Browser browser;
    private MailServiceImpl service;
    private Message message;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        browser = Browser.getBrowser();
        service = new MailServiceImpl();
    }

    @AfterMethod
    public void tearDown() {
        service = null;
        browser.close();
    }

    @Test(description = "Supports testing preconditions.")
    public void loginToMail() {
        LoginService loginService = new LoginServiceImpl();
        User user = new User(Constants.EMAIL_LOGIN, Constants.CORRECT_PASS);
        loginService.login(user);
        assertTrue(loginService.isLoginSuccess());
    }

    @Test(dependsOnMethods = "loginToMail", description = "Message could be puts in folder 'Trash'.")
    public void messagePutsInTrash() {
        String content = SendEmailTest.generateText();
        message = new Message(Constants.EMPTY, content, content);
        service.putInDraft(message);
        assertTrue("Message should be in trash.", service.isMessageInTrash(message));
    }

    @Test(dependsOnMethods = {"loginToMail", "messagePutsInTrash"},
            description = "Message should be desappeared from 'Trash' folder")
    public void deleteMessageFromTrash() {
        assertFalse("Message could not be in trash folder", service.isMessageInTrash(message));
    }
}
