package com.epam.homework.product.utility.builder;

import com.epam.homework.product.bean.Message;
import com.epam.homework.product.bean.MessageWithAttach;
import com.epam.homework.product.utility.factory.MessageFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class MessageWithAttachBuilder {
    private static final int INDEX_0 = 0;
    private static final String ATTACHED_FILE_PATH_PATTERN = "./src/main/resources/file-to-upload%d.txt";
    private static final int ATTACH_NUMBER = 3;

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
        Message letter = MessageFactory.createFullMessage();
        if (message.getEmail().equals(EMPTY)) {
            email(letter.getEmail());
        }
        if (message.getSubject().equals(EMPTY)) {
            subject(letter.getSubject());
        }
        if (message.getBody().equals(EMPTY)) {
            body(letter.getBody());
        }
        if (message.getAttaches().size() == INDEX_0) {
            attach(fillPathList(ATTACH_NUMBER));
        }
        return message;
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
