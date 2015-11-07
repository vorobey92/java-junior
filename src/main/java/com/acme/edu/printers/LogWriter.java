package com.acme.edu.printers;

public interface LogWriter {
    void println(String stringToPrint) throws LogWriterException;
}
