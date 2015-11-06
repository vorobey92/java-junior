package com.acme.edu.printers;

import java.io.IOException;

public class PrinterException extends Exception {
    public PrinterException(Exception e) {
        initCause(e);
    }
}
