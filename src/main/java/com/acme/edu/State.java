package com.acme.edu;

import com.acme.edu.printers.Printer;

public abstract class State {
    private Printer printer;

    public State(Printer printer) {
        this.printer = printer;
    }

    public abstract void print();
    public abstract void add(String s);

    protected Printer getPrinter() {
        return printer;
    }
}
