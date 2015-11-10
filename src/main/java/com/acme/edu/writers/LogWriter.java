package com.acme.edu.writers;

/**
 * LogWriter interface represents a writer that can write a log message. Implementations of this interface define
 * various strategies for outputting log statements.
 */
public interface LogWriter {
    /**
     * Writes a log message to the log and then terminate the line
     * @param logMessage a message to be written to a log
     * @throws LogWriterException if an error occurs when writing the message to a log
     */
    void writeLine(String logMessage) throws LogWriterException;
}
