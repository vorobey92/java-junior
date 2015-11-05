package com.acme.edu.exception;


public class PreviousStateIsNullException extends Exception {

    public PreviousStateIsNullException() {
    }

    public PreviousStateIsNullException(String message) {
        super(message);
    }

    public PreviousStateIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public PreviousStateIsNullException(Throwable cause) {
        super(cause);
    }

    public PreviousStateIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
