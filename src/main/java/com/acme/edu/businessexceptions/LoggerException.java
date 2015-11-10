package com.acme.edu.businessexceptions;

import java.util.List;

/**
 * Signals that exceptional situations of some sort has occurred while using Logger
 */
public class LoggerException extends Exception {

    private List<Exception> causes;

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public LoggerException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and the specified list of causes -
     * exceptions that caused this exception to get thrown
     * @param message the detail message
     * @param causes the list of causes (which is saved for later retrieval by the getCauses() method
     */
    public LoggerException(String message, List<Exception> causes) {
        super(message);
        this.causes = causes;
    }

    /**
     * @return the array of causes of this exception
     */
    public Exception[] getCauses() {
        if (causes == null) {
            return new Exception[0];
        }

        Exception[] exceptions = new Exception[causes.size()];
        causes.toArray(exceptions);

        return exceptions;
    }
}
