package com.acme.edu.printers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FilePrinter implements Printer {
    private File file;
    private String charSet;

    public FilePrinter(File file, String charSet) {
        this.file = file;
        this.charSet = charSet;
    }

    @Override
    public void print(String stringToPrint) throws PrinterException {
        try(PrintWriter printWriter =
                    new PrintWriter(
                            new BufferedWriter(
                                    new OutputStreamWriter(
                                            new FileOutputStream(file), charSet)))) {
            printWriter.print(stringToPrint);
            printWriter.flush();
        } catch (IOException e) {
            throw new PrinterException(e);
        }

    }

    @Override
    public void println(String stringToPrint) throws PrinterException {
        try(PrintWriter printWriter =
                    new PrintWriter(
                            new BufferedWriter(
                                    new OutputStreamWriter(
                                            new FileOutputStream(file), charSet)))) {
            printWriter.println(stringToPrint);
            printWriter.flush();
        } catch (IOException e) {
            throw new PrinterException(e);
        }

    }
}
