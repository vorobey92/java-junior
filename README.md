package com.jet.edu.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class Client {

    private final Pattern chidPattern = Pattern.compile("^/chid \\S{1,50}$");
    private final Pattern histPattern = Pattern.compile("^/hist$");
    private final Pattern sndPattern = Pattern.compile("^/snd \\S{1,150}$");
    private final Pattern chroomPattern = Pattern.compile("^/chroom \\S{1,50}$");

    private String host;
    private int port;
    private final BlockingQueue<String> blockingQueue;
    private Thread backgroundThread;
    private volatile Socket socket = null;
    private final Object socketMonitor;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        this.blockingQueue = new LinkedBlockingQueue<String>();
        this.socketMonitor = new Object();
    }

    public void start() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String userInput;
            while ((userInput = bufferedReader.readLine()) != null) {
                if ("/quit".equals(userInput)) {
                    if (backgroundThread != null) {
                        backgroundThread.interrupt();
                    }
                    closeSocket();
                    return;
                }

                if (!isCorrectCommand(userInput)) {
                    out.println("Unsupported command");
                    continue;
                }

                if (tryStartNetworkingIfItStopped()) {
                    blockingQueue.put(userInput);
                }
            }

        } catch (IOException | InterruptedException e) {
            //log and close app
            e.printStackTrace();
        } finally {
            closeSocket();
        }
    }

    public static void main(String[] args) {
        Client client = new Client(args[0], Integer.parseInt(args[1]));
        client.start();
    }

    private boolean isCorrectCommand(String userInput) {
        return chidPattern.matcher(userInput).matches()
                || sndPattern.matcher(userInput).matches()
                || histPattern.matcher(userInput).matches()
                || chroomPattern.matcher(userInput).matches();
    }

    private boolean tryStartNetworkingIfItStopped() {
        synchronized (socketMonitor) {
            if (socket == null) {
                blockingQueue.clear();
                try {
                    socket = new Socket(host, port);
                    backgroundThread = new Thread(new Handler(blockingQueue, socket));
                    backgroundThread.start();
                    return true;
                } catch (IOException e) {
                    //need to log
                    out.println("Cannot connect to the server");
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    private void closeSocket() {
        synchronized (socketMonitor) {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    //need to log
                    e.printStackTrace();
                } finally {
                    socket = null;
                }
            }
        }
    }

    private class Handler implements Runnable {
        private final String LINE_SEPARATOR = System.lineSeparator();

        private BlockingQueue<String> blockingQueue;
        private BufferedWriter bufferedWriter;
        private BufferedReader bufferedReader;

        Handler(BlockingQueue<String> blockingQueue, Socket socket) throws IOException {
            this.blockingQueue = blockingQueue;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                String command;
                try {
                    command = blockingQueue.take();
                } catch (InterruptedException e) {
                    //log the message
                    return;
                }

                String transportCommand = command.replaceAll(" ", LINE_SEPARATOR) + LINE_SEPARATOR + LINE_SEPARATOR;

                try {
                    bufferedWriter.write(transportCommand);
                    bufferedWriter.flush();

                    String response = bufferedReader.readLine();
                    printResponseStatus(response);
                    StringBuilder messages = new StringBuilder();
                    while (!"".equals(response = bufferedReader.readLine())) {
                        if (response == null) {
                            closeSocket();
                            return;
                        }

                        messages.append(response).append(LINE_SEPARATOR);
                    }

                    if (messages.length() > 0) {
                        out.println(messages.toString());
                    }
                } catch (IOException e) {
                    //need to log it
                    e.printStackTrace();
                    closeSocket();
                    return;
                }
            }
        }

        public void printResponseStatus(String responseStatus) {
            if ("OK".equals(responseStatus)) {
                out.println("OK");
            } else if ("Internal server error".equals(responseStatus)) {
                out.println("Internal server error");
            } else if ("Name is reserved".equals(responseStatus)) {
                out.println("Name is reserved");
            } else {
                out.println("Unexpected status code");
            }
        }
    }
}
