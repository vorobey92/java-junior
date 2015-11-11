package com.acme.edu.clientserver;

import com.acme.edu.writers.BufferWriter;
import com.acme.edu.writers.FileBufferWriterFactory;
import com.acme.edu.writers.LogWriterException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogServer {
    private static final int TIMEOUT = 5000;
    private static final int OK = 200;
    private static final int BAD_REQUEST = 400;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;

    private static final int NUMBER_OF_THREADS = 5;
    private static final String LOG_FILE = "server-log.txt";

    private final Object fileMonitor = new Object();
    private Executor executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private Logger logger = null;

    private int port;
    private FileBufferWriterFactory fileBufferWriterFactory;

    public LogServer(int port, FileBufferWriterFactory fileBufferWriterFactory) throws IOException {
        this.port = port;
        this.fileBufferWriterFactory = fileBufferWriterFactory;
    }

    public static void main(String[] args) throws IOException {
        LogServer server =
                new LogServer(Integer.parseInt(args[0]), new FileBufferWriterFactory(args[1], args[2]));
        server.activateLogging();
        server.start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    executor.execute(new RequestHandler(clientSocket, fileBufferWriterFactory.createBufferWriter()));
                } catch (IOException e) {
                    logExceptionMessage("Cannot accept an incoming connection", e);
                }
            }
        } catch (IOException e) {
            String message = "The server failed to start:";
            RuntimeException runtimeException = new RuntimeException(message);
            runtimeException.initCause(e);
            logExceptionMessage(message, e);
            throw runtimeException;
        }
    }

    public void activateLogging() throws IOException {
        logger = Logger.getLogger("server-log");
        FileHandler fileHandler = new FileHandler(LOG_FILE);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
    }

    private void logExceptionMessage(String message, Exception e) {
        if (logger != null) {
            logger.log(Level.WARNING, message + ": " + e.getMessage());
        }
    }

    private void logExceptionMessageAndSendDataToClient(
            String message, Exception e, int statusCode, ObjectOutputStream objectOutputStream, Socket clientSocket
    ) throws IOException {
        logExceptionMessage(message, e);
        objectOutputStream.writeInt(statusCode);
        objectOutputStream.writeUTF(message);
        objectOutputStream.flush();
        clientSocket.shutdownOutput();
    }

    private class RequestHandler implements Runnable {
        private Socket clientSocket;
        private BufferWriter bufferWriter;

        private RequestHandler(Socket clientSocket, BufferWriter bufferWriter) {
            this.clientSocket = clientSocket;
            this.bufferWriter = bufferWriter;
        }

        @Override
        public void run() {
            try (ObjectInputStream objectInputStream =
                         new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                 ObjectOutputStream objectOutputStream =
                         new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()))) {
                clientSocket.setSoTimeout(TIMEOUT);
                List<String> messages;

                try {
                    messages = (List<String>) objectInputStream.readObject();
                    clientSocket.shutdownInput();
                } catch (SocketTimeoutException e) {
                    logExceptionMessageAndSendDataToClient(
                            "The server timed out waiting for the request",
                            e,
                            REQUEST_TIMEOUT,
                            objectOutputStream,
                            clientSocket
                    );
                    return;
                } catch (ClassNotFoundException | IOException e) {
                    logExceptionMessageAndSendDataToClient(
                            "The communication protocol was violated: the server received unexpected data types",
                            e,
                            BAD_REQUEST,
                            objectOutputStream,
                            clientSocket);
                    return;
                }

                try {
                    synchronized (fileMonitor) {
                        bufferWriter.writeBuffer(messages);
                    }
                    objectOutputStream.writeInt(OK);
                    objectOutputStream.flush();
                    clientSocket.shutdownOutput();
                } catch (LogWriterException e) {
                    logExceptionMessageAndSendDataToClient(
                            "Cannot write to file", e, INTERNAL_SERVER_ERROR, objectOutputStream, clientSocket
                    );
                }
            } catch (IOException e) {
                logExceptionMessage("An exceptional situation occured", e);
            }
        }
    }
}
