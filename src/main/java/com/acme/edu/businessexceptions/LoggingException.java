package com.acme.edu.businessexceptions;

import java.util.List;

public class LoggingException extends LoggerException {
    public LoggingException(String message) {
        super(message);
    }

    public LoggingException(String message, List<Exception> printerExceptions) {
        super(message, printerExceptions);
    }
}
