package com.acme.edu.printers;

import java.io.PrintStream;

public class StdOutPrinter implements Printer {

    private static final PrintStream OUT = System.out;

    @Override
    public void println(String stringToPrint) {
        OUT.println(stringToPrint);
    }

    @Override
    public void close() {
        OUT.close();
    }
}
