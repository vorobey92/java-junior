package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.printers.LogWriter;
import com.acme.edu.printers.LogWriterException;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    private LogWriter[] logWriters;
    private String message;

    public Command(LogWriter... logWriters) {
        this.logWriters = logWriters;
    }

    public abstract Command merge(Command oldCommand) throws LoggingException;

    public void execute() throws LoggingException {
        if (getMessage() == null) {
            return;
        }

        List<Exception> printerExceptions = new ArrayList<>();
        for (LogWriter logWriter : logWriters) {
            try {
                logWriter.writeLine(getFormattedString());
            } catch (LogWriterException e) {
                printerExceptions.add(e);
            }
        }

        setMessage(null);

        if (!printerExceptions.isEmpty()) {
            throw new LoggingException("Errors occur while logging", printerExceptions);
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    protected abstract String getFormattedString();
}
