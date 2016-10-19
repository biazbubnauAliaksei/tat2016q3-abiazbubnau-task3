package com.epam.homework.framework.service.exception;

public class MessageSentException extends RuntimeException {
    public MessageSentException(String message) {
        super(message);
    }

    public MessageSentException(String message, Throwable cause) {
        super(message, cause);
    }
}