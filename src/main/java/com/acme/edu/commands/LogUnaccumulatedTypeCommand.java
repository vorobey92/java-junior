package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.LogWriter;

public class LogUnaccumulatedTypeCommand extends Command {
    private Decorator decorator;

    public LogUnaccumulatedTypeCommand(Decorator decorator, LogWriter... logWriters) {
        super(logWriters);
        this.decorator = decorator;
    }

    @Override
    protected String getFormattedString() {
        return decorator.decorate(getMessage());
    }

    @Override
    protected Command mergeWithCommandOfSameClass(Command oldCommand) throws LoggingException {
        oldCommand.execute();
        return this;
    }
}
