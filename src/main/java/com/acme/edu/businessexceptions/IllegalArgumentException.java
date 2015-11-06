package com.acme.edu.businessexceptions;

public class IllegalArgumentException extends LoggerException {
    public IllegalArgumentException(String message) {
        super(message);
    }
}
