package com.acme.edu;

import com.acme.edu.exception.PrintException;
import com.acme.edu.printer.OutputStreamPrinter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

        ServerSocket ss = new ServerSocket(6666);

        Executor pool = Executors.newFixedThreadPool(3);

        while (true) {
            Socket client = ss.accept();
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try (
                            BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    ) {

                        OutputStreamPrinter pr = new OutputStreamPrinter(new File("ServerLog.txt"), "UTF-8", false);

                        String readLine;
//                        while ((readLine = is.readLine()) != null) {
//                            if ("STOP".equals(readLine)) {
//                                System.out.println("Sry, we are closing");
//                                System.exit(0);
//                            }
                            readLine = is.readLine();
                            pr.println(Thread.currentThread().getName().toString() + "MESSAGE: " + readLine);
//                        }
                    } catch (PrintException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
