package com.acme.edu.printers;

public class StdOutPrinter implements Printer {
    @Override
    public void println(String stringToPrint) {
        System.out.println(stringToPrint);
    }
}
