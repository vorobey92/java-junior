package com.acme.edu.writers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * FileBufferWriter is an implementation of BufferWriter and can be used to write a List of messages
 * to a file.
 */
public class FileBufferWriter implements BufferWriter {
    private String fileName;
    private String charset;

    /**
     * Constructs a new FileBufferWriter
     * @param fileName the path to the file in which to write a list of messages
     * @param charset the charset to use for encoding
     */
    public FileBufferWriter(String fileName, String charset) {
        this.fileName = fileName;
        this.charset = charset;
    }

    /**
     * Writes a List of strings to the file. Each string is written to the file in sequence with each line terminated by
     * the platform's line separator, as defined by the system property line.separator. Characters are encoded into
     * bytes using the specified in constructor charset.
     * @param buffer a list of messages which is to be written by the FileBufferWriter
     * @throws LogWriterException if an error occurs when writing the message to a log
     */
    @Override
    public void writeBuffer(List<String> buffer) throws LogWriterException {
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
