package com.acme.edu.printers;

import java.io.PrintStream;

public class ConsolePrinter implements Printer {

    private static final PrintStream OUT = System.out;

    @Override
    public void print(String stringToPrint) {
        OUT.print(stringToPrint);
    }

    @Override
    public void println(String stringToPrint) {
        OUT.println(stringToPrint);
    }

    @Override
    public void close() {
        OUT.close();
    }
}
