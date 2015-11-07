package com.acme.edu.printers;

public class PrinterException extends Exception {

    public PrinterException(String message) {
        super(message);
    }

    public PrinterException(String message, Exception e) {
        super(message);
        initCause(e);
    }
}
