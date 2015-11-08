package com.acme.edu.printers;

public interface LogWriter {
    void writeLine(String stringToPrint) throws LogWriterException;
}
