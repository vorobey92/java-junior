package com.acme.edu.printer;

import com.acme.edu.exception.PrintException;

import java.io.*;
import java.net.Socket;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Realisation of Printable interface.
 * OutputStreamPrinter logs messages into file or through socket to server.
 * In the end of print need to call OutputStreamPrinter.stop() method to flush the buffer,
 *  and close file or socket.
 */
public class OutputStreamPrinter implements Printable {

    private static final int CAPACITY_OF_BUFFER = 50;
    private List<String> buffer = new LinkedList<>();
    private File file;
    private Socket socket;
//    private OutputStream outputStream;
    private Class outputStreamHelper;
    private boolean bufferization;
    //CharSet by default for sending to server
    private String code = "UTF-8";


    /**
     * Creates BufferedWriter(file)
     * @param file object of type File
     * @param code charSet
     * @param bufferization true if you want to activate second layer of buffer for 50 logs.
     * @throws PrintException
     */
    public OutputStreamPrinter(File file, String code, boolean bufferization) throws PrintException {
            this.file = file;
            outputStreamHelper = FileOutputStream.class;
            this.code = code;
            this.bufferization = bufferization;
    }

    /**
     * Creates a socket and sending text info to the server.
     * Charset of sending is UTF-8 by default.
     *
     * @param host name or ip of the server
     * @param port number of port
     * @param bufferization true if you want to activate second layer of buffer for 50 logs.
     * @throws PrintException
     */
    public OutputStreamPrinter(String host, int port, boolean bufferization) throws PrintException {
//        try {
//            socket = new Socket(host, port);
//            outputStreamHelper = socket.getOutputStream().getClass();
            this.bufferization = bufferization;
//        } catch (IOException e) {
//            throw new PrintException("Socket problem",e);
//        }
    }

    /**
     * This method have a buffer of 50 words,
     * so if you won't call stop() method and you don't have more than 50 words to print
     * you will see nothing in outputStream
     * @param  message The <code>String</code> to be printed
     * @throws PrintException
     */
    @Override
    public void print(String message) throws PrintException {
        if (bufferization) {
            if (buffer.size() < CAPACITY_OF_BUFFER) {
                buffer.add(message);
            } else {
                buffer.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if ( (o1.contains("ERROR")) && (!o2.contains("ERROR")) ) {
                            return 1;
                        } else if ( (!o1.contains("ERROR")) && (o2.contains("ERROR")) ) {
                            return -1;
                        } else {
                            return o1.compareTo(o2);
                        }
                    }
                });
                ourBufferFlush( buffer.toArray(new String[50]));
            }

        } else {
           ourBufferFlush(message);
        }
    }

    /**
     * Prints a String and then terminate the line.
     * This method have a buffer of 50 words,
     * so if you won't call stop() method and you don't have more than 50 words to print
     * you will see nothing in outputStream
     * @param message The <code>String</code> to be printed
     * @throws PrintException
     */
    @Override
    public void println(String message) throws PrintException {
        print(message + System.lineSeparator());
    }


    private void ourBufferFlush(String... messages) throws PrintException {
        if (outputStreamHelper == FileOutputStream.class) {
            try (
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true), code))
            ) {
                for (String s : messages) {
                    writer.write(s);
                }
            } catch (IOException e){
                throw new PrintException("Failure writing to file ", e);
            }
        } else {
            try (
                    Socket socket = new Socket("localhost", 6666);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
            ) {
                for (String s : messages) {
                    writer.write(s);
                }
            } catch (IOException e){
                throw new PrintException("Failure writing to the socket ", e);
            }
        }
    }
}

