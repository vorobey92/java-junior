package com.acme.edu.writers;

import java.io.PrintStream;

public class ConsoleLogWriter implements LogWriter {

    private static final PrintStream OUT = System.out;

    @Override
    public void writeLine(String stringToPrint) {
        OUT.println(stringToPrint);
    }
}
