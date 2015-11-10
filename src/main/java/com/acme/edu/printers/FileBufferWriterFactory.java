package com.acme.edu.printers;

public class FileBufferWriterFactory {
    private String fileName;
    private String charset;

    public FileBufferWriterFactory(String fileName, String charset) {
        this.fileName = fileName;
        this.charset = charset;
    }

    public BufferWriter createBufferWriter() {
        return new FileBufferWriter(fileName, charset);
    }
}
