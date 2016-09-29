package com.epam.homework.service.ifaces;

import com.epam.homework.beans.Message;

public interface MailService {

    void sendMessage(Message message);

    boolean isMessageSent(Message message);

    boolean isMessageRecieved(Message message);
}
