package com.epam.homework.product.utility.builders;

import com.epam.homework.product.beans.MessageWithAttach;

import java.nio.file.Path;
import java.util.List;

public class MessageWithAttachBuilder {
    private MessageWithAttach message = new MessageWithAttach();

    public MessageWithAttachBuilder email(String email) {
        message.setEmail(email);
        return this;
    }

    public MessageWithAttachBuilder subject(String subject) {
        message.setSubject(subject);
        return this;
    }

    public MessageWithAttachBuilder body(String body) {
        message.setBody(body);
        return this;
    }

    public MessageWithAttachBuilder attach(List<Path> attaches) {
        message.setAttaches(attaches);
        return this;
    }

    public MessageWithAttach build() {
        return message;
    }
}
