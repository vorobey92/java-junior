package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.printers.LogWriter;
import com.acme.edu.printers.LogWriterException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a skeletal implementation for the Command subclasses. It encapsulates information needed
 * to perform an logging action: a message to be logged and LogWriters for writing the message to the target
 * destinations.
 */
public abstract class Command {
    private LogWriter[] logWriters;
    private String message;

    /**
     * Constructs a new Command object with provided array of LogWriters
     * @param logWriters an array of LogWriters which will be used for writing a message
     */
    public Command(LogWriter... logWriters) {
        this.logWriters = logWriters;
    }

    /**
     * Merges two commands into new Command object by accumulating message data in the result Command object. During
     * the operation, some data may be logging. If accumulating is not applicable, the argument must be executed.
     * @param oldCommand a Command to be merged with this command
     * @return the resulting Command object or this Command if accumulating is not applicable between commands
     * @throws LoggingException if errors occur when logging the message
     */
    public abstract Command merge(Command oldCommand) throws LoggingException;

    /**
     * Executes the command by logging the message with provided LogWriters. The message is set to null after executing.
     * @throws LoggingException if errors occur when logging the message
     */
    public void execute() throws LoggingException {
        if (getMessage() == null) {
            return;
        }

        List<Exception> printerExceptions = new ArrayList<>();
        for (LogWriter logWriter : logWriters) {
            try {
                logWriter.writeLine(getFormattedString());
            } catch (LogWriterException e) {
                printerExceptions.add(e);
            }
        }

        setMessage(null);

        if (!printerExceptions.isEmpty()) {
            throw new LoggingException("Errors occur while logging", printerExceptions);
        }
    }

    /**
     * Sets the argument as a message of the command
     * @param message a String to be set as the command's message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the message
     * @return the command's message
     */
    public String getMessage() {
        return message;
    }

    protected abstract String getFormattedString();
}
