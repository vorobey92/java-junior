package com.acme.edu;

import com.acme.edu.printers.Printer;

public class UnaccumulatingState extends State {

    public UnaccumulatingState(Printer printer) {
        super(printer);
    }

    @Override
    public void print() {
    }

    @Override
    public void add(String s) {
        getPrinter().println(s);
    }
}
