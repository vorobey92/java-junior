package com.acme.edu.clientserver;

import com.acme.edu.printers.BufferWriter;
import com.acme.edu.printers.FileBufferWriter;
import com.acme.edu.printers.LogWriterException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;

public class LogServer implements Runnable {
    private static final int TIMEOUT = 5000;
    private static final int OK = 200;
    private static final int BAD_REQUEST = 400;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;

    private static final PrintStream ERR = System.err;

    private int port;
    private BufferWriter bufferWriter;

    public LogServer(int port, BufferWriter bufferWriter) {
        this.port = port;
        this.bufferWriter = bufferWriter;
    }

    public static void main(String[] args) {
        BufferWriter bufferWriter = new FileBufferWriter(args[1], args[2]);
        LogServer server = new LogServer(Integer.parseInt(args[0]), bufferWriter);
        server.run();
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream objectInputStream =
                             new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                     ObjectOutputStream objectOutputStream =
                             new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()))) {

                    clientSocket.setSoTimeout(TIMEOUT);
                    List<String> messages;

                    try {
                        messages = (List<String>) objectInputStream.readObject();
                        clientSocket.shutdownInput();
                    } catch (SocketTimeoutException e) {
                        writeDataAndShutdownOutputStream(
                                REQUEST_TIMEOUT,
                                "The server timed out waiting for the request",
                                objectOutputStream,
                                clientSocket
                        );
                        continue;
                    } catch (ClassNotFoundException | IOException e) {
                        writeDataAndShutdownOutputStream(
                                BAD_REQUEST,
                                "The communication protocol was violated: the server received unexpected data types",
                                objectOutputStream,
                                clientSocket
                        );
                        continue;
                    }

                    try {
                        bufferWriter.writeBuffer(messages);
                        objectOutputStream.writeInt(OK);
                        objectOutputStream.flush();
                        clientSocket.shutdownOutput();
                    } catch (LogWriterException e) {
                        writeDataAndShutdownOutputStream(
                                INTERNAL_SERVER_ERROR,
                                e.getMessage(),
                                objectOutputStream,
                                clientSocket
                        );
                    }
                } catch (IOException e) {
                    ERR.println(new Date());
                    e.printStackTrace();
                    ERR.println();
                }
            }
        } catch (IOException e) {
            ERR.println("The server failed to start:\r\n" + e.getMessage());
            System.exit(1);
        }
    }

    private static void writeDataAndShutdownOutputStream(
            int statusCode, String errorMessage, ObjectOutputStream objectOutputStream, Socket clientSocket
    ) throws IOException {
        objectOutputStream.writeInt(statusCode);
        objectOutputStream.writeUTF(errorMessage);
        objectOutputStream.flush();
        clientSocket.shutdownOutput();
    }
}
