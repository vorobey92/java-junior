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
