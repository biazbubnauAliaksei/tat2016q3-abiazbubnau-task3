package com.epam.homework.test;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.product.beans.Message;
import com.epam.homework.product.utility.builders.MessageBuilder;
import com.epam.homework.product.utility.constants.Constants;
import com.epam.homework.product.utility.factories.UserFactory;
import com.epam.homework.service.iface.LoginService;
import com.epam.homework.service.impl.LoginServiceImpl;
import com.epam.homework.service.impl.MailServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.apache.commons.lang3.RandomStringUtils.*;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class MailBoxOperationsTest {
    private MailServiceImpl service;
    private Message message;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        service = new MailServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        Browser.getBrowser().close();
    }

    @Test(description = "Supports testing preconditions.")
    public void loginToMail() {
        LoginService loginService = new LoginServiceImpl();
        loginService.login(UserFactory.createCorrectUser());
        assertTrue("Need to be logged in for supporting next tests", loginService.isLoginSuccess());
    }

    @Test(dependsOnMethods = "loginToMail", description = "Message could be puts in folder 'Trash'.")
    public void messagePutsInTrash() {
        String content = random(Constants.CONTENT_INDEX);
        message = new MessageBuilder()
                .body(EMPTY)
                .email(content)
                .subject(content)
                .build();
        service.putInDraft();
        assertTrue("Message should be in trash.", service.isMessageInTrash(message));
    }

    @Test(dependsOnMethods = {"loginToMail", "messagePutsInTrash"},
            description = "Message should be desappeared from 'Trash' folder")
    public void deleteMessageFromTrash() {
        service.deleteMessage(message);
        assertFalse("Message could not be in trash folder", service.isMessageInTrash(message));
    }
}
