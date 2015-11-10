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
        ServerSocket ss = null;
        try {
             ss = new ServerSocket(port);
        } catch (IOException e) {
            try {
                if (ss != null) {
                    ss.close();
                }
            } catch (IOException e1) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Server starter!");
        try {
            while (true) {
                Socket client = ss.accept();
                try {
                    new ServeClient(client);
                } catch (IOException e) {
                    if (client != null){
                        try{
                            client.close();
                        }catch (IOException e1){
                            throw new RuntimeException(e1);
                        }
                    }
                }
            }
        } catch (PrintException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


class ServeClient extends Thread {
    private Socket socket;
    private BufferedReader in;
    private OutputStreamPrinter pr;


    public ServeClient(Socket s) throws PrintException, IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        pr = new OutputStreamPrinter(new File("ServerLog.txt"), "UTF-8", false);
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String readLine = in.readLine();
                if ("STOP".equals(readLine)) {
                    break;
                }
                if (readLine != null) {
                    pr.println(Thread.currentThread().getName() + " MESSAGE: " + readLine);
                    System.out.println(Thread.currentThread().getName() + " MESSAGE: " + readLine);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (PrintException e) {
            System.err.println(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.print(e);
            }
        }
    }
}


