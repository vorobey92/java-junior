package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.LogWriter;

public class LogIntegerCommand extends AccumulatingCommand {
    private int maxValue;
    private int minValue;
    private Decorator decorator;

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
