package com.acme.edu.state;

import com.acme.edu.exception.CanNotPrintException;
import com.acme.edu.exception.StateException;
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
    public abstract void log(String message) throws StateException;

    /**
     * Method for releasing buffer
     */
    public abstract void flush() throws StateException;

    protected void print(String message) throws StateException {
        try {
            printer.print(message);
        } catch (CanNotPrintException e) {
            throw new StateException(e);
        }
    }

    protected void println(String message) throws StateException {
        try {
            printer.println(message);
        } catch (CanNotPrintException e) {
            throw new StateException(e);
        }
    }


}
