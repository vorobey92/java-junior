package com.acme.edu.state;

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
        printer.print(message);
    }

    protected void println(String message){
        printer.println(message);
    }


}
