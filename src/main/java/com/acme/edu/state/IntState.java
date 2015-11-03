package com.acme.edu.state;

import com.acme.edu.printer.Printable;

/**
 * State class for Int
 */
public class IntState extends State {

    private static final String PRIMITIVE = "primitive: ";
    protected static int cntOfInts = 0;
    protected static int bufferOfInts = 0;

    /**
     * Creates IntState object
     * @param printer determines stream for logging
     */
    public IntState(Printable printer) {
        super(printer);
    }


    /**
     *  Method for logging ints.
     *  For sequence of ints This method will log sum of ints when
     *  Also it logs two numbers (sum and new int) if type overflow while summing.
     * @param message number (int) that will be logged (or sum for sequence)
     */
    @Override
    public void log(String message){
        if (printIfOverflof(Integer.parseInt(message))) {
            return;
        }

        bufferOfInts += Integer.parseInt(message);
        cntOfInts++;
    }

    /**
     * Method to release buffer into log.
     */
    @Override
    public void flush(){
        checkAndPrintSum();
    }

    protected void checkAndPrintSum() {
        if (cntOfInts > 0) {
            println(PRIMITIVE + bufferOfInts);
            resetCounters();
        }
    }

    protected static void resetCounters() {
        bufferOfInts = 0;
        cntOfInts = 0;
    }

    private boolean printIfOverflof(int message) {
        if (message + bufferOfInts < 0) {
            println(PRIMITIVE + bufferOfInts);
            println(PRIMITIVE + message);
            resetCounters();
            return true;
        }
        return false;
    }

}
