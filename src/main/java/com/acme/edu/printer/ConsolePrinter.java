package com.acme.edu.printer;

/**
 *
 */
public class ConsolePrinter implements Printer {

    /**
     *
     * @param message
     */
    @Override
    public void print(String message) {
        System.out.print(message);
    }

    /**
     *
     * @param message
     */
    @Override
    public void println(String message){
        print(message + System.lineSeparator());
    }
}
