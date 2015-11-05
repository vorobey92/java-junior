package com.acme.edu.exception;


public class CanNotPrintException extends Exception {

    public CanNotPrintException() {
    }

    public CanNotPrintException(String message) {
        super(message);
    }

    public CanNotPrintException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotPrintException(Throwable cause) {
        super(cause);
    }

    public CanNotPrintException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
