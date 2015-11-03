package com.acme.edu.printer;

/**
 * Realisation of Printable interface.
 * ConsolePrinter logs messages into System.out.
 */
public class ConsolePrinter implements Printable {

    /**
     *
     * Prints a string.
     * @param  message   The <code>String</code> to be printed
     */
    @Override
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Prints a String and then terminate the line.
     * @param message The <code>String</code> to be printed
     */
    @Override
    public void println(String message){
        print(message + System.lineSeparator());
    }
}
