package com.acme.edu;

import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.state.*;

/**
 *  Class for logging messages.
 *  To use logging you need to use method Logger.close() to finish.
 */
public class Logger {

    private State state;
    private State lastState;

  private enum StateLoggerHolder{

        INT(new IntState(new ConsolePrinter())),
        STRING(new StringState(new ConsolePrinter())),
        BYTE(new ByteState(new ConsolePrinter())),
        EMPTY_STATE(new EmptyState(new ConsolePrinter()));

        private State state;

        StateLoggerHolder(State state){
            this.state = state;
        }

  }

    /**
     *  Method for logging ints.
     *  Tip: For sequence of ints This method will log sum of ints when
     *  Stirng log is came or logging is completed.
     *  Also it logs two numbers (sum and new int) if type overflow while summing.
     * @param message  number (int) that will be logged (or sum for sequence)
     */
    public void log(int message) {
        matchIntStateOrReleaseBuff();
        state = StateLoggerHolder.INT.state;
        state.log(message + "");
        lastState = StateLoggerHolder.INT.state;
    }

    /**
     *  Method for logging bytes.
     *  Tip: For sequence of bytes This method will log sum of bytes when
     *  String log is came or logging is completed.
     *  Also it logs two numbers (sum and new byte) if type overflow while summing.
     * @param message  number (byte) that will be logged (or sum for sequence)
     */
    public void log(byte message) {
        matchIntStateOrReleaseBuff();
        state = StateLoggerHolder.BYTE.state;
        state.log(message + "");
        lastState = StateLoggerHolder.BYTE.state;
    }

    /**
     *  Method for logging bytes.
     * @param message  char that will be logged
     */
    public void log(char message) {
        state = StateLoggerHolder.EMPTY_STATE.state;
        state.log("char: "+message);
        lastState = StateLoggerHolder.EMPTY_STATE.state;
    }

    /**
     *  Method for logging boolean.
     * @param message  boolean that will be logged
     */
    public void log(boolean message) {
        state = StateLoggerHolder.EMPTY_STATE.state;
        state.log("primitive: "+message);
        lastState = StateLoggerHolder.EMPTY_STATE.state;
    }

    /**
     * Method for logging Strings.
     * Before logging String, this method will log sum of sequent ints (if sum exists).
     * If message matches last logged String, method will count it before new String is came or Logger.close()
     * method is called or any number is logged.
     * @param message string that will be logged
     */
    public void log(String message) {
        matchStringStateOrReleaseBuff();
        state = StateLoggerHolder.STRING.state;
        state.log(message);
        lastState = StateLoggerHolder.STRING.state;
    }

    /**
     *  Method for logging Object
     * @param message object that will be logged
     */
    public void log(Object message) {
        state = StateLoggerHolder.EMPTY_STATE.state;
        state.log("reference: "+message);
        lastState = StateLoggerHolder.EMPTY_STATE.state;
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
        state = StateLoggerHolder.EMPTY_STATE.state;
        state.log(Mapper.fromArrayToString(message));
        lastState = StateLoggerHolder.EMPTY_STATE.state;
    }

    /**
     *
     * @param message matrix of ints that will be loged
     */
    public void log(int[][] message){
        state = StateLoggerHolder.EMPTY_STATE.state;
        state.log(Mapper.fromMatrixToString(message));
        lastState = StateLoggerHolder.EMPTY_STATE.state;
    }

    /**
     *
     * @param message multimatrix that will be loged
     */
    public void log(int[][][][] message){
        state = StateLoggerHolder.EMPTY_STATE.state;
        state.log(Mapper.fromMultiMatrixToString(message));
        lastState = StateLoggerHolder.EMPTY_STATE.state;
    }

    /**
     *
     * @param message array of Strings that will be loged
     */
    public void log(String... message){
        state = StateLoggerHolder.EMPTY_STATE.state;
        for(String str : message) {
            state.log(str + System.lineSeparator());
        }
    }

    private void matchIntStateOrReleaseBuff(){
        if (lastState != null && lastState != StateLoggerHolder.INT.state ){
            lastState.flush();
        }
    }

    private void matchStringStateOrReleaseBuff(){
        if (lastState != null && lastState != StateLoggerHolder.STRING.state ){
            lastState.flush();
        }
    }

}
