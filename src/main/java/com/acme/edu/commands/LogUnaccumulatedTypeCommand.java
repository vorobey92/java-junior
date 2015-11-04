package com.acme.edu.commands;

import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.Printer;

public class LogUnaccumulatedTypeCommand extends Command<LogUnaccumulatedTypeCommand> {
    private Decorator decorator;

    public LogUnaccumulatedTypeCommand(Printer printer, Decorator decorator) {
        super(printer);
        this.decorator = decorator;
    }

    @Override
    public LogUnaccumulatedTypeCommand merge(LogUnaccumulatedTypeCommand oldCommand) {
        execute();
        setMessage(null);

        return this;
    }

    @Override
    protected String getFormattedString() {
        return decorator.decorate(getMessage());
    }
}
