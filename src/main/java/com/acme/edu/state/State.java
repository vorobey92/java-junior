package com.acme.edu.state;

import com.acme.edu.exception.CanNotPrintException;
import com.acme.edu.exception.StateException;
import com.acme.edu.printer.Printable;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for State
 */
public abstract class State {

    protected List<Printable> list = new ArrayList<Printable>();

    /**
     *
     * @param printers determines streams for logging
     */
    public State (Printable... printers){
        for(Printable pr : printers) {
            list.add(pr);
        }
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
            for(Printable printer : list){
                printer.print(message);
            }
        } catch (CanNotPrintException e) {
            throw new StateException(e);
        }
    }

    protected void println(String message) throws StateException {
        try {
            for(Printable printer : list) {
                printer.println(message);
            }
        } catch (CanNotPrintException e) {
            throw new StateException(e);
        }
    }


}
