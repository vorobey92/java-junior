package com.acme.edu;

import com.acme.edu.printers.Printer;

public abstract class State {
    private Printer printer;

    public State(Printer printer) {
        this.printer = printer;
    }

    public abstract void fflush();
    public abstract void log(String stringToBeLogged);

    public void close() {
        fflush();
        printer.close();
    }

    protected Printer getPrinter() {
        return printer;
    }
}
