package com.epam.homework.test;

import com.epam.homework.framework.browser.Browser;
import com.epam.homework.product.beans.Message;
import com.epam.homework.product.beans.MessageWithAttach;
import com.epam.homework.product.utility.builders.MessageBuilder;
import com.epam.homework.product.utility.builders.MessageWithAttachBuilder;
import com.epam.homework.product.utility.constants.Constants;
import com.epam.homework.product.utility.exception.MessageSentException;
import com.epam.homework.product.utility.factories.MessageFactory;
import com.epam.homework.product.utility.factories.UserFactory;
import com.epam.homework.service.iface.LoginService;
import com.epam.homework.service.iface.MailService;
import com.epam.homework.service.impl.LoginServiceImpl;
import com.epam.homework.service.impl.MailServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.apache.commons.lang3.RandomStringUtils.*;

public class SendEmailTest {

    private static final String ADDRESS_ERROR_MESSAGE = "Не указан адрес получателя";
    private static final String ATTACHED_FILE_PATH_PATTERN = "./src/main/resources/file-to-upload%d.txt";
    private static final int ATTACH_NUMBER = 3;
    private MailService service;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        service = new MailServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        Browser.getBrowser().close();
    }

    @Test(description = "Supports testing preconditions")
    public void loginToMail() {
        LoginService loginService = new LoginServiceImpl();
        loginService.login(UserFactory.createCorrectUser());
        assertTrue("Correct precondition for supporting tests.", loginService.isLoginSuccess());
    }

    @Test(dependsOnMethods = "loginToMail", description = "Correct mail sending process.")
    public void sendMailAllFieldsCorrect() {
        String body = randomAlphanumeric(Constants.CONTENT_INDEX);
        Message message = new MessageBuilder()
                .email(Constants.EMAIL_LOGIN)
                .subject(body)
                .body(body)
                .build();
        service.sendMessage(message);
        assertTrue("Message should be sent. All fields in order.", service.isMessageSent(message));
    }

    @Test(dependsOnMethods = "loginToMail", description = "Opportunity to send mail with incomplete data.")
    public void sendMailWithAddressNoSubjectNoBody() {
        Message message = MessageFactory.createEmailOnlyMessage();
        service.sendMessage(message);
        assertTrue("Message should be sent. Not all fields are necessary.", service.isMessageSent(message));
    }

    @Test(dependsOnMethods = "loginToMail", description = "Inability of sending message without email address.",
            expectedExceptions = MessageSentException.class, expectedExceptionsMessageRegExp = ADDRESS_ERROR_MESSAGE)
    public void sendMailNotFilledAddress() {
        Message message = MessageFactory.createEmptyMessage();
        service.sendIncorrectMessage(message);
    }

    @Test(dependsOnMethods = "loginToMail", description = "Opportunity to send mail with attached file.")
    public void sendCorrectMailWithAttachedFile() {
        String content = randomAlphanumeric(Constants.CONTENT_INDEX);
        List<Path> attaches = fillPathList(ATTACH_NUMBER);
        MessageWithAttach message = new MessageWithAttachBuilder()
                .email(Constants.EMAIL_LOGIN)
                .subject(content)
                .body(content)
                .attach(attaches)
                .build();
        service.sendMessage(message);
        assertTrue(service.isAllFilesAttached(attaches));
    }

    private List<Path> fillPathList(int number) {
        List<Path> attaches = new ArrayList<>(number);
        for (int i = 1; i <= number; i++) {
            String path = String.format(ATTACHED_FILE_PATH_PATTERN, i);
            attaches.add(Paths.get(path));
        }
        return attaches;
    }
}
