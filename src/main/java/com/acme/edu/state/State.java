package com.acme.edu.state;

import com.acme.edu.printer.Printer;

/**
 *
 */
public abstract class State {

    private Printer printer;

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

    protected static void print(String message){
        System.out.print(message);
    }

    protected static void println(String message){
        print(message + System.lineSeparator());
    }




}
