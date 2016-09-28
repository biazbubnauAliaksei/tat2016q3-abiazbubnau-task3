package com.epam.homework.service.ifaces;

public interface ComposeService {
    void composeMessage(String email, String subject, String message);
    void sendMessage();
}
