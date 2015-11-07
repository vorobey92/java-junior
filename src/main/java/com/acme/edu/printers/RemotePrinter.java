package com.acme.edu.printers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RemotePrinter implements Printer {
    private static final int BUFFER_SIZE = 50;
    private static final int OK = 200;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private String host;
    private int port;
    private List<String> buffer = new ArrayList<>(BUFFER_SIZE);

    public RemotePrinter(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void println(String stringToPrint) throws PrinterException {
        buffer.add(stringToPrint);

        if (buffer.size() < BUFFER_SIZE) {
            return;
        }

        String serverExceptionMessage = null;
        try (Socket socket = new Socket(host, port);
             DataOutputStream dataOutputStream =
                     new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
             ObjectInputStream objectInputStream =
                     new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))) {

            for (String stringFromBuffer : buffer) {
                dataOutputStream.writeUTF(stringFromBuffer);
            }

            dataOutputStream.close();
            int statusCode = objectInputStream.readInt();

            if (statusCode == OK) {
                return;
            }

            if (statusCode == INTERNAL_SERVER_ERROR) {
                serverExceptionMessage = objectInputStream.readUTF();
                Exception serverException = (Exception) objectInputStream.readObject();
                throw new PrinterException("The server encountered an unexpected condition", serverException);
            }

            throw new PrinterException("Bad response was received from the server");

        } catch (IOException e) {
            throw new PrinterException("I/O exception of some sort has occurred", e);
        } catch (ClassNotFoundException e) {
            throw new PrinterException(serverExceptionMessage, e);
        } finally {
            buffer.clear();
        }
    }
}
