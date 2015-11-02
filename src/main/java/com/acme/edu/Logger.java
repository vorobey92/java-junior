package com.acme.edu;

import sun.rmi.runtime.Log;

import java.io.OutputStream;
import java.io.PrintStream;

public class Logger {

    private State currentState;
    private State unaccumulatingState;
    private State intState;
    private State byteState;
    private State stringState;

    public Logger(PrintStream outputStream) {
        unaccumulatingState = new Unaccumulating(outputStream);
        intState = new IntegerState(Integer.MAX_VALUE, Integer.MIN_VALUE, outputStream);
        byteState = new IntegerState(Byte.MAX_VALUE, Byte.MIN_VALUE, outputStream);
        stringState = new StringState(outputStream);
        currentState = unaccumulatingState;
    }

    public void log(int message) {
        if (currentState == intState) {
            intState.add(String.valueOf(message));
            return;
        }

        currentState.print();
        intState.add(String.valueOf(message));
        currentState = intState;
    }

    /**
     * Accumulates a byte. This is done by adding the passed byte to the sum. The sum is internally maintained
     * through a continuous sequence of invocations of this method.
     * If adding the byte comes to an byte overflow, the sum and the byte are logged respectively and the sum
     * is set to zero. The accumulating is considered dropped.
     * If the sequence of invocations of this method is discontinued by any other method of Logger class, the sum
     * is logged and set to zero. The accumulating is considered dropped.
     * This method also logs the currently accumulated data if it invokes after any other accumulated method of
     * the Logger class.
     *
     * @param message The byte to be accumulated.
     */
    public void log(byte message) {
        if (currentState == byteState) {
            byteState.add(String.valueOf(message));
            return;
        }

        currentState.print();
        byteState.add(String.valueOf(message));
        currentState = byteState;
    }

    /**
     * Logs a char.
     * This method also logs the currently accumulated data if it invokes after any accumulated method of
     * the Logger class.
     *
     * @param message The char to be logged.
     */
    public void log(char message) {
        currentState.print();
        unaccumulatingState.add(String.valueOf("char: " + message));
        currentState = unaccumulatingState;
    }

    /**
     * Accumulates a String. The Logger internally maintains the last provided string and the number of
     * sequential invocations of methods referred to String-family with the same argument. If the passed string
     * equals to provided string, the number is incremented. Otherwise the last provided string is logged along
     * with the number of sequential invocations and the passed string becomes the last provided string.
     * If the sequence of invocations of this method is discontinued by any other method of Logger class
     * not referred to String-family, the last provided string is logged along with the number of sequential
     * invocations. The accumulating is considered dropped.
     * This method also logs the currently accumulated data if it invokes after any other accumulated method of
     * the Logger class.
     *
     * @param message The String to be accumulated.
     */
    public void log(String message) {
        if (currentState == stringState) {
            stringState.add(String.valueOf(message));
            return;
        }

        currentState.print();
        stringState.add(message);
        currentState = stringState;
    }

    /**
     * Logs a boolean.
     * This method also logs the currently accumulated data if it invokes after any accumulated method of
     * the Logger class.
     *
     * @param message The boolean to be logged.
     */
    public void log(boolean message) {
        currentState.print();
        unaccumulatingState.add(String.valueOf("primitive: " + message));
        currentState = unaccumulatingState;
    }

    /**
     * Logs an Object.
     * This method also logs the currently accumulated data if it invokes after any accumulated method of
     * the Logger class.
     *
     * @param message The Object to be logged.
     */
    public void log(Object message) {
        currentState.print();
        unaccumulatingState.add(String.valueOf("reference: " + message));
        currentState = unaccumulatingState;
    }

    /**
     * Accumulates an array of ints. This method behaves as a sequence of invocations of method log(int) for
     * each int in the passed array.
     *
     * @param message an array of ints to be accumulated.
     */
    public void log(int... message) {
        for (int intMessage : message) {
            log(intMessage);
        }
    }

    /**
     * Accumulates a two-dimensional array of ints. This method behaves as a sequence of invocations of
     * method log(int[]) for each underlying array of ints in the passed two-dimensional array of ints.
     *
     * @param message a two-dimensional array of ints to be accumulated.
     */
    public void log(int[][] message) {
        log(ArrayUtils.multiDimIntArrayToOneDimIntArray(message));
    }

    /**
     * Accumulates a four-dimensional array of ints. This method behaves as a sequence of invocations of
     * method log(int[]) for each underlying array of ints in the passed four-dimensional array of ints.
     *
     * @param message a four-dimensional array of ints to be accumulated.
     */
    public void log(int[][][][] message) {
        log(ArrayUtils.multiDimIntArrayToOneDimIntArray(message));
    }

    /**
     * Accumulates an array of Strings. This method behaves as a sequence of invocations of method log(String)
     * for each String in the passed array of Strings.
     *
     * @param messages an array of Strings to be accumulated.
     */
    public void log(String... messages) {
        for (String string : messages) {
            log(string);
        }
    }

    public void close() {
        currentState.print();
    }
}
