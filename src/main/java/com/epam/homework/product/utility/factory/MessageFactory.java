package com.epam.homework.product.utility.factory;

import com.epam.homework.product.bean.Message;
import com.epam.homework.product.utility.constant.Constants;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class MessageFactory {
    private static final int CONTENT_INDEX = 10;

    public static Message createFullMessage() {
        Message message = new Message();
        message.setEmail(Constants.EMAIL_LOGIN);
        message.setSubject(randomAlphabetic(CONTENT_INDEX));
        message.setBody(randomAlphanumeric(CONTENT_INDEX));
        return message;
    }

    public static Message createEmptyMessage() {
        Message message = new Message();
        message.setEmail(EMPTY);
        message.setSubject(EMPTY);
        message.setBody(EMPTY);
        return message;
    }

    public static Message createEmailOnlyMessage() {
        Message message = new Message();
        message.setEmail(Constants.EMAIL_LOGIN);
        message.setSubject(EMPTY);
        message.setBody(EMPTY);
        return message;
    }
}
