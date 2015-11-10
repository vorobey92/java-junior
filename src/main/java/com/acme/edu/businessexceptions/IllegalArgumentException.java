package com.acme.edu.businessexceptions;

/**
 * This exception is thrown to indicate that a method of Logger class has been passed an illegal or
 * inappropriate argument
 */
public class IllegalArgumentException extends LoggerException {

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public IllegalArgumentException(String message) {
        super(message);
    }
}
