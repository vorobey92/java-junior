package com.acme.edu.printers;

import java.util.List;

public interface BufferWriter {
    void writeBuffer(List<String> buffer) throws LogWriterException;
}
