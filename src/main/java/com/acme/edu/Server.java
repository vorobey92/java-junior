package com.acme.edu;

import com.acme.edu.exception.CanNotPrintException;
import com.acme.edu.printer.FilePrinter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class Server {
    public static void main(String[] args) throws CanNotPrintException {
        try {
            ServerSocket ss = new ServerSocket(6666);

            Socket client = ss.accept();

            BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
            File log = new File("log.txt");
            FilePrinter pr = new FilePrinter(log, "UTF-8");
            String readLine;
            while ((readLine = is.readLine()) != null) {
                pr.println(readLine);
            }

        } catch (IOException e) {
            throw new CanNotPrintException(e);
        }
    }

}
