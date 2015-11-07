package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.printers.LogWriter;
import com.acme.edu.printers.LogWriterException;

import java.util.ArrayList;

public abstract class Command<T> {
    private LogWriter[] logWriters;
    private String message;

    public Command(LogWriter... logWriters) {
        this.logWriters = logWriters;
    }

    abstract public Command merge(T oldCommand) throws LoggingException;

    public void execute() throws LoggingException {
        if (getMessage() == null) {
            return;
        }

        ArrayList<Exception> printerExceptions = new ArrayList<>();
        for (LogWriter logWriter : logWriters) {
            try {
                logWriter.println(getFormattedString());
            } catch (LogWriterException e) {
                printerExceptions.add(e);
            }
        }

        setMessage(null);

        if (printerExceptions.size() > 0) {
            throw new LoggingException("Errors occur while logging", printerExceptions);
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
