package com.acme.edu.printers;

import java.lang.IllegalArgumentException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
        try {
            Files.write(
                    Paths.get(fileName),
                    buffer,
                    Charset.forName(charset),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException | IllegalArgumentException e) {
            throw new LogWriterException("An exception of some sort has occurred while writing messages to the file", e);
        }
    }
}
