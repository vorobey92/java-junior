package com.acme.edu;

import com.acme.edu.printers.Printer;

public class UnaccumulatingState extends State {

    public UnaccumulatingState(Printer printer) {
        super(printer);
    }

    @Override
    public void fflush() {
    }

    @Override
    public void log(String s) {
        getPrinter().println(s);
    }
}
