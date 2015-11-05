package com.acme.edu.exception;


public class NullMessageException extends Exception {

    public NullMessageException() {
    }

    public NullMessageException(String message) {
        super(message);
    }

    public NullMessageException(Throwable cause) {
        super(cause);
    }

    public NullMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
