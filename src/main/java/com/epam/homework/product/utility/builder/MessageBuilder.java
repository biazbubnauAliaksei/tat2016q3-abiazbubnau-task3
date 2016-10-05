package com.epam.homework.product.utility.builder;

import com.epam.homework.product.bean.Message;
import com.epam.homework.product.utility.factory.MessageFactory;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class MessageBuilder {

    private Message message = new Message();

    public MessageBuilder email(String email) {
        message.setEmail(email);
        return this;
    }

    public MessageBuilder subject(String subject) {
        message.setSubject(subject);
        return this;
    }

    public MessageBuilder body(String body) {
        message.setBody(body);
        return this;
    }

    public Message build() {
        Message letter = MessageFactory.createFullMessage();

        if (isEmpty(message.getEmail())) {
            email(letter.getEmail());
        }
        if (isEmpty(message.getSubject())) {
            subject(letter.getSubject());
        }
        if (isEmpty(message.getBody())) {
            body(letter.getBody());
        }
        return message;
    }
}
