package com.acme.edu.writers;

import java.util.List;

/**
 * This interface abstracts the operation of writing a buffer of messages.
 */
public interface BufferWriter {
    /**
     * Writes a List of Strings.
     * @param buffer a list of messages which is to be written by the BufferWriter
     * @throws LogWriterException if an error occurs when writing the message to a log
     */
    void writeBuffer(List<String> buffer) throws LogWriterException;
}
