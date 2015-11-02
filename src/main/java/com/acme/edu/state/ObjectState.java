package com.acme.edu.state;

import com.acme.edu.printer.Printer;

/**
 *
 */
public class ObjectState extends State {

    /**
     *
     * @param printer
     */
    public ObjectState(Printer printer) {
        super(printer);
    }

    /**
     *
     * @param message JavaDoc not ready
     */
    public void log(String message){
        println("reference: " + message);
    }

    @Override
    /**
    *
    */
    public void flush() {
    }
}
