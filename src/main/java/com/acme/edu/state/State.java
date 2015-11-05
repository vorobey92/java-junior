package com.acme.edu.state;

import com.acme.edu.exception.CanNotPrintException;
import com.acme.edu.exception.LogException;
import com.acme.edu.printer.Printable;

/**
 * Abstract class for State
 */
public abstract class State {

    protected Printable printer;

    /**
     *
     * @param printer determines stream for logging
     */
    public State (Printable printer){
        this.printer = printer;
    }

    /**
     *
     * @param message string thar will be loged
     */
    public abstract void log(String message) throws LogException;

    /**
     * Method for releasing buffer
     */
    public abstract void flush() throws LogException;

    protected void print(String message) throws LogException {
        try {
            printer.print(message);
        } catch (CanNotPrintException e) {
            throw new LogException(e);
        }
    }

    protected void println(String message) throws LogException {
        try {
            printer.println(message);
        } catch (CanNotPrintException e) {
            throw new LogException(e);
        }
    }


}
