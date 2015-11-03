package com.acme.edu.state;

import com.acme.edu.printer.Printable;

/**
 *
 */
public class IntState extends State {

    private static final String PRIMITIVE = "primitive: ";
    protected static int cntOfInts = 0;
    protected static int bufferOfInts = 0;

    /**
     *
     * @param printer ...
     */
    public IntState(Printable printer) {
        super(printer);
    }


    /**
     *
     * @param message JavaDoc not ready
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
     *
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
