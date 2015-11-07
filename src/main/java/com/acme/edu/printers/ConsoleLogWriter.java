package com.acme.edu.printers;

import java.io.PrintStream;

public class ConsoleLogWriter implements LogWriter {

    private static final PrintStream OUT = System.out;

    @Override
    public void println(String stringToPrint) {
        OUT.println(stringToPrint);
    }
}
