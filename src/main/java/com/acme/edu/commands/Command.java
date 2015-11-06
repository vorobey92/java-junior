package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.printers.Printer;
import com.acme.edu.printers.PrinterException;

import java.util.ArrayList;

public abstract class Command<T> {
    private Printer[] printers;
    private String message;

    public Command(Printer... printers) {
        this.printers = printers;
    }

    abstract public Command merge (T oldCommand) throws LoggingException;

    public void execute() throws LoggingException {
        if (getMessage() != null) {
            ArrayList<Exception> printerExceptions = new ArrayList<>();
            for (Printer printer : printers) {
                try {
                    printer.println(getFormattedString());
                } catch (PrinterException e) {
                    printerExceptions.add(e);
                }
            }

            if (printerExceptions.size() > 0) {
                throw new LoggingException("Errors occur while logging", printerExceptions);
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
