package com.acme.edu.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
                    } catch (SocketTimeoutException e) {
                        clientSocket.shutdownInput();
                        objectOutputStream.writeInt(REQUEST_TIMEOUT);
                        objectOutputStream.flush();
                        clientSocket.shutdownOutput();
                        continue;
                    } catch (ClassNotFoundException | IOException e) {
                        clientSocket.shutdownInput();
                        objectOutputStream.writeInt(BAD_REQUEST);
                        objectOutputStream.writeUTF(
                                "The communication protocol was violated: the server received unexpected data types"
                        );
                        objectOutputStream.flush();
                        clientSocket.shutdownOutput();
                        continue;
                    }

                    clientSocket.shutdownInput();

                    try {
                        writeTofile(messages, charset);
                        objectOutputStream.writeInt(OK);
                    } catch (FileNotFoundException e) {
                        objectOutputStream.writeInt(INTERNAL_SERVER_ERROR);
                        objectOutputStream.writeUTF("Error occurred while writing a log file\r\n" + e.getMessage());
                    } catch (UnsupportedEncodingException e) {
                        objectOutputStream.writeInt(INTERNAL_SERVER_ERROR);
                        objectOutputStream.writeUTF(
                                "The server does not support the provided charset: charset=" + charset
                        );
                    } finally {
                        objectOutputStream.flush();
                        clientSocket.shutdownOutput();
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
