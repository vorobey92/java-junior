package com.acme.edu;

import com.acme.edu.exception.PrintException;
import com.acme.edu.printer.OutputStreamPrinter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server. Writes logs into file.
 */
public class Server {

    private Server() {

    }

    /**
     * Starts a server
     *
     * @param args
     * @throws PrintException
     */
    public static void main(String[] args) throws PrintException, IOException {

        try {
            ServerSocket ss = new ServerSocket(6666);
            while (true) {
                Socket client = ss.accept();

                BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));

                OutputStreamPrinter pr = new OutputStreamPrinter(new File("ServerLog.txt"), "UTF-8", false);

                String readLine;
                while ((readLine = is.readLine()) != null) {
                    if ("STOP".equals(readLine)) {
                        System.out.println("Sry, we are closing");
                        System.exit(0);
                    }
                    pr.println(readLine);
                    System.out.println(">>>>>" + readLine);

                }

            }
        }catch (Exception e){}
    }
}