package com.acme.edu.writers;

/**
 * Exception thrown when an error occurs while logging
 */
public class LogWriterException extends Exception {

    /**
     * Constructs a new LogWriterException.
     */
    public LogWriterException() {
        super();
    }

    /**
     * Constructs a new LogWriterException with the specified detail message.
     * @param message the detail message
     */
    public LogWriterException(String message) {
        super(message);
    }

    /**
     * Constructs a new LogWriterException with the specified detail message and the specified cause -
     * an exception that caused this exception to get thrown
     * @param message the detail message
     * @param e the causes (which is saved for later retrieval)
     */
    public LogWriterException(String message, Exception e) {
        super(message);
        initCause(e);
    }
}
