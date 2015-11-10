package com.acme.edu.writers;

public interface LogWriter {
    void writeLine(String stringToPrint) throws LogWriterException;
}
