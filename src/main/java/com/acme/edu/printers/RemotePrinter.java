package com.acme.edu.printers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class RemotePrinter implements Printer {
    private static final int OK = 200;
    private static final int BAD_REQUEST = 400;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int TIMEOUT = 3000;

    private static final int BUFFER_SIZE = 50;

    private String host;
    private int port;
    private String charset;
    private List<String> buffer = new ArrayList<>(BUFFER_SIZE);

    public RemotePrinter(String host, int port, String charset) {
        this.host = host;
        this.port = port;
        this.charset = charset;
    }

    @Override
    public void println(String stringToPrint) throws PrinterException {
        buffer.add(stringToPrint);

        if (buffer.size() < BUFFER_SIZE) {
            return;
        }

        try (Socket socket = new Socket(host, port);
             ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
             ObjectInputStream objectInputStream =
                     new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))) {

            objectOutputStream.writeObject(buffer);
            objectOutputStream.writeUTF(charset);
            socket.shutdownOutput();

            socket.setSoTimeout(TIMEOUT);

            switch (objectInputStream.readInt()) {
                case OK:
                    socket.shutdownInput();
                    return;
                case INTERNAL_SERVER_ERROR:
                case BAD_REQUEST:
                    String serverErrorMessage = objectInputStream.readUTF();
                    socket.shutdownInput();
                    throw new PrinterException(wrapServerErrorMessage(serverErrorMessage));
                case REQUEST_TIMEOUT:
                    socket.shutdownInput();
                    throw new PrinterException("The server timed out waiting for the request" + toStringHostAndPort());
                default:
                    socket.shutdownInput();
                    throw new PrinterException(
                            "Bad response was received from the server: " + toStringHostAndPort()
                    );
            }
        } catch (SocketTimeoutException e) {
            throw new PrinterException("The Log Server did not respond within timeout: " + toStringHostAndPort(), e);
        } catch (IOException e) {
            throw new PrinterException("I/O exception of some sort has occurred: " + toStringHostAndPort(), e);
        } finally {
            buffer.clear();
        }
    }

    private String toStringHostAndPort() {
        return "host=" + host + " port=" + port;
    }

    private String wrapServerErrorMessage(String serverExceptionMessage) {
        return "The server encountered an unexpected condition: " + toStringHostAndPort() + System.lineSeparator()
                + "Error Message: " + System.lineSeparator() + serverExceptionMessage;
    }
}