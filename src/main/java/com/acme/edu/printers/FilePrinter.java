package com.acme.edu.printers;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FilePrinter implements Printer {
    private static final int BUFFER_SIZE = 50;
    private String fileName;
    private String charSet;
    private int count = 0;
    private StringBuilder buffer = new StringBuilder();

    public FilePrinter(String fileName, String charSet) {
        this.fileName = fileName;
        this.charSet = charSet;
    }

    @Override
    public void println(String stringToPrint) throws PrinterException {
        buffer.append(stringToPrint);
        ++count;

        if (count > BUFFER_SIZE) {
            try (PrintWriter printWriter =
                         new PrintWriter(
                                 new BufferedWriter(
                                         new OutputStreamWriter(
                                                 new FileOutputStream(fileName, true), charSet)))) {
                printWriter.println(buffer.toString());
                printWriter.flush();
            } catch (IOException e) {
                throw new PrinterException("I/O exception of some sort has occurred", e);
            }
            buffer.setLength(0);
            count = 0;
        }
    }
}
