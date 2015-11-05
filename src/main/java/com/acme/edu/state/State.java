package com.acme.edu.state;

import com.acme.edu.exception.CanNotPrintException;
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
    public abstract void log(String message);

    /**
     * Method for releasing buffer
     */
    public abstract void flush();

    protected void print(String message){
        try {
            printer.print(message);
        } catch (CanNotPrintException e) {
            e.printStackTrace();
        }
    }

    protected void println(String message){
        try {
            printer.println(message);
        } catch (CanNotPrintException e) {
            e.printStackTrace();
        }
    }


}
