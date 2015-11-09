package com.acme.edu;

import com.acme.edu.exception.LogException;
import com.acme.edu.exception.NullMessageException;
import com.acme.edu.state.*;

/**
 *  Class for logging messages.
 *  To use logging you need to use method Logger.close() to finish.
 */
public class Logger {

    private static final String SEP = System.lineSeparator();
    private static final String PREFIX_FOR_CHAR_MESSAGES = "char: ";
    private static final String PREFIX_FOR_PRIMITIVE_MESSAGES = "primitive: ";
    private static final String PREFIX_FOR_REFERENCE_MESSAGES = "reference: ";
    private State state;
    private State lastState;
    private StateFactory factory;

    /**
     *
     * @param factory factory of states
     *
     */
    public Logger (StateFactory factory) {
        this.factory = factory;
        state = factory.getDefaultState();
        lastState = factory.getDefaultState();
    }


    /**
     *  Method for logging ints.
     *  For sequence of ints This method will log sum of ints when
     *  Stirng log is came or logging is completed.
     *  Also it logs two numbers (sum and new int) if type overflow while summing.
     * @param message  number (int) that will be logged (or sum for sequence)
     */
    public void log(int message) throws LogException {
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
    public void log(byte message) throws LogException {
        state = factory.getIntState(lastState);
        state.log(message + "");
        lastState = state;
    }

    /**
     *  Method for logging bytes.
     * @param message  char that will be logged
     */
    public void log(char message) throws LogException {
        state = factory.getDefaultState();
        state.log(PREFIX_FOR_CHAR_MESSAGES + message + SEP);
        lastState = state;
    }

    /**
     *  Method for logging boolean.
     * @param message  boolean that will be logged
     */
    public void log(boolean message) throws LogException {
        state = factory.getDefaultState();
        state.log(PREFIX_FOR_PRIMITIVE_MESSAGES + message + SEP);
        lastState = state;
    }

    /**
     * Method for logging Strings.
     * Before logging String, this method will log sum of sequent ints (if sum exists).
     * If message matches last logged String, method will count it before new String is came or Logger.close()
     * method is called or any number is logged.
     * @param message string that will be logged
     */
    public void log(String message) throws LogException {
        if (message == null){
            throw new NullMessageException("String is null");
        }
        state = factory.getStringState(lastState);
        state.log(message);
        lastState = state;
    }

    /**
     *  Method for logging Object
     * @param message object that will be logged
     */
    public void log(Object message) throws LogException {
        if (message == null){
            throw new NullMessageException("Object is null");
        }
        state = factory.getDefaultState();
        state.log(PREFIX_FOR_REFERENCE_MESSAGES + message + SEP);
        lastState = state;
    }

    /**
     * Method for finishing logging. Prints the rest statement.
     */
    public void close() throws  LogException {
        if (lastState == null){
            throw  new LogException("Trying to close null State");
        }
        lastState.flush();
    }

    /**
     *  Method for logging arrays of ints.
     * @param message array of ints that will be loged
     */
    public void log(int... message) throws LogException {
        if (    message == null ||
                message.length == 0){
            throw  new NullMessageException("Array is null  or empty");
        }
        state = factory.getDefaultState();
        state.log(Mapper.fromArrayToString(message));
        lastState = state;
    }

    /**
     *
     * @param message matrix of ints that will be loged
     */
    public void log(int[][] message) throws LogException {
        if ( message == null ||
             message.length == 0 ){
            throw new NullMessageException("Matrix is null or empty");
        }
        state = factory.getDefaultState();
        state.log(Mapper.fromMatrixToString(message));
        lastState = state;
    }

    /**
     *
     * @param message multimatrix that will be loged
     */
    public void log(int[][][][] message) throws LogException {
        if (    message == null ||
                message.length == 0 ){
            throw  new NullMessageException("Multimatrix is null or empty");
        }
        state = factory.getDefaultState();
        state.log(Mapper.fromMultiMatrixToString(message));
        lastState = state;
    }

    /**
     *
     * @param message array of Strings that will be loged
     */
    public void log(String... message) throws LogException {
        if (    message == null ||
                message.length == 0 ){
                throw new NullMessageException("Array of strings is null or empty");
        }
        state = factory.getDefaultState();
        for(String str : message) {
            state.log(str + SEP);
        }
        lastState = state;
    }

}
