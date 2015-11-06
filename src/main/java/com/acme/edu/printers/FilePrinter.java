package com.acme.edu.printers;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FilePrinter implements Printer {
    private static final int BUFFER_SIZE = 50;
    private String fileName;
    private String charSet;
    private List<String> buffer = new ArrayList<String>(BUFFER_SIZE);

    public FilePrinter(String fileName, String charSet) {
        this.fileName = fileName;
        this.charSet = charSet;
    }

    @Override
    public void println(String stringToPrint) throws PrinterException {
        buffer.add(stringToPrint);

        if (buffer.size() == BUFFER_SIZE) {
            try (PrintWriter printWriter =
                         new PrintWriter(
                                 new BufferedWriter(
                                         new OutputStreamWriter(
                                                 new FileOutputStream(fileName, true), charSet)))) {

                for (String stringFromBuffer : buffer) {
                    printWriter.println(stringFromBuffer);
                }
                printWriter.flush();
            } catch (IOException e) {
                throw new PrinterException("I/O exception of some sort has occurred", e);
            } finally {
                buffer.clear();
            }
        }
    }
}
