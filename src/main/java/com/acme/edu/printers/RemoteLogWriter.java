package com.acme.edu.printers;

import com.acme.edu.clientserver.Client;
import java.util.List;

public class RemoteLogWriter extends BufferedLogWriter {
    private Client client;
    private String charset;

    public RemoteLogWriter(String charset, Client client) {
        this.client = client;
        this.charset = charset;
    }

    @Override
    protected void writeBuffer(List<String> buffer) throws LogWriterException {
        client.sendData(buffer, charset);
    }
}