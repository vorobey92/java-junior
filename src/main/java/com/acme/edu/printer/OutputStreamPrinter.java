package com.acme.edu.printer;

import com.acme.edu.exception.CanNotPrintException;
import java.io.*;
import java.net.Socket;


/**
 * Realisation of Printable interface.
 * OutputStreamPrinter logs messages into file or through socket to server.
 * In the end of print need to call OutputStreamPrinter.stop() method to flush the buffer,
 *  and close file or socket.
 */
public class OutputStreamPrinter implements Printable {

    private static BufferedWriter i;
    private static StringBuilder str = new StringBuilder("");
    private static int countOfLogs = 0;
    private Socket socket;


    /**
     * Creates BufferedWriter(file)
     *@param file
     * @param code charSet
     */
    public OutputStreamPrinter(File file, String code) throws CanNotPrintException {
        try {
            i = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),code));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            throw new CanNotPrintException("File or Charset problems",e);
        }
    }

    /**
     * Creates a socket
     *
     * @param host name or ip of the server
     * @param port
     * @throws CanNotPrintException
     */
    public OutputStreamPrinter(String host, int port) throws CanNotPrintException {
        try {
            socket = new Socket(host, port);
            i = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new CanNotPrintException("Socket problem",e);
        }
    }

    /**
     * This method have a buffer of 50 words,
     * so if you won't call stop() method and you don't have more than 50 words to print
     * you will see nothing in outputStream
     * @param  message The <code>String</code> to be printed
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
     * Prints a String and then terminate the line.
     * This method have a buffer of 50 words,
     * so if you won't call stop() method and you don't have more than 50 words to print
     * you will see nothing in outputStream
     * @param message The <code>String</code> to be printed
     * @throws CanNotPrintException
     */
    @Override
    public void println(String message) throws CanNotPrintException {
        print(message + System.lineSeparator());
    }

    /**
     * Stops process of logging to file or to the server.
     * Also this method flush the buffer and close the OutputStream
     * @throws CanNotPrintException
     */
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
