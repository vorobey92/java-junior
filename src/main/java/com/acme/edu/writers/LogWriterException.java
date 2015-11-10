package com.acme.edu.writers;

public class LogWriterException extends Exception {

    public LogWriterException() {
        super();
    }

    public LogWriterException(String message) {
        super(message);
    }

    public LogWriterException(String message, Exception e) {
        super(message);
        initCause(e);
    }
}
