package com.epam.homework.framework.service.iface;

import com.epam.homework.product.bean.Message;
import com.epam.homework.product.bean.MessageWithAttach;

import java.nio.file.Path;
import java.util.List;

public interface MailService {

    void sendMessage(Message message);

    void sendMessage(MessageWithAttach message);

    void sendIncorrectMessage(Message message);

    boolean isMessageSent(Message message);

    boolean isAllFilesAttached(List<Path> attaches, String subject);
}
