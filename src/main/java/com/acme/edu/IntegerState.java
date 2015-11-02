package com.acme.edu;

import com.acme.edu.printers.Printer;

public class IntegerState extends State {
    private String buffer;
    private int maxValue;
    private int minValue;

    public IntegerState(int maxValue, int minValue, Printer printer) {
        super(printer);
        buffer = null;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    @Override
    public void fflush() {
        if (buffer != null) {
            getPrinter().println("primitive: " + buffer);
            buffer = null;
        }
    }

    @Override
    public void log(String message) {
        if (isSumOutOfRange(message)) {
            fflush();
            buffer = message;
            fflush();
            buffer = null;

            return;
        }

        if (buffer != null) {
            buffer = Integer.toString(Integer.parseInt(buffer) + Integer.parseInt(message));
        } else {
            buffer = message;
        }
    }

    private boolean isSumOutOfRange(String message) {
        if (buffer == null) {
            return false;
        }

        int sum = Integer.parseInt(buffer);
        int summand = Integer.parseInt(message);

        return (summand > 0 && maxValue - summand < sum)
                || (summand < 0 && minValue - summand > sum);
    }
}
