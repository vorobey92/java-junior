package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.LogWriter;

/**
 * LogUnaccumulatedTypeCommand is subclass of Command and implements unaccumulating behaviour of
 * interaction between commands
 */
public class LogUnaccumulatedTypeCommand extends Command {
    private Decorator decorator;

    /**
     * Constructs a new LogUnaccumulatedTypeCommand object with provided array of LogWriters
     * @param logWriters an array of LogWriters which will be used for writing a message exception
     */
    public LogUnaccumulatedTypeCommand(Decorator decorator, LogWriter... logWriters) {
        super(logWriters);
        this.decorator = decorator;
    }

    /**
     * Merges this command with another command. This is done by executing the argument if it is not null and has
     * non-null message and executing this command in precisely the order given.
     * @param oldCommand a Command to be merged with this command
     * @return the resulting Command object or this Command if accumulating is not applicable between commands
     * @throws LoggingException if errors occur when logging the message
     */
    @Override
    public Command merge(Command oldCommand) throws LoggingException {
        if (oldCommand != null && oldCommand.getMessage() != null) {
            oldCommand.execute();
        }

        execute();
        return this;
    }

    @Override
    protected String getFormattedString() {
        return decorator.decorate(getMessage());
    }
}
