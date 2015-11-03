package com.acme.edu.state;

import com.acme.edu.printer.Printable;

/**
 *
 */
public abstract class State {

    protected Printable printer;

    public State (Printable printer){
        this.printer = printer;
    }

    /**
     *
     * @param message JavaDoc not ready
     */
    public abstract void log(String message);

    /**
     *
     */
    public abstract void flush();

    protected void print(String message){
        printer.print(message);
    }

    protected void println(String message){
        printer.println(message);
    }


}
