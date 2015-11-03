package com.acme.edu.state;

import com.acme.edu.printer.Printable;

/**
 *
 */
public class EmptyState extends State {

    /**
     *
     * @param printable
     */
    public EmptyState(Printable printable) {
        super(printable);
    }

    /**
     *
     * @param message JavaDoc not ready
     */
    @Override
    public void log(String message){
        print(message);
    }

    @Override
    public void flush() {

    }
}
