package com.acme.edu;

public class Logger {

    private final static String PRIMITIVE_STRING = "primitive: ";

    private static void println(String message) {
        System.out.println(message);
    }

    /**
     * Logs an integer and then terminate the line.
     *
     * @param i <code>int</code> to be logged.
     */
    public static void log(int i) {
        println(PRIMITIVE_STRING + i);
    }

    /**
     * Logs a boolean and then terminate the line.
     *
     * @param bool <code>boolean</code> to be logged.
     */
    public static void log(boolean bool) {
        println(PRIMITIVE_STRING + bool);
    }

    /**
     * Logs a char and then terminate the line.
     *
     * @param c <code>char</code> to be logged.
     */
    public static void log(char c) {
        println("char: " + c);
    }

    /**
     * Logs a String and then terminate the line.
     *
     * @param string <code>String</code> to be logged.
     */
    public static void log(String string) {
        println("string: " + string);
    }

    /**
     * Logs an Object and then terminate the line.
     *
     * @param object <code>Object</code> to be logged.
     */
    public static void log(Object object) {
        println("reference: " + object);
    }
}