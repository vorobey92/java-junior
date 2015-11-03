package com.acme.edu.state;

import com.acme.edu.printer.Printable;

/**
 * Default state for not Int(Byte) or String state.
 */
public class EmptyState extends State {

    /**
     *
     * @param printer determines stream for logging
     */
    public EmptyState(Printable printer) {
        super(printer);
    }

    /**
     *
     * @param message String that will be logged
     */
    @Override
    public void log(String message){
        print(message);
    }

    /**
     * Release buffers, but for EmptyState nothing to log.
     */
    @Override
    public void flush() {
// leak of Abstraction
    }
}
