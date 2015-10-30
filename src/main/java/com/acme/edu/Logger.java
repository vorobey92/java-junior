package com.acme.edu;

public class Logger {

    private static final String PRIMITIVE_STRING = "primitive: ";
    private static int intSum = 0;
    private static boolean hasLastInteger = false;

    public static void close() {
        if (hasLastInteger) {
            println(PRIMITIVE_STRING + intSum);
        }
    }

    /**
     * Logs an integer message and then terminate the line.
     *
     * @param message <code>int</code> to be logged.
     */
    public static void log(int message) {
        if ((intSum > 0 && Integer.MAX_VALUE - intSum < message)
                || (intSum < 0 && Integer.MIN_VALUE - intSum > message)) {
            if (hasLastInteger) {
                println(PRIMITIVE_STRING + intSum);
                hasLastInteger = false;
                intSum = 0;
            }
            println(PRIMITIVE_STRING + message);
        }
        else {
            hasLastInteger = true;
            intSum += message;
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
            println(PRIMITIVE_STRING + intSum);
            intSum = 0;
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