package com.acme.edu.state;

import com.acme.edu.printer.Printer;

/**
 *
 */
public class EmptyState extends State {

    /**
     *
     * @param printer
     */
    public EmptyState(Printer printer) {
        super(printer);
    }

    /**
     *
     * @param message JavaDoc not ready
     */
    @Override
    public void log(String message){
        print(message);
    }
}
