package com.acme.edu.state;

import com.acme.edu.printer.Printer;

/**
 *
 */
public abstract class State {

    protected Printer printer;

    public State (Printer printer){
        this.printer = printer;
    }

    /**
     *
     * @param message JavaDoc not ready
     */
    public void log(String message){

    }

    /**
     *
     */
    public void flush(){

    }

    protected void print(String message){
        printer.print(message);
    }

    protected void println(String message){
        printer.println(message);
    }




}
