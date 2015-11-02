package com.acme.edu.printer;

/**
 *
 */
public class ConsolePrinter implements Printer {

    /**
     *
     * @param message
     */
    public static void print(String message) {
        System.out.print(message);
    }

    /**
     *
     * @param message
     */
    public static void printer(String message){
        print(message + System.lineSeparator());
    }
}
