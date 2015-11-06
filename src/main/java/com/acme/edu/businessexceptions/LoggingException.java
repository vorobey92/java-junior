package com.acme.edu.businessexceptions;

import java.util.ArrayList;

public class LoggingException extends LoggerException {
    public LoggingException(String message) {
        super(message);
    }

    public LoggingException(String message, ArrayList<Exception> printerExceptions) {
        super(message, printerExceptions);
    }
}
