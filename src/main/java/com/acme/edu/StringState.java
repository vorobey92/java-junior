package com.acme.edu;

import com.acme.edu.printers.Printer;

public class StringState extends State {
    private String buffer;
    private int lengthOfStringsSequence;

    public StringState(Printer printer) {
        super(printer);
        buffer = null;
        lengthOfStringsSequence = 0;
    }

    @Override
    public void fflush() {
        if (buffer != null) {
            getPrinter().println("string: " + buffer + stringSuffix());
            buffer = null;
        }
    }

    @Override
    public void log(String message) {
        if (buffer != null && buffer.equals(message)) {
            ++lengthOfStringsSequence;
            return;
        }

        if (buffer != null && !buffer.equals(message)) {
            fflush();
        }

        lengthOfStringsSequence = 1;
        buffer = message;
    }

    private String stringSuffix() {
        return (lengthOfStringsSequence == 1) ? "" : (" (x" + lengthOfStringsSequence + ")");
    }
}
