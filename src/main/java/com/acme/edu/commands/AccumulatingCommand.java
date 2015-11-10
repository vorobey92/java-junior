package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.writers.LogWriter;

/**
 * AccumulatingCommand is subclass of Command and provides a skeletal implementation for command which accumulate
 * data.
 */
public abstract class AccumulatingCommand extends Command {

    /**
     * Constructs a new AccumulatingCommand object with provided array of LogWriters
     * @param logWriters an array of LogWriters which will be used for writing a message exception
     */
    public AccumulatingCommand(LogWriter... logWriters) {
        super(logWriters);
    }

    /**
     * Merges this command with another command. If another command is null or does not have a message then just
     * this command is returned. If another command is of different class, then another command is executed and
     * this command is returned. Exact behaviour of merging with another command of the same class is definded
     * by subclasses.
     * @param oldCommand a Command to be merged with this command
     * @return the resulting Command object or this Command if accumulating is not applicable between commands
     * @throws LoggingException if errors occur when logging the message
     */
    @Override
    public Command merge(Command oldCommand) throws LoggingException {
        if (oldCommand == null || oldCommand.getMessage() == null) {
            return this;
        }

        if (!getClass().isAssignableFrom(oldCommand.getClass())) {
            oldCommand.execute();
            return this;
        }

        return mergeWithCommandOfSameClass(oldCommand);
    }

    protected abstract Command mergeWithCommandOfSameClass(Command oldCommand) throws LoggingException;
}
