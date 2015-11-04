package com.acme.edu.commands;

import com.acme.edu.printers.Printer;

public class LogUnaccumulatedTypeCommand extends Command<LogUnaccumulatedTypeCommand> {
    private String format;

    public LogUnaccumulatedTypeCommand(Printer printer, String format) {
        super(printer);
        this.format = format;
    }

    @Override
    public LogUnaccumulatedTypeCommand merge(LogUnaccumulatedTypeCommand oldCommand) {
        execute();
        setMessage(null);

        return this;
    }

    @Override
    protected String getFormattedString() {
        return String.format(format, getMessage());
    }
}
