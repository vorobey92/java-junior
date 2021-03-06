package com.acme.edu.state;

import com.acme.edu.exception.PrintException;
import com.acme.edu.printer.Printable;

/**
 * State class for Int
 */
public class IntState extends State {

    private static final String PREFIX = "primitive: ";
    protected static int cntOfInts = 0;
    protected static int bufferOfInts = 0;

    /**
     * Creates IntState object
     * @param printers determines streams for logging
     */
    public IntState(Printable... printers) {
        super(printers);
    }


    /**
     *  Method for logging ints.
     *  For sequence of ints This method will log sum of ints when
     *  Also it logs two numbers (sum and new int) if type overflow while summing.
     * @param message number (int) that will be logged (or sum for sequence)
     */
    @Override
    public void log(String message) throws PrintException {
        int parsedMessage = Integer.parseInt(message);
        // Checking type overflow
        if (    (parsedMessage > 0) &&
                (bufferOfInts > 0)  &&
                (parsedMessage + bufferOfInts < 0)) {
            println(PREFIX + bufferOfInts);
            println(PREFIX + message);
            bufferOfInts = 0;
            cntOfInts = 0;
            return;
        }
        // Checking type overflow
        if (    (parsedMessage < 0) &&
                (bufferOfInts < 0)              &&
                (parsedMessage + bufferOfInts > 0)) {
            println(PREFIX + bufferOfInts);
            println(PREFIX + message);
            bufferOfInts = 0;
            cntOfInts = 0;
            return;
        }

        bufferOfInts += parsedMessage;
        cntOfInts++;
    }

    /**
     * Method to release buffer into log.
     */
    @Override
    public void flush() throws PrintException {
        if (cntOfInts > 0) {
            println(PREFIX + bufferOfInts);
            bufferOfInts = 0;
            cntOfInts = 0;
        }
    }

}
