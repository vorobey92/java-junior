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
                try {
                    new ServeClient(client);
                } catch (IOException e) {
                    System.err.print(e);
                    if (client != null){
                        try{
                            client.close();
                        }catch (IOException e1){
                            System.err.print(e1);
                        }
                    }
                }
            }
        } catch (PrintException e) {
            System.err.print(e);
        } catch (IOException e) {
            System.err.print(e);
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
                }
            }
        } catch (IOException | PrintException e) {
            System.err.println(e);
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.print(e);
            }
        }
    }
}


