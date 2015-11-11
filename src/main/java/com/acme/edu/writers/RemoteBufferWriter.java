package com.acme.edu.writers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * RemoteBufferWriter is an implementation of BufferWriter and can be used to send a List of messages
 * to a remote LogServer.
 */
public class RemoteBufferWriter implements BufferWriter {
    private static final int OK = 200;
    private static final int BAD_REQUEST = 400;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int TIMEOUT = 3000;

    private String host;
    private int port;

    /**
     * Constructs a new RemoteBufferWriter.
     * @param host the host on which the LogServer runs
     * @param port the port number on which the LogServer runs
     */
    public RemoteBufferWriter(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Sends a List of strings to the LogServer.
     * @param buffer a list of messages which is to be sent by the RemoteBufferWriter
     * @throws LogWriterException if an error occurs when writing the message to a log
     */
    @Override
    public void writeBuffer(List<String> buffer) throws LogWriterException {
        try (Socket socket = new Socket(host, port)) {

            ObjectOutputStream objectOutputStream;
            try {
                objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                objectOutputStream.writeObject(buffer);
                objectOutputStream.flush();
                socket.shutdownOutput();
            } catch (IOException e) {
                throw new LogWriterException(
                        "An I/O error occurred while sending data to the server: " + toStringHostAndPort(), e
                );
            }

            ObjectInputStream objectInputStream;
            try {
                objectInputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                socket.setSoTimeout(TIMEOUT);

                switch (objectInputStream.readInt()) {
                    case OK:
                        socket.shutdownInput();
                        return;
                    case INTERNAL_SERVER_ERROR:
                    case BAD_REQUEST:
                        String serverErrorMessage = objectInputStream.readUTF();
                        socket.shutdownInput();
                        throw new LogWriterException(wrapServerErrorMessage(serverErrorMessage));
                    case REQUEST_TIMEOUT:
                        socket.shutdownInput();
                        throw new LogWriterException(
                                "The server timed out waiting for the request" + toStringHostAndPort()
                        );
                    default:
                        throw new LogWriterException(
                                "Bad response was received from the server: " + toStringHostAndPort()
                        );
                }
            } catch (SocketTimeoutException e) {
                throw new LogWriterException(
                        "The server did not respond within timeout: " + toStringHostAndPort(), e
                );
            } catch (IOException e) {
                throw new LogWriterException(
                        "An I/O error occurred while receiving data from the server: " + toStringHostAndPort(), e
                );
            }
        } catch (IOException e) {
            throw new LogWriterException("An I/O error occured when creating the socket: " + toStringHostAndPort(), e);
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