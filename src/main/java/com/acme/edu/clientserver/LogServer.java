package com.acme.edu.clientserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
    private String fileName;

    public LogServer(int port, String fileName) {
        this.port = port;
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        LogServer server = new LogServer(Integer.parseInt(args[0]), args[1]);
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
                    String charset;

                    try {
                        messages = (List<String>) objectInputStream.readObject();
                        charset = objectInputStream.readUTF();
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
                        writeTofile(messages, charset);
                        writeDataAndShutdownOutputStream(OK, null, objectOutputStream, clientSocket);
                    } catch (IOException | InvalidPathException e) {
                        writeDataAndShutdownOutputStream(
                                INTERNAL_SERVER_ERROR,
                                "Error occurred while writing a log file\r\n" + e.getMessage(),
                                objectOutputStream,
                                clientSocket
                        );
                    } catch (IllegalArgumentException e) {
                        writeDataAndShutdownOutputStream(
                                INTERNAL_SERVER_ERROR,
                                "The server does not support the provided charset: charset=" + charset,
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

    private void writeTofile(List<String> messages, String charset) throws IOException, IllegalArgumentException {
        Files.write(
                Paths.get(fileName),
                messages,
                Charset.forName(charset),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.APPEND
        );
    }
}
