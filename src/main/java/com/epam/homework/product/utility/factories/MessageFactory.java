package com.epam.homework.product.utility.factories;

import com.epam.homework.product.beans.Message;
import com.epam.homework.product.utility.constants.Constants;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class MessageFactory {

    public static Message createFullMessage() {
        Message message = new Message();
        message.setEmail(Constants.EMAIL_LOGIN);
        message.setSubject(randomAlphabetic(Constants.CONTENT_INDEX));
        message.setBody(randomAlphanumeric(Constants.CONTENT_INDEX));
        return message;
    }

    public static Message createEmptyMessage() {
        Message message = new Message();
        message.setEmail(Constants.EMPTY);
        message.setSubject(Constants.EMPTY);
        message.setBody(Constants.EMPTY);
        return message;
    }

    public static Message createEmailOnlyMessage() {
        Message message = new Message();
        message.setEmail(Constants.EMAIL_LOGIN);
        message.setSubject(Constants.EMPTY);
        message.setBody(Constants.EMPTY);
        return message;
    }
}
