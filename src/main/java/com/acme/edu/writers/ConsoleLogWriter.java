package com.acme.edu.writers;

import java.io.PrintStream;

/**
 * ConsoleLogWriter is an implementation of LogWriter interface. It writes log messages to the "standard"
 * output stream.
 */
public class ConsoleLogWriter implements LogWriter {

    private static final PrintStream OUT = System.out;

    /**
     * Writes a log message to the "standard" output stream and then terminate the line.
     * @param logMessage a message to be written to a log.
     */
    @Override
    public void writeLine(String logMessage) {
        OUT.println(logMessage);
    }
}
