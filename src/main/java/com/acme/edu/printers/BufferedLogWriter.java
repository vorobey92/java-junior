package com.acme.edu.printers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BufferedLogWriter implements LogWriter {
    private static final int BUFFER_SIZE = 50;
    private static final String HIGH_PRIORITY_SUBSTRING = "ERROR";
    private static final Comparator<String> MESSAGE_COMPARATOR =
            (message1, message2)
                    -> Boolean.compare(containsHighPrioritySubstring(message1), containsHighPrioritySubstring(message2));

    private List<String> buffer = new ArrayList<>(BUFFER_SIZE);
    private BufferWriter bufferWriter;

    public BufferedLogWriter(BufferWriter bufferWriter) {
        this.bufferWriter = bufferWriter;
    }

    @Override
    public void writeLine(String stringToPrint) throws LogWriterException {
        buffer.add(stringToPrint);

        if (buffer.size() < BUFFER_SIZE) {
            return;
        }

        Collections.sort(buffer, MESSAGE_COMPARATOR);

        try {
            bufferWriter.writeBuffer(buffer);
        } finally {
            buffer.clear();
        }
    }

    private static boolean containsHighPrioritySubstring(String message) {
        return message.contains(HIGH_PRIORITY_SUBSTRING);
    }
}
