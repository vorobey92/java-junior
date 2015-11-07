package com.acme.edu.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;

public class LogServer {
    private static final int TIMEOUT = 5000;
    private static final int OK = 200;
    private static final int BAD_REQUEST = 400;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;

    private int port;
    private String fileName;

    public LogServer(int port, String fileName) {
        this.port = port;
        this.fileName = fileName;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream objectInputStream =
                             new ObjectInputStream(
                                     new BufferedInputStream(
                                             clientSocket.getInputStream()));
                     ObjectOutputStream objectOutputStream =
                             new ObjectOutputStream(
                                     new BufferedOutputStream(
                                             clientSocket.getOutputStream()))) {

                    clientSocket.setSoTimeout(TIMEOUT);
                    List<String> messages;
                    String charSet;

                    try {
                        messages = (List<String>) objectInputStream.readObject();
                        charSet = objectInputStream.readUTF();
                    } catch (SocketTimeoutException e) {
                        clientSocket.shutdownInput();
                        objectOutputStream.writeInt(REQUEST_TIMEOUT);
                        clientSocket.shutdownOutput();
                        continue;
                    } catch (ClassNotFoundException | IOException e) {
                        clientSocket.shutdownInput();
                        objectOutputStream.writeInt(BAD_REQUEST);
                        objectOutputStream.writeUTF("The communication protocol was violated: invalid parcel type");
                        clientSocket.shutdownOutput();
                        continue;
                    }

                    clientSocket.shutdownInput();

                    try {
                        writeTofile(messages, charSet);
                        objectOutputStream.writeInt(OK);
                    } catch (FileNotFoundException e) {
                        objectOutputStream.writeInt(INTERNAL_SERVER_ERROR);
                        objectOutputStream.writeUTF("Error occurred while writing a log file\r\n" + e.getMessage());
                    } catch (UnsupportedEncodingException e) {
                        objectOutputStream.writeInt(INTERNAL_SERVER_ERROR);
                        objectOutputStream.writeUTF(
                                "The server does not support the provided charset: charset=" + charSet
                        );
                    } finally {
                        clientSocket.shutdownOutput();
                    }

                } catch (IOException e) {
                    System.err.println(new Date());
                    e.printStackTrace();
                    System.err.println();
                }
            }
        } catch (IOException e) {
            System.err.println("The server failed to start:\r\n" + e.getMessage());
            System.exit(1);
        }
    }

    private void writeTofile(List<String> messages, String charSet)
            throws FileNotFoundException, UnsupportedEncodingException {
        try (PrintWriter printWriter =
                     new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), charSet), false)) {

            for (String message : messages) {
                printWriter.println(message);
            }
            printWriter.flush();
        }
    }
}
