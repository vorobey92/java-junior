package com.acme.edu;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class StringState implements State {
    private String buffer;
    private int lengthOfStringsSequence;
    private PrintStream printWriter;

    public StringState(PrintStream outputStream) {
        buffer = null;
        printWriter = outputStream;
        lengthOfStringsSequence = 0;
    }

    @Override
    public void print() {
        if (buffer != null) {
            printWriter.println("string: " + buffer + stringSuffix());
            buffer = null;
        }
    }

    @Override
    public void add(String message) {
        if (buffer != null && buffer.equals(message)) {
            ++lengthOfStringsSequence;
            return;
        }

        if (buffer != null && !buffer.equals(message)) {
            print();
        }

        lengthOfStringsSequence = 1;
        buffer = message;
    }

    private String stringSuffix() {
        return (lengthOfStringsSequence == 1) ? "" : (" (x" + lengthOfStringsSequence + ")");
    }
}
