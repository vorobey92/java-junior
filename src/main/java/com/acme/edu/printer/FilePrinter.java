package com.acme.edu.printer;

import com.acme.edu.exception.CanNotPrintException;
import java.io.*;


/**
 *
 */
public class FilePrinter implements Printable {

    private String code;

    /**
     *
     * @param code charSet
     */
    public FilePrinter(String code){
        this.code = code;
    }
    @Override
    public void print(String message) throws CanNotPrintException {
        File file = new File("log.txt");

        try(
                RandomAccessFile i = new RandomAccessFile(file, "rw" );
        ) {
            i.seek(i.length());
            i.write(message.getBytes(code));
        } catch (IOException e) {
            throw new CanNotPrintException(e);
        }
    }

    @Override
    public void println(String message) throws CanNotPrintException {
        print(message + System.lineSeparator());
    }
}
