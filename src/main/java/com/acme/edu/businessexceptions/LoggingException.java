package com.acme.edu.businessexceptions;

import java.util.List;

/**
 * Exception thrown when an error occurs while logging
 */
public class LoggingException extends LoggerException {

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public LoggingException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and the specified list of causes -
     * exceptions that caused this exception to get thrown
     * @param message the detail message
     * @param logWriterExceptions the list of causes (which is saved for later retrieval by the getCauses() method)
     */
    public LoggingException(String message, List<Exception> logWriterExceptions) {
        super(message, logWriterExceptions);
    }
}
