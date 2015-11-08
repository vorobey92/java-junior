package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.printers.LogWriter;

public abstract class AccumulatingCommand extends Command {

    public AccumulatingCommand(LogWriter... logWriters) {
        super(logWriters);
    }

    @Override
    public Command merge(Command oldCommand) throws LoggingException {
        if (oldCommand == null) {
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
