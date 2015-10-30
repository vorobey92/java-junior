package com.acme.edu;

import java.util.Arrays;

public class Logger {

    private static final String PRIMITIVE_STRING = "primitive: ";
    private static int intSum = 0;
    private static boolean hasLastInteger = false;
    private static byte byteSum = 0;
    private static boolean hasLastByte = false;
    private static String lastString = null;
    private static int numStrings = 0;

    public static void close() {
        if (hasLastInteger) {
            println(PRIMITIVE_STRING + intSum);
        }
        if (hasLastByte) {
            println(PRIMITIVE_STRING + byteSum);
        }
        if (lastString != null) {
            println("string: " + lastString + (numStrings == 1 ? "" : " (x" + numStrings + ")"));
            lastString = null;
            numStrings = 0;
        }

        hasLastInteger = hasLastByte = false;
        numStrings = intSum = 0;
        byteSum = 0;
        lastString = null;
    }

    /**
     * Logs an integer message and then terminate the line.
     *
     * @param message <code>int</code> to be logged.
     */
    public static void log(byte message) {
        if (lastString != null) {
            println("string: " + lastString + (numStrings == 1 ? "" : " (x" + numStrings + ")"));
            lastString = null;
            numStrings = 0;
        }
        if ((byteSum > 0 && Byte.MAX_VALUE - byteSum < message)
                || (byteSum < 0 && Byte.MIN_VALUE - byteSum > message)) {
            if (hasLastByte) {
                println(PRIMITIVE_STRING + byteSum);
                hasLastByte = false;
                byteSum = 0;
            }
            println(PRIMITIVE_STRING + message);
        }
        else {
            hasLastByte = true;
            byteSum += message;
        }
    }

    /**
     * Logs an integer message and then terminate the line.
     *
     * @param message <code>int</code> to be logged.
     */
    public static void log(int message) {
        if (lastString != null) {
            println("string: " + lastString + (numStrings == 1 ? "" : " (x" + numStrings + ")"));
            lastString = null;
            numStrings = 0;
        }
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
        if (hasLastByte) {
            println(PRIMITIVE_STRING + byteSum);
            byteSum = 0;
            hasLastByte = false;
        }

        if (lastString == null) {
            lastString = message;
            numStrings = 1;
        } else if (lastString.equals(message)) {
            numStrings++;
        } else {
            println("string: " + lastString + (numStrings == 1 ? "" : " (x" + numStrings + ")"));
            lastString = message;
            numStrings = 1;
        }
    }

    /**
     * Logs an Object message and then terminate the line.
     *
     * @param message <code>Object</code> to be logged.
     */
    public static void log(Object message) {
        println("reference: " + message);
    }

    public static void log(int[] message) {
        println("primitives array: "
                + Arrays.toString(message).replaceFirst("\\[", "\\{").replaceFirst("\\]", "\\}"));
    }

    private static void println(String message) {
        System.out.println(message);
    }
}