package com.acme.edu.printer;

import com.acme.edu.exception.CanNotPrintException;
import java.io.*;


/**
 * Realisation of Printable interface.
 * FilePrinter logs messages into file.
 *
 */
public class FilePrinter implements Printable {

    private static BufferedWriter i;
    private static StringBuilder str = new StringBuilder("");
    private static int countOfLogs = 0;
    private File file;

    /**
     * Creates BufferedWriter(file)
     *@param file
     * @param code charSet
     */
    public FilePrinter(File file,String code) throws CanNotPrintException {
        this.file = file;
        try {
            i = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),code));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            throw new CanNotPrintException(e);
        }
    }

    /**
     *
     * @param  message The <code>String</code> to be printed
     * @throws CanNotPrintException
     */
    @Override
    public void print(String message) throws CanNotPrintException {

        try{
            if (countOfLogs < 50){
                str.append(message);
                countOfLogs++;
            }else {
                i.write(str.toString());
                str.delete(0, str.length());
                countOfLogs = 0;
                i.flush();
            }
        } catch (IOException e) {
            throw new CanNotPrintException(e);
        }
    }

    /**
     * Prints a String and then terminate the line.
     * @param message The <code>String</code> to be printed
     * @throws CanNotPrintException
     */
    @Override
    public void println(String message) throws CanNotPrintException {
        print(message + System.lineSeparator());
    }

    /**
     * Stops process of logging to file
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
