package com.acme.edu.state;

import com.acme.edu.printer.Printer;

/**
 *
 */
public class BooleanState extends State {
    /**
     *
     * @param printer ..
     */
    public BooleanState(Printer printer) {
        super(printer);
    }

    /**
     *
     * @param message JavaDoc not ready
     */
    public void log(String message){
        println("primitive: " + message);
    }

    @Override
    /**
     *
     */
    public void flush() {
    }

}
