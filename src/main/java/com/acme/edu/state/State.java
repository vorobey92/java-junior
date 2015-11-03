package com.acme.edu.state;

import com.acme.edu.printer.Printable;

/**
 *
 */
public abstract class State {

    protected Printable printable;

    public State (Printable printable){
        this.printable = printable;
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
        printable.print(message);
    }

    protected void println(String message){
        printable.println(message);
    }


}
