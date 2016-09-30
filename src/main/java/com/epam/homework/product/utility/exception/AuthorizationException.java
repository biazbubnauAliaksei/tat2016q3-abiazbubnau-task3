package com.epam.homework.product.utility.exception;

/**
 * Created by Al on 29.09.2016.
 */
public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
