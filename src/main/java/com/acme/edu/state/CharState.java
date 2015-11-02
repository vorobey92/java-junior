package com.acme.edu.state;

import com.acme.edu.printer.Printer;

/**
 *
 */
public class CharState extends State {

    /**
     *
     * @param printer
     */
    public CharState(Printer printer) {
        super(printer);
    }

    public void log(String message){
        println("char: " + message);
    }

    @Override
    /**
     *
     */
    public void flush() {
    }
}
