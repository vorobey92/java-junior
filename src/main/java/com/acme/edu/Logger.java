package com.acme.edu;

public class Logger {

    private static final String PRIMITIVE_STRING = "primitive: ";
    private static int sum = 0;
    private static boolean hasLastInteger = false;


    /**
     * Logs an integer message and then terminate the line.
     *
     * @param message <code>int</code> to be logged.
     */
    public static void log(int message) {
        if (message == 0) {
            if (hasLastInteger) {
                println(PRIMITIVE_STRING + sum);
                hasLastInteger = false;
            }
            println(PRIMITIVE_STRING + message);
        } else {
            hasLastInteger = true;
            sum += message;
        }
    }

    /**
     * Logs a boolean message and then terminate the line.
     *
     * @param message <code>boolean</code> to be logged.
     */
    public static void log(boolean message) {
        println("primitive: " + message);
    }

    /**
     * Logs a char message and then terminate the line.
     *
     * @param message <code>char</code> to be logged.
     */
    public static void log(char message) {
        println("char: " + message);
    }

    /**
     * Logs a String message and then terminate the line.
     *
     * @param message <code>String</code> to be logged.
     */
    public static void log(String message) {
        if (hasLastInteger) {
            println(PRIMITIVE_STRING + sum);
            sum = 0;
            hasLastInteger = false;
        }
        println("string: " + message);
    }

    /**
     * Logs an Object message and then terminate the line.
     *
     * @param message <code>Object</code> to be logged.
     */
    public static void log(Object message) {
        println("reference: " + message);
    }

    private static void println(String message) {
        System.out.println(message);
    }
}