package com.acme.edu.commands;

import com.acme.edu.printers.Printer;
import com.acme.edu.printers.PrinterException;

public abstract class Command<T> {
    private Printer[] printers;
    private String message;

    public Command(Printer... printers) {
        this.printers = printers;
    }

    abstract public Command merge (T oldCommand) throws PrinterException;

    public void execute() throws PrinterException{
        if (getMessage() != null) {
            for (Printer printer : printers) {
                printer.println(getFormattedString());
            }
            setMessage(null);
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    abstract protected String getFormattedString();
}
