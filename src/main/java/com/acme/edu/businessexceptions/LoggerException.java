package com.acme.edu.businessexceptions;

import java.util.List;

public class LoggerException extends Exception {

    private List<Exception> causes;

    public LoggerException(String message) {
        super(message);
    }

    public LoggerException(String message, List<Exception> causes) {
        super(message);
        this.causes = causes;
    }

    public Exception[] getCauses() {
        if (causes == null) {
            return new Exception[0];
        }

        Exception[] exceptions = new Exception[causes.size()];
        causes.toArray(exceptions);

        return exceptions;
    }
}
