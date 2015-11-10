package com.acme.edu.writers;

import java.util.List;

public interface BufferWriter {
    void writeBuffer(List<String> buffer) throws LogWriterException;
}
