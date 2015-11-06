package com.acme.edu;

import com.acme.edu.commands.Command;
import com.acme.edu.commands.CommandFactory;
import com.acme.edu.businessexceptions.IllegalArgumentException;
import com.acme.edu.businessexceptions.LogException;
import com.acme.edu.printers.Printer;
import com.acme.edu.printers.PrinterException;
import com.acme.edu.states.State;
import com.acme.edu.states.StateFactory;

import java.util.Arrays;

/**
 * A Logger can be used to apply different types of messages to the standard output stream. A Logger
 * accumulates some types of messages before logging (see description in method-level documentation).
 * The flush() method is used to force the Logger to write an accumulated data to the standard output stream.
 */
public class Logger {

    private State currentState;
    private State unaccumulatingState;
    private State intState;
    private State byteState;
    private State stringState;
    private Printer printer;
    private Printer[] printers;
    private CommandFactory commandFactory;

    public Logger(CommandFactory commandFactory, StateFactory stateFactory, Printer... printers) {
        this.commandFactory = commandFactory;
        unaccumulatingState = stateFactory.createState();
        intState = stateFactory.createState();
        byteState = stateFactory.createState();
        stringState = stateFactory.createState();
        currentState = unaccumulatingState;
        this.printers = Arrays.copyOf(printers, printers.length);
        this.printer = printers[0];
    }

    /**
     * Accumulates an integer. This is done by adding the passed integer to the sum. The sum is internally
     * maintained through a continuous sequence of invocations of methods referred to int-family.
     * If adding the integer comes to an integer overflow, the sum and the integer are logged respectively and
     * the sum is set to zero. The accumulating is considered dropped.
     * If the sequence of invocations of this method is discontinued by any method of Logger class not referred
     * to int-family, the sum is logged and set to zero. The accumulating is considered dropped.
     * This method also logs the currently accumulated data if it invokes after any other accumulated method of
     * the Logger class.
     *
     * @param message The int to be accumulated.
     */
    public void log(int message) throws LogException {
        changeState(CommandFactory.Type.INT, intState, message);
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
    public void log(byte message) throws LogException {
        changeState(CommandFactory.Type.BYTE, byteState, message);
    }

    /**
     * Logs a char.
     * This method also logs the currently accumulated data if it invokes after any accumulated method of
     * the Logger class.
     *
     * @param message The char to be logged.
     */
    public void log(char message) throws LogException {
        changeState(CommandFactory.Type.CHAR, unaccumulatingState, message);
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
    public void log(String message) throws IllegalArgumentException, LogException {
        if (message == null) {
            throw new IllegalArgumentException();
        }
        changeState(CommandFactory.Type.STRING, stringState, message);
    }

    /**
     * Logs a boolean.
     * This method also logs the currently accumulated data if it invokes after any accumulated method of
     * the Logger class.
     *
     * @param message The boolean to be logged.
     */
    public void log(boolean message) throws LogException {
        changeState(CommandFactory.Type.BOOLEAN, unaccumulatingState, message);
    }

    /**
     * Logs an Object.
     * This method also logs the currently accumulated data if it invokes after any accumulated method of
     * the Logger class.
     *
     * @param message The Object to be logged.
     */
    public void log(Object message) throws IllegalArgumentException, LogException {
        if (message == null) {
            throw new IllegalArgumentException();
        }
        changeState(CommandFactory.Type.OBJECT, unaccumulatingState, message);
    }

    /**
     * Accumulates an array of ints. This method behaves as a sequence of invocations of method log(int) for
     * each int in the passed array.
     *
     * @param message an array of ints to be accumulated.
     */
    public void log(int... message) throws IllegalArgumentException, LogException {
        if (message == null) {
            throw new IllegalArgumentException();
        }

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
    public void log(int[][] message) throws IllegalArgumentException, LogException {
        log(ArrayUtils.multiDimIntArrayToOneDimIntArray(message));
    }

    /**
     * Accumulates a four-dimensional array of ints. This method behaves as a sequence of invocations of
     * method log(int[]) for each underlying array of ints in the passed four-dimensional array of ints.
     *
     * @param message a four-dimensional array of ints to be accumulated.
     */
    public void log(int[][][][] message) throws IllegalArgumentException, LogException {
        log(ArrayUtils.multiDimIntArrayToOneDimIntArray(message));
    }

    /**
     * Accumulates an array of Strings. This method behaves as a sequence of invocations of method log(String)
     * for each String in the passed array of Strings.
     *
     * @param messages an array of Strings to be accumulated.
     */
    public void log(String... messages) throws IllegalArgumentException, LogException {
        for (String string : messages) {
            log(string);
        }
    }

    /**
     * Flushes the Logger. This is done by logging the accumulated data. The Logger still can be used
     * after the flush() method is invoked.
     */
    public void flush() throws LogException {
        try {
            currentState.flush();
        } catch (PrinterException e) {
            LogException logException = new LogException();
            logException.initCause(e);
            throw logException;
        }
    }

    private void changeState(CommandFactory.Type type, State nextState, Object message) throws LogException {
        if (currentState != nextState) {
            flush();
        }

        Command command = commandFactory.createCommand(type, printer);
        command.setMessage(message.toString());
        currentState = nextState;
        try {
            currentState.apply(command);
        } catch (PrinterException e) {
            LogException logException = new LogException();
            logException.initCause(e);
            throw logException;
        }
    }
}
