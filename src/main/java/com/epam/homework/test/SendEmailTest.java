package com.epam.homework.test;

import com.epam.homework.product.bean.Message;
import com.epam.homework.product.bean.MessageWithAttach;
import com.epam.homework.product.utility.builder.MessageBuilder;
import com.epam.homework.product.utility.builder.MessageWithAttachBuilder;
import com.epam.homework.product.utility.constant.Constants;
import com.epam.homework.service.exception.MessageSentException;
import com.epam.homework.product.utility.factory.MessageFactory;
import com.epam.homework.service.iface.MailService;
import com.epam.homework.service.impl.MailServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;
import static org.apache.commons.lang3.RandomStringUtils.*;

public class SendEmailTest extends BaseLoginTest {

    private static final String ADDRESS_ERROR_MESSAGE = "Не указан адрес получателя";

    private MailService service;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        service = new MailServiceImpl();
    }

    @Test(description = "Correct mail sending process.")
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

    @Test(description = "Opportunity to send mail with incomplete data.")
    public void sendMailWithAddressNoSubjectNoBody() {
        Message message = MessageFactory.createEmailOnlyMessage();
        service.sendMessage(message);
        assertTrue("Message should be sent. Not all fields are necessary.", service.isMessageSent(message));
    }

    @Test(description = "Inability of sending message without email address.",
            expectedExceptions = MessageSentException.class, expectedExceptionsMessageRegExp = ADDRESS_ERROR_MESSAGE)
    public void sendMailNotFilledAddress() {
        Message message = MessageFactory.createEmptyMessage();
        service.sendIncorrectMessage(message);
    }

    @Test(description = "Opportunity to send mail with attached file.")
    public void sendCorrectMailWithAttachedFile() {
        MessageWithAttach message = new MessageWithAttachBuilder().build();
        service.sendMessage(message);
        assertTrue("All filenames of attached files should be presents remotely",
                service.isAllFilesAttached(message.getAttaches()));
    }
}
