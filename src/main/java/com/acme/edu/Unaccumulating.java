package com.acme.edu;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Unaccumulating implements State {
    private PrintStream printWriter;

    public Unaccumulating(PrintStream outputStream) {
        printWriter = outputStream;
    }

    @Override
    public void print() {
    }

    @Override
    public void add(String s) {
        printWriter.println(s);
    }
}
