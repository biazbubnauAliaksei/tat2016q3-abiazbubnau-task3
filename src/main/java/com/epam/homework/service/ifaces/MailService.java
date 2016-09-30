package com.epam.homework.service.ifaces;

import com.epam.homework.product.beans.Message;

public interface MailService {

    void sendMessage(Message message);

    boolean isMessageSent(Message message);

    void putInDraft(Message message);

    void deleteMessage(Message message);

    boolean isAlertPresents();

    boolean isMessageInTrash(Message message);
}
