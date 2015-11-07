package com.acme.edu.printers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public class FileLogWriter extends BufferedLogWriter {
    private String fileName;
    private String charset;

    public FileLogWriter(String fileName, String charset) {
        this.fileName = fileName;
        this.charset = charset;
    }

    @Override
    protected void write(List<String> buffer) throws LogWriterException {
        try (PrintWriter printWriter =
                     new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), charset), false)) {

            for (String stringFromBuffer : buffer) {
                printWriter.println(stringFromBuffer);
            }
            printWriter.flush();
        } catch (IOException e) {
            throw new LogWriterException("I/O exception of some sort has occurred", e);
        }
    }
}
