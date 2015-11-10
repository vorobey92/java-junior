package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.writers.LogWriter;

/**
 * LogIntegerCommand is subclass of AccumulatingCommand and implements behaviour of accumulating of
 * integral-type messages with bounds checking. If this command merges with another command of LogIntegerCommand
 * class, this command is returned with a new message which is equal sum of the message of this command and the
 * message of another command. If the new message may come out of bounds, another command and this command are
 * executed respectively instead of addition of their messages and this command is returned.
 */
public class LogIntegerCommand extends AccumulatingCommand {
    private int maxValue;
    private int minValue;
    private Decorator decorator;

    /**
     * Constructs a new LogIntegerCommand object with provided decorator, upper bound of integer
     * interval, lower bound of integer interval and array of LogWriters
     * @param decorator a decorator which will be used to decorate the contained message
     * @param maxValue upper bound of integer interval
     * @param minValue lower bound of integer interval
     * @param logWriters an array of LogWriters which will be used for writing a message exception
     */
    public LogIntegerCommand(Decorator decorator, int maxValue, int minValue, LogWriter... logWriters) {
        super(logWriters);
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.decorator = decorator;
    }

    @Override
    protected String getFormattedString() {
        return decorator.decorate(getMessage());
    }

    @Override
    protected Command mergeWithCommandOfSameClass(Command oldCommand) throws LoggingException {

        LogIntegerCommand logIntegerCommand = (LogIntegerCommand) oldCommand;
        if (isSumOutOfRange(logIntegerCommand.getMessage())) {
            logIntegerCommand.execute();
            execute();
            setMessage(null);

            return this;
        }

        setMessage(
                Integer.toString(Integer.parseInt(getMessage()) + Integer.parseInt(logIntegerCommand.getMessage()))
        );

        return this;
    }

    private boolean isSumOutOfRange(String oldMessage) {

        int summand = Integer.parseInt(getMessage());
        int oldSummand = Integer.parseInt(oldMessage);

        return (oldSummand > 0 && maxValue - oldSummand < summand)
                || (oldSummand < 0 && minValue - oldSummand > summand);
    }
}
