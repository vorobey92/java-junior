package com.acme.edu.printers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileLogWriter implements LogWriter {
    private static final int BUFFER_SIZE = 50;
    private String fileName;
    private String charset;
    private List<String> buffer = new ArrayList<>(BUFFER_SIZE);

    public FileLogWriter(String fileName, String charset) {
        this.fileName = fileName;
        this.charset = charset;
    }

    @Override
    public void println(String stringToPrint) throws LogWriterException {
        buffer.add(stringToPrint);

        if (buffer.size() < BUFFER_SIZE) {
            return;
        }

        try (PrintWriter printWriter =
                     new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), charset), false)) {

            for (String stringFromBuffer : buffer) {
                printWriter.println(stringFromBuffer);
            }
            printWriter.flush();
        } catch (IOException e) {
            throw new LogWriterException("I/O exception of some sort has occurred", e);
        } finally {
            buffer.clear();
        }
    }
}
