package com.acme.edu.writers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * BufferedLogWriter implements the LogWriter interface, internally maintains a buffer of messages and implements
 * prioritisation mechanism to guarantee that messages of great importance are logged in the first place.
 */
public class BufferedLogWriter implements LogWriter {

    /**
     * The size of buffer of messages.
     */
    public static final int BUFFER_SIZE = 50;

    /**
     * A message is treated as a high priority message if it contains this String.
     */
    public static final String HIGH_PRIORITY_SUBSTRING = "ERROR";
    private static final Comparator<String> MESSAGE_COMPARATOR =
            (message1, message2)
                    -> -Boolean.compare(containsHighPrioritySubstring(message1), containsHighPrioritySubstring(message2));

    private List<String> buffer = new ArrayList<>(BUFFER_SIZE);
    private BufferWriter bufferWriter;

    /**
     * Constructs a new BufferedLogWriter object with passed BufferWriter
     * @param bufferWriter is used later to write the internally maintained buffer of messages to a log
     */
    public BufferedLogWriter(BufferWriter bufferWriter) {
        this.bufferWriter = bufferWriter;
    }

    /**
     * Writes a log message to the internally maintained buffer of messages. If the buffer is full it flushes the buffer
     * to the log. The implementation guaranties that high priority messages are logged in the first place.
     * @param logMessage a message to be written to a log
     * @throws LogWriterException if an error occurs when writing the message to a log
     */
    @Override
    public void writeLine(String logMessage) throws LogWriterException {
        buffer.add(logMessage);

        if (buffer.size() < BUFFER_SIZE) {
            return;
        }

        Collections.sort(buffer, MESSAGE_COMPARATOR);

        try {
            bufferWriter.writeBuffer(buffer);
        } finally {
            buffer = new ArrayList<>(BUFFER_SIZE);
        }
    }

    private static boolean containsHighPrioritySubstring(String message) {
        return message.contains(HIGH_PRIORITY_SUBSTRING);
    }
}
