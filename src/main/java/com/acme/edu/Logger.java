package com.acme.edu;

import com.acme.edu.printer.Printable;
import com.acme.edu.state.*;

/**
 *  Class for logging messages.
 *  To use logging you need to use method Logger.close() to finish.
 */
public class Logger {

    private static final String SEP = System.lineSeparator();
    private State state;
    private State lastState;
    private StateFactory factory;
    private static Printable printer;

    /**
     *
     * @param printer realization of Printable interface
     */
    public Logger (Printable printer) {
        this.printer = printer;
        factory = new StateFactory(printer);
        state = factory.getEmptyState();
    }


    /**
     *  Method for logging ints.
     *  For sequence of ints This method will log sum of ints when
     *  Stirng log is came or logging is completed.
     *  Also it logs two numbers (sum and new int) if type overflow while summing.
     * @param message  number (int) that will be logged (or sum for sequence)
     */
    public void log(int message) {
//        matchIntStateOrReleaseBuff();
        state = factory.getIntState(lastState);
        state.log(message + "");
        lastState = state;
    }

    /**
     *  Method for logging bytes.
     *  For sequence of bytes This method will log sum of bytes when
     *  String log is came or logging is completed.
     *  Also it logs two numbers (sum and new byte) if type overflow while summing.
     * @param message  number (byte) that will be logged (or sum for sequence)
     */
    public void log(byte message) {
//        matchIntStateOrReleaseBuff();
        state = factory.getIntState(lastState);
        state.log(message + "");
        lastState = state;
    }

    /**
     *  Method for logging bytes.
     * @param message  char that will be logged
     */
    public void log(char message) {
        state = factory.getEmptyState();
        state.log("char: " + message + SEP);
        lastState = state;
    }

    /**
     *  Method for logging boolean.
     * @param message  boolean that will be logged
     */
    public void log(boolean message) {
        state = factory.getEmptyState();
        state.log("primitive: " + message + SEP);
        lastState = state;
    }

    /**
     * Method for logging Strings.
     * Before logging String, this method will log sum of sequent ints (if sum exists).
     * If message matches last logged String, method will count it before new String is came or Logger.close()
     * method is called or any number is logged.
     * @param message string that will be logged
     */
    public void log(String message) {
//        matchStringStateOrReleaseBuff();
        state = factory.getStringState(lastState);
        state.log(message);
        lastState = state;
    }

    /**
     *  Method for logging Object
     * @param message object that will be logged
     */
    public void log(Object message) {
        state = factory.getEmptyState();
        state.log("reference: " + message + SEP);
        lastState = state;
    }

    /**
     * Method for finishing logging. Prints the rest statement.
     */
    public void close(){
        lastState.flush();
    }

    /**
     *  Method for logging arrays of ints.
     * @param message array of ints that will be loged
     */
    public void log(int... message){
        state = factory.getEmptyState();
        state.log(Mapper.fromArrayToString(message));
        lastState = state;
    }

    /**
     *
     * @param message matrix of ints that will be loged
     */
    public void log(int[][] message){
        state = factory.getEmptyState();
        state.log(Mapper.fromMatrixToString(message));
        lastState = state;
    }

    /**
     *
     * @param message multimatrix that will be loged
     */
    public void log(int[][][][] message){
        state = factory.getEmptyState();
        state.log(Mapper.fromMultiMatrixToString(message));
        lastState = state;
    }

    /**
     *
     * @param message array of Strings that will be loged
     */
    public void log(String... message){
        state = factory.getEmptyState();
        for(String str : message) {
            state.log(str + SEP);
        }
        lastState = state;
    }


}
