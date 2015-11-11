package com.acme.edu;

import com.acme.edu.exception.PrintException;
import com.acme.edu.printer.OutputStreamPrinter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Server. Writes logs into file.
 */
public class Server extends Thread {
    private int port;


    /**
     * @param port
     */
    public Server(int port) {
        this.port = port;
        start();
    }

    @Override
    public void run() {
        try(
                ServerSocket ss = new ServerSocket(port)
        ) {
            while (true) {
                Socket client = ss.accept();
                new ServeClient(client);
            }
        } catch (IOException | PrintException e) {
            System.err.println(e);
        }
    }
}


class ServeClient extends Thread {
    private Socket socket;
    private OutputStreamPrinter pr = new OutputStreamPrinter(new File("ServerLog.txt"), "UTF-8", false);;


    public ServeClient(Socket s) throws PrintException, IOException {
        socket = s;
        start();
    }

    @Override
    public void run() {
        try (
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            while (true) {
                String readLine = in.readLine();
                if ("STOP".equals(readLine)) {
                    break;
                }
                if (readLine != null) {
                    pr.println(Thread.currentThread().getName() + " MESSAGE: " + readLine);
                }
            }
        } catch (IOException | PrintException e) {
            System.err.println(e);
        }
    }
}


