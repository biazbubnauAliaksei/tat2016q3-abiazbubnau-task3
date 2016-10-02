package com.epam.homework.service.iface;

import com.epam.homework.product.beans.Message;

public interface MailService {

    void sendMessage(Message message);

    void sendIncorrectMessage(Message message);

    boolean isMessageSent(Message message);

    void putInDraft(Message message);

    void deleteMessage(Message message);

    boolean isMessageInTrash(Message message);
}
