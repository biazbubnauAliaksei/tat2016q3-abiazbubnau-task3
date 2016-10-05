package com.epam.homework.product.utility.builders;

import com.epam.homework.product.beans.Message;

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
        return message;
    }
}
