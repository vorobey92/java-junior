package com.acme.edu.printers;

import java.util.ArrayList;
import java.util.List;

public abstract class BufferedLogWriter implements LogWriter {
    private static final int BUFFER_SIZE = 50;
    private List<String> buffer = new ArrayList<>(BUFFER_SIZE);

    @Override
    public void writeLine(String stringToPrint) throws LogWriterException {
        buffer.add(stringToPrint);

        if (buffer.size() < BUFFER_SIZE) {
            return;
        }

        try {
            write(buffer);
        } finally {
            buffer.clear();
        }
    }

    protected abstract void write(List<String> buffer) throws LogWriterException;
}
