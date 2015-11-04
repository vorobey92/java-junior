package com.acme.edu.commands;

import com.acme.edu.printers.Printer;

public abstract class Command<T> {
    private Printer printer;
    private String message;

    public Command(Printer printer) {
        this.printer = printer;
    }

    abstract public Command merge(T oldCommand);

    public void execute() {
        if (message != null) {
            printer.println(getFormattedString());
            message = null;
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    abstract protected String getFormattedString();

    protected String getMessage() {
        return message;
    }
}
