package com.epam.homework.product.utility.exception;

/**
 * Created by Aliaksei Biazbubnau on 30.09.2016.
 */
public class MessageSentException extends RuntimeException {
    public MessageSentException(String message) {
        super(message);
    }

    public MessageSentException(String message, Throwable cause) {
        super(message, cause);
    }
}
