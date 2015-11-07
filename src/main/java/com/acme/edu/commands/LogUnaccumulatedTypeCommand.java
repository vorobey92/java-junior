package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.LogWriter;

public class LogUnaccumulatedTypeCommand extends Command<LogUnaccumulatedTypeCommand> {
    private Decorator decorator;

    public LogUnaccumulatedTypeCommand(Decorator decorator, LogWriter... logWriters) {
        super(logWriters);
        this.decorator = decorator;
    }

    @Override
    public LogUnaccumulatedTypeCommand merge(LogUnaccumulatedTypeCommand oldCommand) throws LoggingException {
        execute();

        return this;
    }

    @Override
    protected String getFormattedString() {
        return decorator.decorate(getMessage());
    }
}
