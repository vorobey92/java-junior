package com.acme.edu.printer;

import com.acme.edu.exception.CanNotPrintException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 */
public class NetPrinter implements Printable {

    private static BufferedWriter i;
    private static StringBuilder str = new StringBuilder("");
    private static int countOfLogs = 0;
    private Socket socket;

    public NetPrinter(String host, int port) throws CanNotPrintException {
        try {
            socket = new Socket(host, port);
            i = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new CanNotPrintException(e);
        }
    }

    /**
     *
     * @param message String message
     * @throws CanNotPrintException
     */
    @Override
    public void print(String message) throws CanNotPrintException {
        if (countOfLogs < 50) {
            str.append(message);
            countOfLogs++;
        } else {
            try {
                i.write(str.toString());
                str.delete(0, str.length());
                countOfLogs = 0;
                i.flush();
            } catch(IOException e){
            throw new CanNotPrintException(e);
             }
        }
    }

    /**
     *
     * @param message The <code>String</code> to be printed
     * @throws CanNotPrintException
     */
    @Override
    public void println(String message) throws CanNotPrintException {
        print(message + System.lineSeparator());
    }


    public static void stop() throws CanNotPrintException {
        try {
            if (i == null) {
                return;
            }
            i.write(str.toString());
            str.delete(0, str.length());
            i.close();
        } catch (IOException e) {
            throw new CanNotPrintException(e);
        }
    }
}
