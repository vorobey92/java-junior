package com.acme.edu;

import com.acme.edu.printers.Printer;

public class Unaccumulating extends State {

    public Unaccumulating(Printer printer) {
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
