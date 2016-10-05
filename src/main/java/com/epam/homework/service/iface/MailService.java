package com.epam.homework.service.iface;

import com.epam.homework.product.beans.Message;

import java.util.List;

public interface MailService {

    void sendMessage(Message message);

    void sendMessage(Message message, List<String> attaches);

    void sendIncorrectMessage(Message message);

    boolean isMessageSent(Message message);

    void putInDraft();

    void deleteMessage(Message message);

    boolean isAllFilesAttached(List<String> attaches);

    boolean isMessageInTrash(Message message);
}
