package com.acme.edu.printers;

public interface Printer {
    void print(String stringToPrint) throws PrinterException;
    void println(String stringToPrint) throws PrinterException;
    void close() throws PrinterException;
}
