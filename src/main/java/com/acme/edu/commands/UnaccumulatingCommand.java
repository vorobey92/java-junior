package com.acme.edu.commands;

import com.acme.edu.printers.Printer;

public class UnaccumulatingCommand extends Command<UnaccumulatingCommand> {
    private String format;

    public UnaccumulatingCommand(Printer printer, String format) {
        super(printer);
        this.format = format;
    }

    @Override
    public UnaccumulatingCommand merge(UnaccumulatingCommand oldCommand) {
        execute();
        setMessage(null);

        return this;
    }

    @Override
    protected String getFormattedString() {
        return String.format(format, getMessage());
    }
}
