package com.acme.edu.printers;

public class LogWriterException extends Exception {

    public LogWriterException(String message) {
        super(message);
    }

    public LogWriterException(String message, Exception e) {
        super(message);
        initCause(e);
    }
}
